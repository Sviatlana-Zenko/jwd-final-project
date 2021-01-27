package com.epam.jwd.final_project.dao;

import com.epam.jwd.final_project.criteria.Criteria;
import com.epam.jwd.final_project.domain.AppEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface AppEntityDao<T extends AppEntity, M extends Criteria> {

    boolean create(T t);

    List<T> findAll();

    Optional<T> findById(Long id);

    boolean delete(T t);

    T updateByCriteria(T t, M m);

    default void closeStatement(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    default void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    default void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
