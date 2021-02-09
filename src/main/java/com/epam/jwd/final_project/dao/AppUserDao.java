package com.epam.jwd.final_project.dao;

import com.epam.jwd.final_project.criteria.AppUserCriteria;
import com.epam.jwd.final_project.domain.AppUser;
import com.epam.jwd.final_project.domain.Status;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

/**
 * An interface extends the set of standard CRUD methods
 * described inside the {@link AppEntityDao} interface.
 * This methods were designed to work with {@link AppUser} objects
 * taken from the database.
 */
public interface AppUserDao extends AppEntityDao<AppUser, AppUserCriteria> {

    /**
     * Returns an Optional describing the element of
     * the database with a specific email address, or an empty
     * Optional if there is no such element in the database.
     *
     * @param email address to find
     * @param connection a {@link java.sql.Connection} to the database URL
     * @return an Optional describing found object, or an empty Optional
     * @throws DatabaseInteractionException if an SQLException occurs at
     * the time of working with database
     */
    Optional<AppUser> findUserByEmail(String email, Connection connection)
            throws DatabaseInteractionException;

    /**
     * Returns an Optional describing the element of
     * the database with a specific nickname, or an empty
     * Optional if there is no such element in the database.
     *
     * @param nickname user's nickname to find
     * @param connection a {@link java.sql.Connection} to the database URL
     * @return an Optional describing found object, or an empty Optional
     * @throws DatabaseInteractionException if an SQLException occurs at
     * the time of working with database
     */
    Optional<AppUser> findUserByNickname(String nickname, Connection connection)
            throws DatabaseInteractionException;

    /**
     * Returns List of all positive marks for a specific user.
     *
     * @param userId user's id number to find marks
     * @param connection a {@link java.sql.Connection} to the database URL
     * @return the List of marks
     * @throws DatabaseInteractionException if an SQLException occurs at
     * the time of working with database
     */
    List<Integer> getPositiveMarks(Long userId, Connection connection)
            throws DatabaseInteractionException;

    /**
     * Returns List of all negative marks for a specific user.
     *
     * @param userId user's id number to find marks
     * @param connection a {@link java.sql.Connection} to the database URL
     * @return the List of marks
     * @throws DatabaseInteractionException if an SQLException occurs at
     * the time of working with database
     */
    List<Integer> getNegativeMarks(Long userId, Connection connection)
            throws DatabaseInteractionException;

    /**
     * Updates status field for AppUser object with a specified id number.
     *
     * @param user concrete user to update
     * @param newStatus the new value for status field
     * @param connection a {@link java.sql.Connection} to the database URL
     * @return true if this field has been updated
     * @throws DatabaseInteractionException if an SQLException occurs at
     * the time of working with database
     */
    boolean updateStatus(AppUser user, Status newStatus, Connection connection)
            throws DatabaseInteractionException;

    /**
     * Adds new row to the database after each user's new review.
     *
     * @param userId user's id number
     * @param productId product id that was reviewed by user
     * @return true if new row has been added
     * @throws DatabaseInteractionException if an SQLException occurs at
     * the time of working with database
     */
    boolean addReviewedProduct(Long userId, Long productId, Connection connection)
            throws DatabaseInteractionException;

    //if a user has rated another user's review

    /**
     * Adds new row to the database after this user has rated
     * another user's review.
     *
     * @param userId user's id number
     * @param reviewId review that was rated by another user
     * @return true if new row has been added
     * @throws DatabaseInteractionException if an SQLException occurs at
     * the time of working with database
     */
    boolean addRatedReview(Long userId, Long reviewId, boolean isPositiveMark, Connection connection)
            throws DatabaseInteractionException;

    /**
     * Updates the block status field for AppUser object with a specified id number.
     *
     * @param userId user's id number to update
     * @param isBanned the new value for field
     * @param connection a {@link java.sql.Connection} to the database URL
     * @return true if this field has been updated
     * @throws DatabaseInteractionException if an SQLException occurs at
     * the time of working with database
     */
    boolean updateBan(Long userId, Boolean isBanned, Connection connection)
            throws DatabaseInteractionException;

    /**
     * Сhecks if the database contains the specified user's nickname.
     *
     * @param nickname user's nickname to find
     * @param connection a {@link java.sql.Connection} to the database URL
     * @return true if this nickname already exists
     * @throws DatabaseInteractionException if an SQLException occurs at
     * the time of working with database
     */
    boolean checkIfNickNameExists(String nickname, Connection connection)
            throws DatabaseInteractionException;

    /**
     * Сhecks if the database contains the specified user's email address.
     *
     * @param email user's email to find
     * @param connection a {@link java.sql.Connection} to the database URL
     * @return true if this email address already exists
     * @throws DatabaseInteractionException if an SQLException occurs at
     * the time of working with database
     */
    boolean checkIfEmailExists(String email, Connection connection)
            throws DatabaseInteractionException;

}
