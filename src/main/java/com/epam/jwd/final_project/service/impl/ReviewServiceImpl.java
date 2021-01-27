package com.epam.jwd.final_project.service.impl;

import com.epam.jwd.final_project.criteria.Criteria;
import com.epam.jwd.final_project.dao.impl.ReviewDaoImpl;
import com.epam.jwd.final_project.domain.Review;
import com.epam.jwd.final_project.service.ReviewService;
import java.util.List;
import java.util.Optional;

public class ReviewServiceImpl implements ReviewService {

    public static final ReviewServiceImpl INSTANCE = new ReviewServiceImpl();

    private ReviewServiceImpl() {
    }

    @Override
    public boolean create(Review review) {
        return ReviewDaoImpl.getInstance().create(review);
    }

    @Override
    public List<Review> findAll() {
        return null;
    }

    @Override
    public Optional<Review> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Review review) {
        return false;
    }

    @Override
    public Review updateByCriteria(Review review, Criteria criteria) {
        return null;
    }


    @Override
    public List<Review> getAllForParticularUser(Long userId) {
        return ReviewDaoImpl.getInstance().findAllForParticularUser(userId);
    }

}
