package com.epam.jwd.final_project.dao.impl;

import com.epam.jwd.final_project.criteria.AppUserCriteria;
import com.epam.jwd.final_project.criteria.Criteria;
import com.epam.jwd.final_project.dao.AppEntityDao;
import com.epam.jwd.final_project.dao.GenreDao;
import com.epam.jwd.final_project.domain.AppUser;
import com.epam.jwd.final_project.domain.Genre;
import com.epam.jwd.final_project.domain.Review;
import com.epam.jwd.final_project.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenreDaoImpl implements GenreDao, AppEntityDao<Genre, Criteria> {

    private static final String SQL_SELECT_USER_GENRES =
            "SELECT * FROM user_genre WHERE user_id=?";
    private static final String SQL_SELECT_CINEMA_PRODUCT_GENRES =
            "SELECT * FROM cinema_product_genre WHERE cinema_product_id=?";
    private static final String SQL_INSERT_USER_GENRES =
            "INSERT INTO user_genre(user_id, genre_id) VALUE (?, ?)";
    private static final String SQL_INSERT_CINEMA_PRODUCT_GENRES =
            "INSERT INTO cinema_product_genre(cinema_product_id, genre_id) VALUE (?, ?)";

    private static GenreDaoImpl INSTANCE;

    public static GenreDaoImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GenreDaoImpl();
        }

        return INSTANCE;
    }






    public boolean createOneGenre(Long id, Genre genre) {
        boolean wasCreated = true;

        try(Connection connection = ConnectionPool.INSTANCE.getAvailableConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user_genre(user_id, genre_id) VALUE (?, ?)")) {
            preparedStatement.setLong(1, id);
            preparedStatement.setLong(2, genre.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            wasCreated = false;
            e.printStackTrace();
        }

        return wasCreated;
    }

    @Override
    public boolean updateUserGenres(List<Genre> newGenres, Connection connection) {
        return false;
    }

    @Override
    public List<Genre> findUserGenres(Long userId) {
        return getGenres(SQL_SELECT_USER_GENRES, userId);
    }

    @Override
    public List<Genre> findCinemaProductGenres(Long productId) {
        return getGenres(SQL_SELECT_CINEMA_PRODUCT_GENRES, productId);
    }

    @Override
    public boolean createUserGenres(Long userId, List<Genre> genres) {
        return setGenres(SQL_INSERT_USER_GENRES, userId, genres);
    }

    @Override
    public boolean createCinemaProductGenres(Long productId, List<Genre> genres) {
        return setGenres(SQL_INSERT_CINEMA_PRODUCT_GENRES, productId, genres);
    }


    private boolean setGenres(String sql, Long id, List<Genre> genres) {
        int i = 0;
        boolean wasAdded = true;

//        Connection connection = null;
//        PreparedStatement statement = null;
//
//        try {
//            connection = ConnectionPool.INSTANCE.getAvailableConnection();
//            connection.setAutoCommit(false);
//            statement = connection.prepareStatement(sql);
//            statement.setLong(1, id);
//            statement.setLong(2, genres.get(i).getId());
//            statement.executeUpdate();
//
////            i++;
////            statement = connection.prepareStatement(sql);
////            statement.setLong(1, id);
////            statement.setLong(2, genres.get(i).getId());
////            statement.executeUpdate();
////            i++;
//
////            for (int i = 0; i < genres.size(); i++) {
////                statement = connection.prepareStatement(sql);
////                statement.setLong(1, id);
////                statement.setLong(2, genres.get(i).getId());
////                statement.executeUpdate(); //++
////            }
//
//            connection.commit();
//        } catch (SQLException e) {
//            wasAdded = false;
//            e.printStackTrace();
//            rollback(connection);
//        } finally {
//            closeStatement(statement);
//            closeConnection(connection);
//        }

        try(Connection connection = ConnectionPool.INSTANCE.getAvailableConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.setLong(2, genres.get(i).getId());
            statement.executeUpdate(); //++
        } catch (SQLException e) {
            wasAdded = false;
            e.printStackTrace();
        }

        return wasAdded;
    }

    private List<Genre> getGenres(String sql, Long id) {
        List<Genre> genres = new ArrayList<>();

        try(Connection connection = ConnectionPool.INSTANCE.getAvailableConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                genres.add(Genre.resolveGenreById(set.getLong("genre_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return genres;
    }

    @Override
    public boolean create(Genre genre) {
        boolean wasCreated = true;

        try(Connection connection = ConnectionPool.INSTANCE.getAvailableConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user_genre(user_id, genre_id) VALUE (?, ?)")) {
            preparedStatement.setString(1, genre.toString());   //сделать метод getName()????
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            wasCreated = false;
            e.printStackTrace();
        }

        return wasCreated;
    }

    @Override
    public List<Genre> findAll() {
        return null;
    }

    @Override
    public Optional<Genre> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Genre genre) {
        return false;
    }

    @Override
    public Genre updateByCriteria(Genre genre, Criteria criteria) {
        return null;
    }

//    @Override
//    public Genre update(Genre genre) {
//        return null;
//    }
}
