package com.epam.jwd.final_project.dao.impl;

import com.epam.jwd.final_project.criteria.Criteria;
import com.epam.jwd.final_project.dao.ReviewDao;
import com.epam.jwd.final_project.domain.AppUser;
import com.epam.jwd.final_project.domain.Review;
import com.epam.jwd.final_project.pool.ConnectionPool;
import com.mysql.cj.jdbc.PreparedStatementWrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReviewDaoImpl implements ReviewDao {

    private static ReviewDaoImpl INSTANCE;

    public static ReviewDaoImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ReviewDaoImpl();
        }

        return INSTANCE;
    }

    //+++
    private static final String SQL_INSERT_INTO_REVIEW =
            "INSERT INTO review(cinema_product_id, user_id, cinema_product_mark, " +
                    "review_summary, review_text, has_spoilers) VALUE (?, ?, ?, ?, ?, ?)";

    //+++
    private static final String SQL_INSERT_INTO_REVIEW_HISTORY =
            "INSERT INTO review_history(review_id, cinema_product_id, user_id, title, nickname, " +
                    "cinema_product_mark, review_summary, review_text, review_positive_marks, " +
                            "review_negative_marks, has_spoilers) VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    //+++
    private static final String SQL_SELECT_REVIEWS =
            "SELECT review.*, app_user.nickname, cinema_product.title FROM review " +
                    "INNER JOIN app_user ON review.user_id=app_user.id " +
                            "INNER JOIN cinema_product ON review.cinema_product_id=cinema_product.id";

    //+++
    private static final String SQL_SELECT_HISTORY_REVIEWS = "SELECT * FROM review_history";

    //+++
    private static final String SQL_SELECT_REVIEW_BY_ID =
            "SELECT review.*, app_user.nickname, cinema_product.title FROM review " +
                    "INNER JOIN app_user ON review.user_id=app_user.id " +
                            "INNER JOIN cinema_product ON review.cinema_product_id=cinema_product.id " +
                                    "WHERE review_id=?";

    //+++
    private static final String SQL_SELECT_HISTORY_REVIEW_BY_ID = "SELECT * FROM review_history WHERE review_id=?";

    //+++
    private static final String SQL_DELETE_REVIEW = "DELETE FROM review WHERE review_id=?";

    //+++
    private static final String SQL_DELETE_HISTORY_REVIEW = "DELETE FROM review_history WHERE review_id=?";

    //+++
    private static final String SQL_UPDATE_REVIEW = "UPDATE review SET cinema_product_mark=?, " +
            "review_summary=?, review_text=?, has_spoilers=? WHERE review_id=?";

    //+++
    private static final String SQL_UPDATE_HISTORY_REVIEW = "UPDATE review_history SET cinema_product_mark=?, " +
            "review_summary=?, review_text=? WHERE review_id=?";
    private static final String SQL_UPDATE_MARKS = "UPDATE review SET review_positive_marks=?, " +
            "review_negative_marks=? WHERE review_id=?";
    private static final String SQL_UPDATE_HISTORY_MARKS = "UPDATE review_history SET review_positive_marks=?, " +
            "review_negative_marks=? WHERE review_id=?";

    @Override
    public boolean create(Review review) {
        boolean wasCreated = true;

        try(Connection connection = ConnectionPool.INSTANCE.getAvailableConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_INTO_REVIEW)) {
            statement.setLong(1, review.getCinemaProductId());
            statement.setLong(2, review.getUserId());
            statement.setByte(3, review.getCinemaProductMark());
            statement.setString(4, review.getReviewSummary());
            statement.setString(5, review.getReviewText());
            statement.setBoolean(6, review.getHasSpoilers());
            statement.executeUpdate();
        } catch (SQLException e) {
            wasCreated = false;
            e.printStackTrace();
            //log
        }

        return wasCreated;
    }

    @Override
    public boolean transferInHistoryTable(List<Review> reviews) {
        boolean wasCreated = true;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.INSTANCE.getAvailableConnection();
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
        } catch (SQLException e) {
            wasCreated = false;
            e.printStackTrace();
            //log
            rollback(connection);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return wasCreated;
    }

    @Override
    public List<Review> findAll() {
        List<Review> reviews = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.INSTANCE.getAvailableConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SQL_SELECT_REVIEWS);
            reviews.addAll(getAllReviews(statement.executeQuery()));
            statement = connection.prepareStatement(SQL_SELECT_HISTORY_REVIEWS);
            reviews.addAll(getAllReviews(statement.executeQuery()));
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            //log
            rollback(connection);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return reviews;
    }

    @Override
    public Optional<Review> findById(Long id) {
        Review review = null;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.INSTANCE.getAvailableConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SQL_SELECT_REVIEW_BY_ID);
            statement.setLong(1, id);
            review = getReview(statement.executeQuery());

            if (review == null) {
                statement = connection.prepareStatement(SQL_SELECT_HISTORY_REVIEW_BY_ID);
                statement.setLong(1, id);
                review = getReview(statement.executeQuery());
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            //log
            rollback(connection);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return Optional.ofNullable(review);
    }

    @Override
    public boolean delete(Review review) {
        boolean wasDeleted = true;

        try(Connection connection = ConnectionPool.INSTANCE.getAvailableConnection();
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_REVIEW)) {
            statement.setLong(1, review.getId());
            statement.execute();
        } catch (SQLException e) {
            wasDeleted = false;
            e.printStackTrace();
        }

        return wasDeleted;
    }

    @Override
    public Review updateByCriteria(Review review, Criteria criteria) {
        return null;
    }

    @Override
    public boolean fullDelete(Review review) {
        boolean wasDeleted = true;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.INSTANCE.getAvailableConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SQL_DELETE_REVIEW);
            statement.setLong(1, review.getId());
            statement.execute();
            statement = connection.prepareStatement(SQL_DELETE_HISTORY_REVIEW);
            statement.setLong(1, review.getId());
            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            wasDeleted = false;
            e.printStackTrace();
            //log
            rollback(connection);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return wasDeleted;
    }


    public Review update(Review review) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.INSTANCE.getAvailableConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SQL_UPDATE_REVIEW);
            updateReview(statement, review);
            statement = connection.prepareStatement(SQL_UPDATE_HISTORY_REVIEW);
            updateReview(statement, review);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            //log
            rollback(connection);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return review;
    }

    @Override
    public Review updateReviewMarks(Review review, Long userId, Boolean isPositive) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.INSTANCE.getAvailableConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SQL_UPDATE_MARKS);
            updateMarks(statement, review);
            statement = connection.prepareStatement(SQL_UPDATE_HISTORY_MARKS);
            updateMarks(statement, review);
            statement = connection.prepareStatement("INSERT INTO rated_reviews(user_id, review_id, " +
                    "is_positive_mark) VALUE (?, ?, ?)");
            statement.setLong(1, userId);
            statement.setLong(2, review.getId());
            statement.setBoolean(3, isPositive);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            //log
            rollback(connection);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return review;
    }

    @Override
    public List<Review> findAllForParticularCinemaProduct(Long productId) {
        List<Review> reviews = findAll().stream()
                .filter(review -> review.getCinemaProductId().equals(productId))
                .collect(Collectors.toList());

        return reviews;
    }

    @Override
    public List<Review> findAllForParticularUser(Long userId) {
        List<Review> reviews = findAll().stream()
                .filter(review -> review.getUserId().equals(userId))
                .collect(Collectors.toList());

        return reviews;
    }

    private List<Review> getAllReviews(ResultSet set) throws SQLException {
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

    private Review getReview(ResultSet set) throws SQLException {
        Review review = null;

        while (set.next()) {
            if (review == null) {
                review = new Review(set.getLong("review_id"),
                        set.getLong("cinema_product_id"),
                        set.getLong("user_id"),
                        set.getString("title"),
                        set.getString("nickname"),
                        set.getByte("cinema_product_mark"),
                        set.getString("review_summary"),
                        set.getString("review_text"),
                        set.getInt("review_positive_marks"),
                        set.getInt("review_negative_marks"),
                        set.getBoolean("has_spoilers"));
            }
        }

        return review;
    }

    private Review updateReview(PreparedStatement statement, Review review) throws SQLException {
        statement.setByte(1, review.getCinemaProductMark());
        statement.setString(2, review.getReviewSummary());
        statement.setString(3, review.getReviewText());
        statement.setLong(4, review.getId());
        statement.setBoolean(5, review.getHasSpoilers());
        statement.executeUpdate();

        return review;
    }

    private Review updateMarks(PreparedStatement statement, Review review) throws SQLException {
        statement.setInt(1, review.getReviewPositiveMarks());
        statement.setInt(2, review.getReviewNegativeMarks());
        statement.setLong(3, review.getId());
        statement.executeUpdate();

        return review;
    }

}
