package com.epam.jwd.final_project.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ReleaseResources {

    /**
     * A constant that is the Logger class object to log information about
     * exceptions that may arise during releasing database and JDBC resources.
     */
    Logger LOGGER = LoggerFactory.getLogger(ReleaseResources.class);

    /**
     * Releases a PreparedStatement object's database and JDBC resources.
     *
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
     * Releases a Connection object's database and JDBC resources.
     *
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
     *
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
