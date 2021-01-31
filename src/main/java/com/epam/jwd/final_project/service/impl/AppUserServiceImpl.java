package com.epam.jwd.final_project.service.impl;

import com.epam.jwd.final_project.context.impl.RatingContext;
import com.epam.jwd.final_project.criteria.AppUserCriteria;
import com.epam.jwd.final_project.criteria.Criteria;
import com.epam.jwd.final_project.dao.impl.AppUserDaoImpl;
import com.epam.jwd.final_project.domain.AppUser;
import com.epam.jwd.final_project.domain.Review;
import com.epam.jwd.final_project.domain.Status;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.exception.ValidationException;
import com.epam.jwd.final_project.pool.ConnectionPool;
import com.epam.jwd.final_project.service.UserService;
import com.epam.jwd.final_project.util.MessageSenderUtil;
import com.epam.jwd.final_project.util.PasswordHasherUtil;
import com.epam.jwd.final_project.validation.ValidationChain;
import com.epam.jwd.final_project.validation.ValidationChainFactory;
import com.epam.jwd.final_project.validation.ValidationType;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AppUserServiceImpl implements UserService {

    private static AppUserServiceImpl INSTANCE;

    public static AppUserServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppUserServiceImpl();
        }

        return INSTANCE;
    }

    @Override
    public boolean create(AppUser appUser) throws ValidationException, DatabaseInteractionException {
        boolean wasCreated;
        ValidationChain<AppUser> chain = ValidationChainFactory.INSTANCE.
                createValidationChain(appUser);
        List<String> validationErrors = chain.getValidationReport(appUser, ValidationType.CREATE_OBJECT);

        if (validationErrors.size() == 0) {
            wasCreated = AppUserDaoImpl.getInstance().create(appUser,
                    ConnectionPool.INSTANCE.getAvailableConnection());
            if (wasCreated) {
                MessageSenderUtil.sendRegistrationEmail(appUser.getEmail());
                RatingContext.INSTANCE.reinit(AppUser.class);
            }
        } else {
            throw new ValidationException(AppUser.class.getSimpleName(), validationErrors);
        }

        return wasCreated;
    }

    @Override
    public boolean delete(AppUser appUser) throws DatabaseInteractionException {
        boolean wasDeleted = false;

        List<Review> toTransfer = ReviewServiceImpl.INSTANCE.findAllForConcreteUserInReview(appUser.getId());
        if (toTransfer.size() > 0) {
            toTransfer = toTransfer.stream()
                    .peek(review -> review.setUserNickname("deleted user"))
                    .collect(Collectors.toList());
            if (ReviewServiceImpl.INSTANCE.transferInHistoryTable(toTransfer)) {
                wasDeleted = AppUserDaoImpl.getInstance().delete(appUser, ConnectionPool.INSTANCE.getAvailableConnection());
                RatingContext.INSTANCE.reinit(AppUser.class);
            }
        }

        return wasDeleted;
    }

    @Override
    public List<AppUser> findAll() throws DatabaseInteractionException {
        return AppUserDaoImpl.getInstance().findAll(ConnectionPool.INSTANCE.getAvailableConnection());
    }

    @Override
    public Optional<AppUser> findByEmail(String email) throws DatabaseInteractionException {
        Optional<AppUser> foundByEmail = findUserInCacheByEmail(email);
        if (foundByEmail.isEmpty()) {
            foundByEmail = AppUserDaoImpl.getInstance().findUserByEmail(email,
                    ConnectionPool.INSTANCE.getAvailableConnection());
        }

        return foundByEmail;
    }

    @Override
    public Optional<AppUser> findByNickname(String nickname) throws DatabaseInteractionException {
        Optional<AppUser> foundByNickname = findUserInCacheByNickname(nickname);
        if (foundByNickname.isEmpty()) {
            foundByNickname = AppUserDaoImpl.getInstance().findUserByNickname(nickname,
                    ConnectionPool.INSTANCE.getAvailableConnection());
        }

        return foundByNickname;
    }

    @Override
    public Optional<AppUser> findById(Long id) throws DatabaseInteractionException {
        Optional<AppUser> foundById = findUserInCacheById(id);
        if (foundById.isEmpty()) {
            foundById = AppUserDaoImpl.getInstance().findById(id,
                    ConnectionPool.INSTANCE.getAvailableConnection());
        }

        return foundById;
    }

    @Override
    public boolean checkPassword(AppUser user, String passwordToCheck) {
        return PasswordHasherUtil.checkPassword(passwordToCheck, user.getPassword());
    }

    @Override
    public Status updateUserStatus(AppUser user) throws DatabaseInteractionException {
        List<Integer> positiveMarks = AppUserDaoImpl.getInstance().getPositiveMarks(user,
                ConnectionPool.INSTANCE.getAvailableConnection());
        List<Integer> negativeMarks = AppUserDaoImpl.getInstance().getNegativeMarks(user,
                ConnectionPool.INSTANCE.getAvailableConnection());

        int numberOfPositiveMarks = positiveMarks.stream()
                .mapToInt((s) -> Integer.parseInt(String.valueOf(s)))
                .sum();
        int numberOfNegativeMarks = negativeMarks.stream()
                .mapToInt((s) -> Integer.parseInt(String.valueOf(s)))
                .sum();

        int totalResult = numberOfPositiveMarks - numberOfNegativeMarks;
        Status status;

        if (totalResult <= 25) {
            status = Status.NEWBIE;
        } else if (totalResult > 26 && totalResult <= 50) {
            status = Status.REVIEWER;
        } else if (totalResult > 51 && totalResult <= 75) {
            status = Status.EXPERIENCED_REVIEWER;
        } else if (totalResult > 76 && totalResult <= 100) {
            status = Status.ADVANCED_REVIEWER;
        } else {
            status = Status.EXPERT;
        }

        AppUserDaoImpl.getInstance().updateStatus(user, status, ConnectionPool.INSTANCE.getAvailableConnection());
        RatingContext.INSTANCE.reinit(AppUser.class);

        return null;
    }

    @Override
    public boolean addReviewedProduct(Long userId, Long productId) throws DatabaseInteractionException {
        return AppUserDaoImpl.getInstance().addReviewedProduct(
                userId, productId, ConnectionPool.INSTANCE.getAvailableConnection());
    }

    @Override
    public boolean addRatedReview(Long userId, Long reviewId, boolean isPositiveMark)
            throws DatabaseInteractionException {
        return AppUserDaoImpl.getInstance().addRatedReview(userId, reviewId, isPositiveMark,
                ConnectionPool.INSTANCE.getAvailableConnection());
    }

    @Override
    public boolean updateBan(Long userId, Boolean isBanned) throws DatabaseInteractionException {
        boolean wasUpdated = AppUserDaoImpl.getInstance().updateBan(
                userId, isBanned, ConnectionPool.INSTANCE.getAvailableConnection());
        if (wasUpdated) {
            RatingContext.INSTANCE.reinit(AppUser.class);
        }

        return wasUpdated;
    }

    @Override
    public AppUser updateByCriteria(AppUser appUser, AppUserCriteria appUserCriteria)
            throws ValidationException, DatabaseInteractionException {
        ValidationChain<AppUser> chain = ValidationChainFactory.INSTANCE.
                createValidationChain(appUser);
        List<String> validationErrors = chain.getValidationReport(appUser, ValidationType.UPDATE_OBJECT);

        if (validationErrors.size() == 0) {
            appUser = AppUserDaoImpl.getInstance().updateByCriteria(appUser, appUserCriteria,
                    ConnectionPool.INSTANCE.getAvailableConnection());
            RatingContext.INSTANCE.reinit(AppUser.class);
        } else {
            throw new ValidationException(AppUser.class.getSimpleName(), validationErrors);
        }

        return appUser;
    }

    private Optional<AppUser> findUserInCacheByEmail(String email) {
        return RatingContext.INSTANCE.retrieveList(AppUser.class).stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    private Optional<AppUser> findUserInCacheByNickname(String nickname) {
        return RatingContext.INSTANCE.retrieveList(AppUser.class).stream()
                .filter(user -> user.getNickname().equalsIgnoreCase(nickname))
                .findFirst();
    }

    private Optional<AppUser> findUserInCacheById(Long id) {
        return RatingContext.INSTANCE.retrieveList(AppUser.class).stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }


// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!



    @Override
    public boolean createUserReview(AppUser appUser, Review review) {
        boolean wasCreated = true;

//        if (!ReviewServiceImpl.INSTANCE.create(review)) {
//            wasCreated = false;
//        } else {
//            if (!AppUserDaoImpl.getInstance().addReviewedProduct(appUser.getId(), review.getCinemaProductId())) {
//                wasCreated = false;
//            }
//        }

        return wasCreated;
    }

}
