package com.epam.jwd.final_project.service.impl;

import com.epam.jwd.final_project.dao.impl.ReviewDaoImpl;
import com.epam.jwd.final_project.domain.AppUser;
import com.epam.jwd.final_project.domain.Review;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.exception.ValidationException;
import com.epam.jwd.final_project.pool.ConnectionPool;
import com.epam.jwd.final_project.service.ReviewService;
import com.epam.jwd.final_project.validation.ValidationChain;
import com.epam.jwd.final_project.validation.ValidationChainFactory;
import com.epam.jwd.final_project.validation.ValidationType;
import java.util.List;

public class ReviewServiceImpl implements ReviewService {

    public static final ReviewServiceImpl INSTANCE = new ReviewServiceImpl();

    private ReviewServiceImpl() {
    }

    @Override
    public boolean create(Review review) throws ValidationException, DatabaseInteractionException {
        boolean wasCreated;
        ValidationChain<Review> chain = ValidationChainFactory.INSTANCE.
                createValidationChain(review);
        List<String> validationErrors = chain.getValidationReport(review, ValidationType.CREATE_OBJECT);

        if (validationErrors.size() == 0) {
            wasCreated = ReviewDaoImpl.getInstance().create(review,
                    ConnectionPool.INSTANCE.getAvailableConnection());
            CinemaProductServiceImpl.INSTANCE.updateProductRating(review.getCinemaProductId());
        } else {
            throw new ValidationException(AppUser.class.getSimpleName(), validationErrors);
        }

        return wasCreated;
    }

    @Override
    public List<Review> findAllForConcreteProduct(Long id) throws DatabaseInteractionException {
        return ReviewDaoImpl.getInstance().findAllForConcreteProduct(id,
                ConnectionPool.INSTANCE.getAvailableConnection());
    }

    @Override
    public List<Review> findAllForConcreteUser(Long id) throws DatabaseInteractionException {
        return ReviewDaoImpl.getInstance().findAllForConcreteUser(id, ConnectionPool.INSTANCE.getAvailableConnection());
    }

    @Override
    public Review updateReviewMarks(Review review) throws DatabaseInteractionException {
        return ReviewDaoImpl.getInstance().updateReviewMarks(
                review, ConnectionPool.INSTANCE.getAvailableConnection());
    }

    @Override
    public List<Review> findAllForConcreteUserInReview(Long id) throws DatabaseInteractionException {
        return ReviewDaoImpl.getInstance().findAllForConcreteUserInReview(
                id, ConnectionPool.INSTANCE.getAvailableConnection());
    }

    @Override
    public List<Review> findAllForConcreteProductInReview(Long id) throws DatabaseInteractionException {
        return ReviewDaoImpl.getInstance().findAllForConcreteProductInReview(
                id, ConnectionPool.INSTANCE.getAvailableConnection());
    }

    @Override
    public boolean transferInHistoryTable(List<Review> reviews) throws DatabaseInteractionException {
        return ReviewDaoImpl.getInstance().transferInHistoryTable(reviews, ConnectionPool.INSTANCE.getAvailableConnection());
    }

    @Override
    public List<Integer> getAllProductMarks(Long id) throws DatabaseInteractionException {
        return ReviewDaoImpl.getInstance().getAllProductMarks(id, ConnectionPool.INSTANCE.getAvailableConnection());
    }

//    @Override
//    public boolean create(Review review) {
////        return ReviewDaoImpl.getInstance().create(review);
//        return false;
//    }
//
//    @Override
//    public List<Review> findAll() {
//        return null;
//    }
//
//    @Override
//    public Optional<Review> findById(Long aLong) {
//        return Optional.empty();
//    }
//
//    @Override
//    public boolean delete(Review review) {
//        return false;
//    }
//
//    @Override
//    public Review updateByCriteria(Review review, Criteria criteria) {
//        return null;
//    }
//
//
//    @Override
//    public List<Review> getAllForParticularUser(Long userId) {
////        return ReviewDaoImpl.getInstance().findAllForParticularUser(userId);
//        return null;
//    }

}
