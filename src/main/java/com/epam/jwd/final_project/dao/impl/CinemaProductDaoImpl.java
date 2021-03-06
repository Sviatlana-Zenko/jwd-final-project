package com.epam.jwd.final_project.dao.impl;

import com.epam.jwd.final_project.criteria.*;
import com.epam.jwd.final_project.dao.CinemaProductDao;
import com.epam.jwd.final_project.dao.ReleaseResources;
import com.epam.jwd.final_project.domain.*;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.util.DateConverterUtil;
import java.sql.*;
import java.util.*;

public class CinemaProductDaoImpl implements CinemaProductDao, ReleaseResources {

    private static CinemaProductDaoImpl INSTANCE;

    public static CinemaProductDaoImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CinemaProductDaoImpl();
        }
        return INSTANCE;
    }

    private static final String SQL_INSERT_INTO_CINEMA_PRODUCT =
            "INSERT INTO cinema_product(type_id, title, description, release_date, " +
            "running_time, country, age_rating, starring, poster_url) " +
            "VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_GENERATED_ID =
            "SELECT id FROM cinema_product WHERE title=? AND release_date=?";
    private static final String SQL_INSERT_INTO_MOVIE =
            "INSERT INTO movie(id, directed_by, produced_by, budget, box_office) " +
            "VALUE (?, ?, ?, ?, ?)";
    private static final String SQL_INSERT_INTO_TV_SERIES =
            "INSERT INTO tv_series(id, number_of_seasons, number_of_episodes, is_finished) " +
             "VALUE (?, ?, ?, ?)";
    private static final String SQL_UPDATE_GENRES =
            "INSERT INTO cinema_product_genre(cinema_product_id, genre_id) VALUE (?, ?)";
    private static final String SQL_SELECT_TYPE_BY_ID =
            "SELECT type_id FROM cinema_product WHERE id=?";
    private static final String SQL_SELECT_MOVIE_BY_ID =
            "SELECT * FROM cinema_product INNER JOIN movie m ON cinema_product.id = m.id " +
            "INNER JOIN cinema_product_genre cpg on cinema_product.id = cpg.cinema_product_id " +
            "WHERE cinema_product.id=?";
    private static final String SQL_SELECT_TV_SERIES_BY_ID =
            "SELECT * FROM cinema_product INNER JOIN tv_series ts on cinema_product.id = ts.id " +
            "INNER JOIN cinema_product_genre cpg on cinema_product.id = cpg.cinema_product_id " +
            "WHERE cinema_product.id=?";
    private static final String SQL_DELETE_CINEMA_PRODUCT =
            "DELETE FROM cinema_product WHERE id=?";
    private static final String SQL_SELECT_MOVIES =
            "SELECT * FROM cinema_product INNER JOIN movie m ON cinema_product.id = m.id " +
            "INNER JOIN cinema_product_genre cpg on cinema_product.id = cpg.cinema_product_id";
    private static final String SQL_SELECT_TV_SERIES =
            "SELECT * FROM cinema_product INNER JOIN tv_series ts on cinema_product.id = ts.id " +
            "INNER JOIN cinema_product_genre cpg on cinema_product.id = cpg.cinema_product_id";
    private static final String SQL_COUNT_ELEMENTS_BY_TYPE =
            "SELECT COUNT(*) AS number FROM cinema_product where type_id=?";
    private static final String SQL_SELECT_ID_BY_TITLE =
            "SELECT id FROM cinema_product WHERE title LIKE ";
    private static final String SQL_SELECT_BY_SEARCH_REQUEST =
            "SELECT * FROM cinema_product WHERE title LIKE ";
    private static final String SQL_SELECT_RECOMMENDATIONS =
            "SELECT id FROM cinema_product ORDER BY RAND() LIMIT ?";
    private static final String SQL_UPDATE_RATING =
            "UPDATE cinema_product SET current_rating=? WHERE id=?";

    @Override
    public boolean create(CinemaProduct product, Connection connection)
            throws DatabaseInteractionException {
        PreparedStatement statement = null;

        try {
            connection.setAutoCommit(false);
            insertIntoProducts(product, connection.prepareStatement(SQL_INSERT_INTO_CINEMA_PRODUCT));
            Long generatedId = getGeneratedId(connection, statement, product);
            product.setId(generatedId);

            List<Genre> genres = product.getGenres();
            if (genres != null && genres.size() > 0) {
                updateProductGenres(generatedId, product.getGenres(),
                        connection.prepareStatement(SQL_UPDATE_GENRES));
            }

            if (product.getType() == ProductType.MOVIE) {
                insertIntoMovies(product, connection.prepareStatement(SQL_INSERT_INTO_MOVIE));
            } else {
                insertIntoTvSeries(product, connection.prepareStatement(SQL_INSERT_INTO_TV_SERIES));
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
    public List<CinemaProduct> findAll(Connection connection) throws DatabaseInteractionException {
        List<CinemaProduct> products = new ArrayList<>();
        products.addAll(findAllMovies(connection));
        products.addAll(findAllTvSeries(connection));

        return products;
    }

    @Override
    public Optional<CinemaProduct> findById(Long id, Connection connection)
            throws DatabaseInteractionException {
        CinemaProduct product = null;
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_SELECT_TYPE_BY_ID);
            statement.setLong(1, id);
            ProductType type = ProductType.resolveTypeById(getType(statement.executeQuery()));
            if (type != null) {
                List<CinemaProduct> products;
                if (type == ProductType.MOVIE) {
                    statement = connection.prepareStatement(SQL_SELECT_MOVIE_BY_ID);
                    statement.setLong(1, id);
                    products = getMoviesFromResultSet(statement.executeQuery());
                } else {
                    statement = connection.prepareStatement(SQL_SELECT_TV_SERIES_BY_ID);
                    statement.setLong(1, id);
                    products = getTvSeriesFromResultSet(statement.executeQuery());
                }

                if (products.size() == 1) {
                    product = products.get(0);
                }
            }
        } catch (SQLException e) {
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return Optional.ofNullable(product);
    }


    @Override
    public boolean delete(CinemaProduct product, Connection connection)
            throws DatabaseInteractionException {
        boolean wasDeleted;
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_DELETE_CINEMA_PRODUCT);
            statement.setLong(1, product.getId());
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
    public List<Long> findIdByTitle(String title, Connection connection)
            throws DatabaseInteractionException {
        List<Long> idList = new ArrayList<>();
        title = "'%" + title + "%'";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_SELECT_ID_BY_TITLE + title);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                idList.add(set.getLong("id"));
            }
        } catch (SQLException e) {
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return idList;
    }

    @Override
    public List<CinemaProduct> findAllByType(ProductType type, Connection connection)
            throws DatabaseInteractionException {
        if (type == ProductType.MOVIE) {
            return findAllMovies(connection);
        } else {
            return findAllTvSeries(connection);
        }
    }

    @Override
    public List<CinemaProduct> findConcreteAmountByType(ProductType type, long startIndex,
            int number, String field, String dir, Connection connection) throws DatabaseInteractionException{
        List<CinemaProduct> products;
        PreparedStatement statement = null;

        try {
            if (type == ProductType.MOVIE) {
                statement = connection.prepareStatement("SELECT * FROM cinema_product " +
                        "INNER JOIN movie m ON cinema_product.id = m.id " +
                        "ORDER BY " + field + " " + dir +
                        " LIMIT ?, ?");
                statement.setLong(1, startIndex);
                statement.setInt(2, number);
                products = getMoviesFromResultSet(statement.executeQuery());
            } else {
                statement = connection.prepareStatement("SELECT * FROM cinema_product " +
                        "INNER JOIN tv_series ts on cinema_product.id = ts.id " +
                        "ORDER BY " + field + " " + dir +
                        " LIMIT ?, ?");
                statement.setLong(1, startIndex);
                statement.setInt(2, number);
                products = getTvSeriesFromResultSet(statement.executeQuery());
            }
        } catch (SQLException e) {
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return products;
    }

    @Override
    public int getNumberOfProducts(ProductType type, Connection connection)
            throws DatabaseInteractionException {
        int number = 0;
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_COUNT_ELEMENTS_BY_TYPE);
            statement.setLong(1, type.getId());
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                number = set.getInt("number");
            }
        } catch (SQLException e) {
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return number;
    }

    @Override
    public List<CinemaProduct> findBySearchRequest(String searchRequest, Connection connection)
            throws DatabaseInteractionException {
        List<CinemaProduct> products = new ArrayList<>();
        searchRequest = "'%" + searchRequest + "%'";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_SELECT_BY_SEARCH_REQUEST + searchRequest);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                products.add(new CinemaProduct(set.getLong("id"),
                        ProductType.resolveTypeById(set.getLong("type_id")),
                        set.getDouble("current_rating"),
                        set.getString("title"),
                        set.getString("description"),
                        DateConverterUtil.convertToLocalDate(set.getString("release_date")),
                        set.getInt("running_time"),
                        set.getString("country"),
                        set.getByte("age_rating"),
                        set.getString("starring"),
                        set.getString("poster_url")));
            }
        } catch (SQLException e) {
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return products;
    }

    @Override
    public List<CinemaProduct> findRecommendations(Connection connection)
            throws DatabaseInteractionException {
        List<CinemaProduct> recommendations = new ArrayList<>();
        List<Long> idNumbers = new ArrayList<>();
        final int numberOfProducts = 6;
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_SELECT_RECOMMENDATIONS);
            statement.setInt(1, numberOfProducts);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                idNumbers.add(set.getLong("id"));
            }

            for (int i = 0; i < idNumbers.size(); i++) {
                Optional<CinemaProduct> product = findById(idNumbers.get(i), connection);
                if (product.isPresent()) {
                    recommendations.add(product.get());
                }
            }
        } catch (SQLException e) {
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return recommendations;
    }

    @Override
    public boolean updateProductRating(Long productId, Double newRating, Connection connection)
            throws DatabaseInteractionException {
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_UPDATE_RATING);
            statement.setDouble(1, newRating);
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

    private void insertIntoTvSeries(CinemaProduct product, PreparedStatement statement) throws SQLException {
        statement.setLong(1, product.getId());
        statement.setInt(2, ((TvSeries) product).getNumberOfSeasons());
        statement.setInt(3, ((TvSeries) product).getNumberOfEpisodes());
        statement.setBoolean(4, ((TvSeries) product).getIsFinished());
        statement.executeUpdate();
    }

    private void insertIntoMovies(CinemaProduct product, PreparedStatement statement) throws SQLException {
        statement.setLong(1, product.getId());
        statement.setString(2, ((Movie) product).getDirectedBy());
        statement.setString(3, ((Movie) product).getProducedBy());
        statement.setInt(4, ((Movie) product).getBudget());
        statement.setInt(5, ((Movie) product).getBoxOffice());
        statement.executeUpdate();
    }

    private void updateProductGenres(Long id, List<Genre> genres,
                                     PreparedStatement statement) throws SQLException {
        for (int i = 0; i < genres.size(); i++) {
            statement.setLong(1, id);
            statement.setLong(2, genres.get(i).getId());
            statement.executeUpdate();
        }
    }

    private void insertIntoProducts(CinemaProduct product, PreparedStatement statement) throws SQLException {
        statement.setLong(1, product.getType().getId());
        statement.setString(2, product.getTitle());
        statement.setString(3, product.getDescription());
        statement.setString(4, product.getReleaseDate().toString());
        statement.setInt(5, product.getRunningTime());
        statement.setString(6, product.getCountry());
        statement.setByte(7, product.getAgeRating());
        statement.setString(8, product.getStarring());
        statement.setString(9, product.getPosterUrl());
        statement.executeUpdate();
    }

    private Long getGeneratedId(Connection connection, PreparedStatement statement,
                                CinemaProduct product) throws SQLException {
        Long id = null;

        statement = connection.prepareStatement(SQL_SELECT_GENERATED_ID);
        statement.setString(1, product.getTitle());
        statement.setString(2, product.getReleaseDate().toString());

        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            id = resultSet.getLong("id");
        }

        return id;
    }

    private Long getType(ResultSet set) throws SQLException {
        Long typeId = null;
        while (set.next()) {
            typeId = set.getLong("type_id");
        }

        return typeId;
    }

    private List<CinemaProduct> findAllMovies(Connection connection)
            throws DatabaseInteractionException{
        List<CinemaProduct> movies;
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_SELECT_MOVIES);
            movies = getMoviesFromResultSet(statement.executeQuery());
        } catch (SQLException e) {
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return movies;
    }

    private List<CinemaProduct> findAllTvSeries(Connection connection)
            throws DatabaseInteractionException{
        List<CinemaProduct> tvSeries;
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_SELECT_TV_SERIES);
            tvSeries = getTvSeriesFromResultSet(statement.executeQuery());
        } catch (SQLException e) {
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return tvSeries;
    }

    private List<CinemaProduct> getMoviesFromResultSet(ResultSet set) throws SQLException {
        List<CinemaProduct> movies = new ArrayList<>();
        Long idToCompare = 0L;
        CinemaProduct movie = null;
        boolean hasGenres = checkColumnInResultSet(set, "genre_id");

        while (set.next()) {
            Long tempId = set.getLong("id");
            if (!idToCompare.equals(tempId)) {
                idToCompare = tempId;
                if (movie != null) {
                    movies.add(movie);
                }
                movie = new Movie(idToCompare,
                        ProductType.MOVIE,
                        set.getDouble("current_rating"),
                        set.getString("title"),
                        set.getString("description"),
                        DateConverterUtil.convertToLocalDate(set.getString("release_date")),
                        set.getInt("running_time"),
                        set.getString("country"),
                        set.getByte("age_rating"),
                        set.getString("starring"),
                        set.getString("poster_url"),
                        set.getString("directed_by"),
                        set.getString("produced_by"),
                        set.getInt("budget"),
                        set.getInt("box_office"));
            }

            if (hasGenres) {
                if (set.getLong("genre_id") != 0) {
                    movie.getGenres().add(Genre.resolveGenreById(set.getLong("genre_id")));
                }
            }
        }
        movies.add(movie);

        return movies;
    }

    boolean checkColumnInResultSet(ResultSet set, String columnName) throws SQLException {
        boolean exists = false;
        ResultSetMetaData metaData = set.getMetaData();
        int numberOfColumns = metaData.getColumnCount();

        for (int i = 1; i < numberOfColumns + 1; i++) {
            String name = metaData.getColumnName(i);
            if (columnName.equals(name)) {
                exists = true;
            }
        }

        return exists;
    }

    private List<CinemaProduct> getTvSeriesFromResultSet(ResultSet set) throws SQLException {
        List<CinemaProduct> tvSeries = new ArrayList<>();
        Long idToCompare = 0L;
        CinemaProduct series = null;
        boolean hasGenres = checkColumnInResultSet(set, "genre_id");

        while (set.next()) {
            Long tempId = set.getLong("id");
            if (!idToCompare.equals(tempId)) {
                idToCompare = tempId;
                if (series != null) {
                    tvSeries.add(series);
                }
                series = new TvSeries(set.getLong("id"),
                        ProductType.TV_SERIES,
                        set.getDouble("current_rating"),
                        set.getString("title"),
                        set.getString("description"),
                        DateConverterUtil.convertToLocalDate(set.getString("release_date")),
                        set.getInt("running_time"),
                        set.getString("country"),
                        set.getByte("age_rating"),
                        set.getString("starring"),
                        set.getString("poster_url"),
                        set.getByte("number_of_seasons"),
                        set.getShort("number_of_episodes"),
                        set.getBoolean("is_finished"));
            }
            if (hasGenres) {
                if (set.getLong("genre_id") != 0) {
                    series.getGenres().add(Genre.resolveGenreById(set.getLong("genre_id")));
                }
            }
        }
        tvSeries.add(series);

        return tvSeries;
    }

    @Override
    public CinemaProduct updateByCriteria(CinemaProduct product, CinemaProductCriteria criteria,
                                          Connection connection) throws DatabaseInteractionException {
        return null;
    }

}
