package com.epam.jwd.final_project.dao;

import com.epam.jwd.final_project.criteria.Criteria;
import com.epam.jwd.final_project.domain.Review;

import java.util.List;

public interface ReviewDao extends AppEntityDao<Review, Criteria> {

    boolean fullDelete(Review review);

    boolean transferInHistoryTable(List<Review> reviews);

    Review updateReviewMarks(Review review, Long userId, Boolean isPositive);

    List<Review> findAllForParticularCinemaProduct(Long productId);

    List<Review> findAllForParticularUser(Long userId);

}
