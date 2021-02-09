package com.epam.jwd.final_project.dao;

import com.epam.jwd.final_project.domain.Review;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import java.sql.Connection;
import java.util.List;

/**
 * An interface provides CRUD methods to create Review objects, update
 * and get information about them or their fields from the database.
 */
public interface ReviewDao {

    /**
     * Inserts the Review object into the database.
     *
     * @param review the element to add to the database
     * @param connection a {@link java.sql.Connection} to the database URL
     * @return true if the element was added to the database
     * @throws DatabaseInteractionException if an SQLException occurs at
     * the time of working with database
     */
    boolean create(Review review, Connection connection) throws DatabaseInteractionException;

    /**
     * Returns all Review objects for a specific movie/TV series,
     * that are present in the database.
     *
     * @param productId movie/TV series id
     * @param connection a {@link java.sql.Connection} to the database URL
     * @return {@link java.util.List} of elements retrieved from the database
     * @throws DatabaseInteractionException if an SQLException occurs at
     * the time of working with database
     */
    List<Review> findAllForConcreteProduct(Long productId, Connection connection)
            throws DatabaseInteractionException;

    /**
     * Returns all Review objects for a specific user,
     * that are present in the database.
     *
     * @param userId user id
     * @param connection a {@link java.sql.Connection} to the database URL
     * @return {@link java.util.List} of elements retrieved from the database
     * @throws DatabaseInteractionException if an SQLException occurs at
     * the time of working with database
     */
    List<Review> findAllForConcreteUser(Long userId, Connection connection)
            throws DatabaseInteractionException;

    /**
     * Returns all Review objects for a specific user not including
     * reviews from the table, which stores reviews for movies/TV series
     * that have been deleted from the database.
     *
     * @param userId movie/TV series id
     * @param connection a {@link java.sql.Connection} to the database URL
     * @return {@link java.util.List} of elements retrieved from the database
     * @throws DatabaseInteractionException if an SQLException occurs at
     * the time of working with database
     */
    List<Review> findAllForConcreteUserInReview(Long userId, Connection connection)
            throws DatabaseInteractionException;

    /**
     * Returns all Review objects for a specific movie/TV series
     * not including reviews from the table, which stores the history of reviews
     * written by users whose accounts have been deleted.
     *
     * @param productId movie/TV series id
     * @param connection a {@link java.sql.Connection} to the database URL
     * @return {@link java.util.List} of elements retrieved from the database
     * @throws DatabaseInteractionException if an SQLException occurs at
     * the time of working with database
     */
    List<Review> findAllForConcreteProductInReview(Long productId, Connection connection)
            throws DatabaseInteractionException;

    Review updateReviewMarks(Review review, Connection connection)
            throws DatabaseInteractionException;

    /**
     * Method adds all reviews from the given list to the table
     * that stores reviews written by users whose accounts have been deleted
     * and reviews for movies/TV series that have been deleted from the database.
     *
     * @param reviews Review objects to add to the table
     * @param connection a {@link java.sql.Connection} to the database URL
     * @return true if given Reviews were added to the table
     * @throws DatabaseInteractionException if an SQLException occurs at
     * the time of working with database
     */
    boolean transferInHistoryTable(List<Review> reviews, Connection connection)
            throws DatabaseInteractionException;

    /**
     * Returns the List of all users marks for a specific movie/TV series.
     *
     * @param productId movie/TV series id
     * @param connection a {@link java.sql.Connection} to the database URL
     * @return {@link java.util.List} of marks retrieved from the database
     * @throws DatabaseInteractionException if an SQLException occurs at
     * the time of working with database
     */
    List<Integer> getAllProductMarks(Long productId, Connection connection)
            throws DatabaseInteractionException;

}
