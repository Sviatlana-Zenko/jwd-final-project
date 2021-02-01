package com.epam.jwd.final_project.service;

import com.epam.jwd.final_project.domain.Review;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.exception.ValidationException;

import java.sql.Connection;
import java.util.List;

public interface ReviewService {

    boolean create(Review review) throws DatabaseInteractionException, ValidationException;

    List<Review> findAllForConcreteProduct(Long id) throws DatabaseInteractionException;

    List<Review> findAllForConcreteUser(Long id) throws DatabaseInteractionException;

    Review updateReviewMarks(Review review) throws DatabaseInteractionException;

    List<Review> findAllForConcreteUserInReview(Long id) throws DatabaseInteractionException;

    List<Review> findAllForConcreteProductInReview(Long id) throws DatabaseInteractionException;

    boolean transferInHistoryTable(List<Review> reviews) throws DatabaseInteractionException;

    List<Integer> getAllProductMarks(Long id) throws DatabaseInteractionException;

}
