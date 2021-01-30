package com.epam.jwd.final_project.dao;

import com.epam.jwd.final_project.criteria.Criteria;
import com.epam.jwd.final_project.domain.AppEntity;
import com.epam.jwd.final_project.domain.Review;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;

import java.sql.Connection;
import java.util.List;

public interface ReviewDao {

    boolean create(Review review, Connection connection) throws DatabaseInteractionException;

    List<Review> findAllForConcreteProduct(Long id, Connection connection)
            throws DatabaseInteractionException;

    List<Review> findAllForConcreteUserInReview(Long id, Connection connection)
            throws DatabaseInteractionException;

    Review updateReviewMarks(Review review, Connection connection)
            throws DatabaseInteractionException;

    boolean transferInHistoryTable(List<Review> reviews, Connection connection)
            throws DatabaseInteractionException;

    boolean fullDelete(Review review,  Connection connection)
            throws DatabaseInteractionException;



//    boolean fullDelete(Review review);
//
//    boolean transferInHistoryTable(List<Review> reviews);
//
//    List<Review> findAllForParticularCinemaProduct(Long productId);
//
//    List<Review> findAllForParticularUser(Long userId);

}
