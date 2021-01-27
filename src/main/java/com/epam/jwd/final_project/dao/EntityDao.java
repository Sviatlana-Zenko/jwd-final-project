package com.epam.jwd.final_project.dao;

import com.epam.jwd.final_project.criteria.Criteria;
import com.epam.jwd.final_project.domain.AppEntity;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * An interface provides CRUD methods to work with database and
 * some default methods to release database and JDBC resources.
 * @param <T> the type of elements in this interface
 * @param <K> the type of criteria to update elements
 */
public interface EntityDao<T extends AppEntity, K extends Criteria<T>> {

    Logger LOGGER = LoggerFactory.getLogger(EntityDao.class);

    /**
     * Inserts the specified element into the database.
     * @param t the element to add to the database
     * @param connection a {@link java.sql.Connection} to the database URL
     * @return true if the element was added to the database, else false
     * @throws DatabaseInteractionException if an SQLException occurs
     */
    boolean create(T t, Connection connection) throws DatabaseInteractionException;

    /**
     * Returns all elements of the specified type, which are present in the database.
     * @param connection a {@link java.sql.Connection} to the database URL
     * @return {@link java.util.List} of elements retrieved from the database
     * @throws DatabaseInteractionException if an SQLException occurs
     */
    List<T> findAll(Connection connection) throws DatabaseInteractionException;

    /**
     * Returns an {@link java.util.Optional} describing the element of
     * the database with a specific id number, or an empty
     * Optional if there is no such element in the database.
     * @param id id of the element to find
     * @param connection a {@link java.sql.Connection} to the database URL
     * @return an Optional describing some element, or an empty Optional
     * @throws DatabaseInteractionException if an SQLException occurs
     */
    Optional<T> findById(Long id, Connection connection) throws DatabaseInteractionException;

    /**
     * Removes the specified element from the database.
     * @param t the element to remove from the database
     * @param connection a {@link java.sql.Connection} to the database URL
     * @return true if the element was removed, else false
     * @throws DatabaseInteractionException if an SQLException occurs
     */
    boolean delete(T t, Connection connection) throws DatabaseInteractionException;

    /**
     * Updates the specified element according to the given criteria.
     * @param t the element to update
     * @param k the criteria that contains information about how the element should be updated
     * @param connection a {@link java.sql.Connection} to the database URL
     * @return updated element
     * @throws DatabaseInteractionException if an SQLException occurs
     */
    T updateByCriteria(T t, K k, Connection connection) throws DatabaseInteractionException;

    /**
     * Releases a {@link java.sql.PreparedStatement} object's database and JDBC resources.
     * @param statement a PreparedStatement object to release resources
     */
    default void closeStatement(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.error("Closing statement error", e);
            }
        }
    }

    /**
     * Releases a {@link java.sql.Connection} object's database and JDBC resources.
     * @param connection a Connection object to release resources
     */
    default void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("Closing connection error", e);
            }
        }
    }

    /**
     * Undoes all changes made in the transaction and releases database
     * locks currently held by specified {@link java.sql.Connection} object.
     * @param connection a Connection object
     */
    default void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.error("Rolling back transaction error", e);
        }
    }

}
