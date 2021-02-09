package com.epam.jwd.final_project.dao.impl;

import com.epam.jwd.final_project.criteria.AppUserCriteria;
import com.epam.jwd.final_project.dao.AppUserDao;
import com.epam.jwd.final_project.dao.ReleaseResources;
import com.epam.jwd.final_project.domain.*;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.pool.ConnectionPool;
import com.epam.jwd.final_project.util.DateConverterUtil;
import com.epam.jwd.final_project.util.PasswordHasherUtil;
import com.epam.jwd.final_project.util.SqlUpdateBuilderUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AppUserDaoImpl implements AppUserDao, ReleaseResources {

    private static AppUserDaoImpl INSTANCE;

    public static AppUserDaoImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AppUserDaoImpl();
        }
        return INSTANCE;
    }

    private static final String SQL_CHECK_NICKNAME_PRESENCE =
            "SELECT COUNT(*) AS number FROM app_user where nickname=?";
    private static final String SQL_CHECK_EMAIL_PRESENCE =
            "SELECT COUNT(*) AS number FROM app_user where email=?";
    private static final String SQL_INSERT_INTO_APP_USER =
            "INSERT INTO app_user(first_name, last_name, nickname, date_of_birth, " +
            "email, password, role_id) VALUE (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_GENERATED_ID =
            "SELECT id FROM app_user WHERE email=?";
    private static final String SQL_DELETE_USER =
            "DELETE FROM app_user WHERE id=?";
    private static final String SQL_SELECT_USERS =
            "SELECT * FROM app_user LEFT JOIN user_genre ug ON app_user.id = ug.user_id";
    private static final String SQL_SELECT_ALL_RATED_REVIEWS = "SELECT * FROM rated_reviews";
    private static final String SQL_SELECT_ALL_REVIEWED_PRODUCTS = "SELECT * FROM reviewed_products";
    private static final String SQL_SELECT_USER_BY_EMAIL =
            "SELECT * FROM app_user LEFT JOIN user_genre ug " +
            "ON app_user.id = ug.user_id WHERE email=?";
    private static final String SQL_SELECT_USER_RATED_REVIEWS =
            "SELECT * FROM rated_reviews WHERE user_id=?";
    private static final String SQL_SELECT_USER_REVIEWED_PRODUCTS =
            "SELECT * FROM reviewed_products WHERE user_id=?";
    private static final String SQL_SELECT_USER_BY_NICKNAME =
            "SELECT * FROM app_user LEFT JOIN user_genre ug " +
            "ON app_user.id = ug.user_id WHERE nickname=?";
    private static final String SQL_SELECT_USER_BY_ID =
            "SELECT * FROM app_user LEFT JOIN user_genre ug " +
            "ON app_user.id = ug.user_id WHERE id=?";
    private static final String SQL_UPDATE_GENRES =
            "INSERT INTO user_genre(user_id, genre_id) VALUE (?, ?)";
    private static final String SQL_DELETE_GENRES =
            "DELETE FROM user_genre WHERE user_id=?";
    private static final String SQL_REVIEW_POSITIVE =
            "SELECT review_positive_marks FROM review WHERE user_id=?";
    private static final String SQL_HISTORY_REVIEW_POSITIVE =
            "SELECT review_positive_marks FROM review_history WHERE user_id=?";
    private static final String SQL_REVIEW_NEGATIVE =
            "SELECT review_negative_marks FROM review WHERE user_id=?";
    private static final String SQL_HISTORY_REVIEW_NEGATIVE =
            "SELECT review_negative_marks FROM review_history WHERE user_id=?";
    private static final String SQL_UPDATE_STATUS =
            "UPDATE app_user SET status_id=? WHERE id=?";
    private static final String SQL_UPDATE_BAN =
            "UPDATE app_user SET is_banned=? WHERE id=?";
    private static final String SQL_INSERT_INTO_RATED_REVIEW =
            "INSERT INTO rated_reviews(user_id, review_id, is_positive_mark) VALUE (?, ?, ?)";
    private static final String SQL_INSERT_INTO_REVIEWED_PRODUCT =
            "INSERT INTO reviewed_products(user_id, cinema_product_id) VALUE (?, ?)";

    @Override
    public boolean create(AppUser appUser, Connection connection) throws DatabaseInteractionException {
        PreparedStatement statement = null;

        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SQL_INSERT_INTO_APP_USER);
            statement.setString(1, appUser.getFirstName());
            statement.setString(2, appUser.getLastName());
            statement.setString(3, appUser.getNickname());
            statement.setString(4, appUser.getDateOfBirth().toString());
            statement.setString(5, appUser.getEmail());
            statement.setString(6, PasswordHasherUtil.generatePasswordHash(appUser.getPassword()));
            statement.setLong(7, appUser.getRole().getId());
            statement.executeUpdate();

            Long generatedForUserId = getGeneratedId(connection, statement, appUser);
            if (appUser.getFavouriteGenres() != null &&
                    appUser.getFavouriteGenres().size() > 0) {
                updateUserGenres(generatedForUserId, appUser.getFavouriteGenres(),
                        connection.prepareStatement(SQL_UPDATE_GENRES));
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
    public boolean delete(AppUser appUser, Connection connection)
            throws DatabaseInteractionException {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_DELETE_USER);
            statement.setLong(1, appUser.getId());
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
    public List<AppUser> findAll(Connection connection) throws DatabaseInteractionException {
        List<AppUser> users;
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_SELECT_USERS);
            users = getUsersFromResultSet(statement.executeQuery());
            statement = connection.prepareStatement(SQL_SELECT_ALL_RATED_REVIEWS);
            addUsersRatedReviews(users, statement.executeQuery());
            statement = connection.prepareStatement(SQL_SELECT_ALL_REVIEWED_PRODUCTS);
            addUsersReviewedProducts(users, statement.executeQuery());
        } catch (SQLException e) {
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return users;
    }

    @Override
    public Optional<AppUser> findUserByEmail(String email, Connection connection)
            throws DatabaseInteractionException {
        AppUser user = null;
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_BY_EMAIL);
            statement.setString(1, email);
            List<AppUser> users = getUsersFromResultSet(statement.executeQuery());
            if (users.size() == 1) {
                user = users.get(0);
                statement = connection.prepareStatement(SQL_SELECT_USER_RATED_REVIEWS);
                statement.setString(1, email);
                addConcreteUserRatedReviews(user, statement.executeQuery());
                statement = connection.prepareStatement(SQL_SELECT_USER_REVIEWED_PRODUCTS);
                statement.setString(1, email);
                addConcreteUserReviewedProducts(user, statement.executeQuery());
            }
        } catch (SQLException e) {
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return Optional.ofNullable(user);
    }

    @Override
    public Optional<AppUser> findUserByNickname(String nickname, Connection connection)
            throws DatabaseInteractionException {
        AppUser user = null;
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_BY_NICKNAME);
            statement.setString(1, nickname);
            List<AppUser> users = getUsersFromResultSet(statement.executeQuery());
            if (users.size() == 1) {
                user = users.get(0);
                statement = connection.prepareStatement(SQL_SELECT_USER_RATED_REVIEWS);
                statement.setString(1, nickname);
                addConcreteUserRatedReviews(user, statement.executeQuery());
                statement = connection.prepareStatement(SQL_SELECT_USER_REVIEWED_PRODUCTS);
                statement.setString(1, nickname);
                addConcreteUserReviewedProducts(user, statement.executeQuery());
            }
        } catch (SQLException e) {
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return Optional.ofNullable(user);
    }

    @Override
    public Optional<AppUser> findById(Long id, Connection connection)
            throws DatabaseInteractionException {
        AppUser user = null;
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
            statement.setLong(1, id);
            List<AppUser> users = getUsersFromResultSet(statement.executeQuery());
            if (users.size() == 1) {
                user = users.get(0);
                statement = connection.prepareStatement(SQL_SELECT_USER_RATED_REVIEWS);
                statement.setLong(1, id);
                addConcreteUserRatedReviews(user, statement.executeQuery());
                statement = connection.prepareStatement(SQL_SELECT_USER_REVIEWED_PRODUCTS);
                statement.setLong(1, id);
                addConcreteUserReviewedProducts(user, statement.executeQuery());
            }
        } catch (SQLException e) {
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return Optional.ofNullable(user);
    }

    @Override
    public AppUser updateByCriteria(AppUser appUser, AppUserCriteria appUserCriteria,
                                    Connection connection) throws DatabaseInteractionException {
        final String updateSql = SqlUpdateBuilderUtil.buildSqlUserUpdate(appUserCriteria, appUser);
        PreparedStatement statement = null;

        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(updateSql);
            statement.executeUpdate();
            if (appUserCriteria.getFavouriteGenres() != null &&
                appUserCriteria.getFavouriteGenres().size() > 0) {
                statement = connection.prepareStatement(SQL_DELETE_GENRES);
                statement.setLong(1, appUser.getId());
                statement.executeUpdate();
                updateUserGenres(appUser.getId(), appUserCriteria.getFavouriteGenres(),
                        connection.prepareStatement(SQL_UPDATE_GENRES));
            }
            connection.commit();
        } catch (SQLException e) {
            rollback(connection);
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        appUser = findById(appUser.getId(), ConnectionPool.INSTANCE.getAvailableConnection()).get();

        return appUser;
    }

    @Override
    public List<Integer> getPositiveMarks(Long userId, Connection connection)
            throws DatabaseInteractionException {
        List<Integer> positiveMarks = new ArrayList<>();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_REVIEW_POSITIVE);
            statement.setLong(1, userId);
            positiveMarks.addAll(getMarksFromResultSet(statement.executeQuery(), "review_positive_marks"));
            statement = connection.prepareStatement(SQL_HISTORY_REVIEW_POSITIVE);
            statement.setLong(1, userId);
            positiveMarks.addAll(getMarksFromResultSet(statement.executeQuery(), "review_positive_marks"));
        } catch (SQLException e) {
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return positiveMarks;
    }

    @Override
    public List<Integer> getNegativeMarks(Long userId, Connection connection)
            throws DatabaseInteractionException {
        List<Integer> negativeMarks = new ArrayList<>();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_REVIEW_NEGATIVE);
            statement.setLong(1, userId);
            negativeMarks.addAll(getMarksFromResultSet(statement.executeQuery(), "review_negative_marks"));
            statement = connection.prepareStatement(SQL_HISTORY_REVIEW_NEGATIVE);
            statement.setLong(1, userId);
            negativeMarks.addAll(getMarksFromResultSet(statement.executeQuery(), "review_negative_marks"));
        } catch (SQLException e) {
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return negativeMarks;
    }

    @Override
    public boolean updateStatus(AppUser user, Status newStatus, Connection connection)
            throws DatabaseInteractionException {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_UPDATE_STATUS);
            statement.setLong(1, newStatus.getId());
            statement.setLong(2, user.getId());
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
    public boolean updateBan(Long userId, Boolean isBanned, Connection connection)
            throws DatabaseInteractionException {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_UPDATE_BAN);
            statement.setBoolean(1, isBanned);
            statement.setLong(2, userId);
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
    public boolean addReviewedProduct(Long userId, Long productId, Connection connection)
            throws DatabaseInteractionException {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_INSERT_INTO_REVIEWED_PRODUCT);
            statement.setLong(1, userId);
            statement.setLong(2, productId);
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
    public boolean addRatedReview(Long userId, Long reviewId, boolean isPositiveMark,
                                  Connection connection) throws DatabaseInteractionException {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_INSERT_INTO_RATED_REVIEW);
            statement.setLong(1, userId);
            statement.setLong(2, reviewId);
            statement.setBoolean(3, isPositiveMark);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }
    }

    private Long getGeneratedId(Connection connection, PreparedStatement statement,
                                AppUser appUser) throws SQLException {
        Long id = null;

        statement = connection.prepareStatement(SQL_SELECT_GENERATED_ID);
        statement.setString(1, appUser.getEmail());

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            id = resultSet.getLong("id");
        }

        return id;
    }

    @Override
    public boolean checkIfNickNameExists(String nickname, Connection connection)
            throws DatabaseInteractionException {
        boolean exsists;
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_CHECK_NICKNAME_PRESENCE);
            statement.setString(1, nickname);
            exsists = checkPresence(statement.executeQuery());
        } catch (SQLException e) {
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return exsists;
    }

    @Override
    public boolean checkIfEmailExists(String email, Connection connection)
            throws DatabaseInteractionException {
        boolean exsists;
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_CHECK_EMAIL_PRESENCE);
            statement.setString(1, email);
            exsists = checkPresence(statement.executeQuery());
        } catch (SQLException e) {
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return exsists;
    }

    private List<Integer> getMarksFromResultSet(ResultSet set, String columnName) throws SQLException {
        List<Integer> marks = new ArrayList<>();
        while (set.next()) {
            marks.add(set.getInt(columnName));
        }

        return marks;
    }

    private void updateUserGenres(Long id, List<Genre> newGenres,
                                  PreparedStatement statement) throws SQLException {
        for (int i = 0; i < newGenres.size(); i++) {
            statement.setLong(1, id);
            statement.setLong(2, newGenres.get(i).getId());
            statement.executeUpdate();
        }
    }

    private void addConcreteUserRatedReviews(AppUser user, ResultSet set) throws SQLException {
        while (set.next()){
            user.getRatedReviews().put(set.getLong("review_id"), set.getBoolean("is_positive_mark"));
        }
    }

    private void addConcreteUserReviewedProducts(AppUser user, ResultSet set) throws SQLException {
        while (set.next()){
            user.getReviewedProducts().add(set.getLong("cinema_product_id"));
        }
    }

    private void addUsersRatedReviews(List<AppUser> users, ResultSet set) throws SQLException {
        while (set.next()){
            Long userId = set.getLong("user_id");
            users.stream()
                    .filter(user -> user.getId().equals(userId))
                    .findFirst()
                    .get()
                    .getRatedReviews().put(set.getLong("review_id"), set.getBoolean("is_positive_mark"));
        }
    }

    private void addUsersReviewedProducts(List<AppUser> users, ResultSet set) throws SQLException {
        while (set.next()){
            Long userId = set.getLong("user_id");
            users.stream()
                    .filter(user -> user.getId().equals(userId))
                    .findFirst()
                    .get()
                    .getReviewedProducts().add(set.getLong("cinema_product_id"));
        }
    }

    private List<AppUser> getUsersFromResultSet(ResultSet set) throws SQLException {
        List<AppUser> users = new ArrayList<>();
        Long idToCompare = 0L;
        AppUser appUser = null;

        while (set.next()) {
            Long tempId = set.getLong("id");
            if (!idToCompare.equals(tempId)) {
                idToCompare = tempId;
                if (appUser != null) {
                    users.add(appUser);
                }
                appUser = new AppUser(idToCompare,
                        set.getString("first_name"),
                        set.getString("last_name"),
                        set.getString("nickname"),
                        DateConverterUtil.convertToLocalDate(set.getString("date_of_birth")),
                        set.getString("email"),
                        set.getString("password"),
                        Role.resolveRoleById(set.getLong("role_id")),
                        Status.resolveStatusById(set.getLong("status_id")),
                        set.getBoolean("is_banned"));
            }
            if (set.getLong("genre_id") != 0) {
                appUser.getFavouriteGenres().add(Genre.resolveGenreById(set.getLong("genre_id")));
            }
        }
        users.add(appUser);

        return users;
    }

    private boolean checkPresence(ResultSet set) throws SQLException {
        boolean exsists = false;
        while (set.next()) {
            if(set.getLong("number") > 0) {
                exsists = true;
            }
        }

        return exsists;
    }

}
