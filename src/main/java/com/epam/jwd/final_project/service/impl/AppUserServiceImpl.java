package com.epam.jwd.final_project.service.impl;

import com.epam.jwd.final_project.context.impl.RatingContext;
import com.epam.jwd.final_project.criteria.AppUserCriteria;
import com.epam.jwd.final_project.criteria.Criteria;
import com.epam.jwd.final_project.dao.impl.AppUserDaoImpl;
import com.epam.jwd.final_project.domain.AppUser;
import com.epam.jwd.final_project.domain.Review;
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

    private Optional<AppUser> findUserInCacheByEmail(String email){
        return RatingContext.INSTANCE.retrieveList(AppUser.class).stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    private Optional<AppUser> findUserInCacheById(Long id){
        return RatingContext.INSTANCE.retrieveList(AppUser.class).stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }



// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!



    @Override
    public boolean delete(AppUser appUser) {
        return false;
    }


    @Override
    public boolean createUserReview(AppUser appUser, Review review) {
        boolean wasCreated = true;

        if (!ReviewServiceImpl.INSTANCE.create(review)) {
            wasCreated = false;
        } else {
            if (!AppUserDaoImpl.getInstance().addReviewedProduct(appUser.getId(), review.getCinemaProductId())) {
                wasCreated = false;
            }
        }

        return wasCreated;
    }

    @Override
    public AppUser updateByCriteria(Criteria<AppUser> criteria, AppUser appUser) {
//        return AppUserDaoImpl.getInstance().updateByCriteria(criteria, appUser);
        return null;
    }
}
