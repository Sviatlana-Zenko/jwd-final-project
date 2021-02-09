package com.epam.jwd.final_project.dao.impl;

import com.epam.jwd.final_project.dao.ReleaseResources;
import com.epam.jwd.final_project.dao.ReviewDao;
import com.epam.jwd.final_project.domain.*;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ReviewDaoImpl implements ReviewDao, ReleaseResources {

    private static ReviewDaoImpl INSTANCE;

    public static ReviewDaoImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ReviewDaoImpl();
        }
        return INSTANCE;
    }

    private static final String SQL_INSERT_INTO_REVIEW =
            "INSERT INTO review(cinema_product_id, user_id, cinema_product_mark, " +
            "review_summary, review_text, has_spoilers) VALUE (?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_PROD_HIST_REVIEWS =
            "SELECT * FROM review_history WHERE cinema_product_id=?";
    private static final String SQL_SELECT_USER_HIST_REVIEWS =
            "SELECT * FROM review_history WHERE user_id=?";
    private static final String SQL_SELECT_USER_REVIEWS =
            "SELECT review.*, cinema_product.title, app_user.nickname FROM review " +
            "INNER JOIN cinema_product ON review.cinema_product_id = cinema_product.id " +
            "INNER JOIN app_user ON review.user_id = app_user.id " +
            "WHERE app_user.id=?";
    private static final String SQL_SELECT_PRODUCT_REVIEWS =
            "SELECT review.*, cinema_product.title, app_user.nickname FROM review " +
            "INNER JOIN cinema_product ON review.cinema_product_id = cinema_product.id " +
            "INNER JOIN app_user ON review.user_id = app_user.id " +
            "WHERE cinema_product.id=?";
    private static final String SQL_CHECK_REVIEW =
            "SELECT COUNT(*) AS number FROM review where review_id=?";
    private static final String SQL_UPDATE_MARKS =
            "UPDATE review SET review_positive_marks=?, " +
            "review_negative_marks=? WHERE review_id=?";
    private static final String SQL_UPDATE_HISTORY_MARKS =
            "UPDATE review_history SET review_positive_marks=?, " +
            "review_negative_marks=? WHERE review_id=?";
    private static final String SQL_INSERT_INTO_REVIEW_HISTORY =
            "INSERT INTO review_history(review_id, cinema_product_id, user_id, title, nickname, " +
            "cinema_product_mark, review_summary, review_text, review_positive_marks, " +
            "review_negative_marks, has_spoilers) VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_REVIEW_MARKS =
            "SELECT cinema_product_mark FROM review WHERE cinema_product_id=?";
    private static final String SQL_SELECT_REVIEW_HISTORY_MARKS =
            "SELECT cinema_product_mark FROM review_history WHERE cinema_product_id=?";

    @Override
    public boolean create(Review review, Connection connection) throws DatabaseInteractionException {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_INSERT_INTO_REVIEW);
            statement.setLong(1, review.getCinemaProductId());
            statement.setLong(2, review.getUserId());
            statement.setByte(3, review.getCinemaProductMark());
            statement.setString(4, review.getReviewSummary());
            statement.setString(5, review.getReviewText());
            statement.setBoolean(6, review.getHasSpoilers());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    @Override
    public List<Review> findAllForConcreteProduct(Long productId, Connection connection)
            throws DatabaseInteractionException {
        List<Review> reviews = new ArrayList<>();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_SELECT_PRODUCT_REVIEWS);
            statement.setLong(1, productId);
            reviews.addAll(getReviewsFromResultSet(statement.executeQuery()));
            statement = connection.prepareStatement(SQL_SELECT_PROD_HIST_REVIEWS);
            statement.setLong(1, productId);
            reviews.addAll(getReviewsFromResultSet(statement.executeQuery()));
        } catch (SQLException e) {
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        reviews.sort(Comparator.comparing(Review::getId).reversed());

        return reviews;
    }

    @Override
    public List<Review> findAllForConcreteUser(Long userId, Connection connection)
            throws DatabaseInteractionException {
        List<Review> reviews = new ArrayList<>();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_REVIEWS);
            statement.setLong(1, userId);
            reviews.addAll(getReviewsFromResultSet(statement.executeQuery()));
            statement = connection.prepareStatement(SQL_SELECT_USER_HIST_REVIEWS);
            statement.setLong(1, userId);
            reviews.addAll(getReviewsFromResultSet(statement.executeQuery()));
        } catch (SQLException e) {
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        reviews.sort(Comparator.comparing(Review::getId).reversed());

        return reviews;
    }

    @Override
    public List<Review> findAllForConcreteUserInReview(Long userId, Connection connection)
            throws DatabaseInteractionException {
        List<Review> reviews = new ArrayList<>();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_REVIEWS);
            statement.setLong(1, userId);
            reviews.addAll(getReviewsFromResultSet(statement.executeQuery()));
        } catch (SQLException e) {
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        reviews.sort(Comparator.comparing(Review::getId).reversed());

        return reviews;
    }

    @Override
    public List<Review> findAllForConcreteProductInReview(Long productId, Connection connection)
            throws DatabaseInteractionException {
        List<Review> reviews = new ArrayList<>();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_SELECT_PRODUCT_REVIEWS);
            statement.setLong(1, productId);
            reviews.addAll(getReviewsFromResultSet(statement.executeQuery()));
        } catch (SQLException e) {
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return reviews;
    }

    @Override
    public Review updateReviewMarks(Review review, Connection connection)
            throws DatabaseInteractionException {
        PreparedStatement statement = null;

        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SQL_CHECK_REVIEW);
            statement.setLong(1, review.getId());
            if (checkIfContainsReview(statement.executeQuery())) {
                statement = connection.prepareStatement(SQL_UPDATE_MARKS);
            } else {
                statement = connection.prepareStatement(SQL_UPDATE_HISTORY_MARKS);
            }
            statement.setInt(1, review.getReviewPositiveMarks());
            statement.setInt(2, review.getReviewNegativeMarks());
            statement.setLong(3, review.getId());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return null;
    }

    @Override
    public boolean transferInHistoryTable(List<Review> reviews, Connection connection)
            throws DatabaseInteractionException {
        PreparedStatement statement = null;

        try {
            connection.setAutoCommit(false);
            for (int i = 0; i < reviews.size(); i++) {
                statement = connection.prepareStatement(SQL_INSERT_INTO_REVIEW_HISTORY);
                statement.setLong(1, reviews.get(i).getId());
                statement.setLong(2, reviews.get(i).getCinemaProductId());
                statement.setLong(3, reviews.get(i).getUserId());
                statement.setString(4, reviews.get(i).getProductTitle());
                statement.setString(5, reviews.get(i).getUserNickname());
                statement.setByte(6, reviews.get(i).getCinemaProductMark());
                statement.setString(7, reviews.get(i).getReviewSummary());
                statement.setString(8, reviews.get(i).getReviewText());
                statement.setInt(9, reviews.get(i).getReviewPositiveMarks());
                statement.setInt(10, reviews.get(i).getReviewNegativeMarks());
                statement.setBoolean(11, reviews.get(i).getHasSpoilers());
                statement.executeUpdate();
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            rollback(connection);
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

    }

    @Override
    public List<Integer> getAllProductMarks(Long id, Connection connection)
            throws DatabaseInteractionException {
        List<Integer> marks = new ArrayList<>();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_SELECT_REVIEW_MARKS);
            statement.setLong(1, id);
            marks.addAll(getMarksFromResultSet(statement.executeQuery()));
            statement = connection.prepareStatement(SQL_SELECT_REVIEW_HISTORY_MARKS);
            statement.setLong(1, id);
            marks.addAll(getMarksFromResultSet(statement.executeQuery()));
        } catch (SQLException e) {
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return marks;
    }

    private boolean checkIfContainsReview(ResultSet set) throws SQLException {
        int number = 0;
        while (set.next()) {
            number = set.getInt("number");
        }

        return number == 1 ? true : false;
    }

    private List<Review> getReviewsFromResultSet(ResultSet set) throws SQLException {
        List<Review> reviews = new ArrayList<>();
        while (set.next()) {
            reviews.add(new Review(set.getLong("review_id"),
                    set.getLong("cinema_product_id"),
                    set.getLong("user_id"),
                    set.getString("title"),
                    set.getString("nickname"),
                    set.getByte("cinema_product_mark"),
                    set.getString("review_summary"),
                    set.getString("review_text"),
                    set.getInt("review_positive_marks"),
                    set.getInt("review_negative_marks"),
                    set.getBoolean("has_spoilers")));
        }

        return reviews;
    }

    private List<Integer> getMarksFromResultSet(ResultSet set) throws SQLException {
        List<Integer> marks = new ArrayList<>();
        while (set.next()) {
            marks.add(set.getInt("cinema_product_mark"));
        }

        return marks;
    }

}
