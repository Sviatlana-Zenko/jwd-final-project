package com.epam.jwd.final_project.dao.impl;

import com.epam.jwd.final_project.criteria.*;
import com.epam.jwd.final_project.dao.CinemaProductDao;
import com.epam.jwd.final_project.dao.ReleaseResources;
import com.epam.jwd.final_project.domain.*;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.pool.ConnectionPool;
import com.epam.jwd.final_project.util.DateConverterUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

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
            "INSERT INTO tv_series(id, number_of_seasons, number_of_episodes, " +
                    "is_finished) VALUE (?, ?, ?, ?)";
    @Override
    public boolean create(CinemaProduct product, Connection connection) throws DatabaseInteractionException {
        boolean wasCreated = false;
        PreparedStatement statement = null;

        try {
            connection.setAutoCommit(false);

            insertIntoProducts(product, connection.prepareStatement(SQL_INSERT_INTO_CINEMA_PRODUCT));
            Long generatedId = getGeneratedId(connection, statement, product);
            product.setId(generatedId);
            List<Genre> genres = product.getGenres();
            if (product.getGenres() != null &&
                    product.getGenres().size() > 0) {
                updateProductGenres(generatedId, product.getGenres(),
                        connection.prepareStatement(SQL_UPDATE_GENRES));
            }

            if (product.getType() == ProductType.MOVIE) {
                insertIntoMovies(product, connection.prepareStatement(SQL_INSERT_INTO_MOVIE));
            } else {
                insertIntoTvSeries(product, connection.prepareStatement(SQL_INSERT_INTO_TV_SERIES));
            }
            connection.commit();
            wasCreated = false;
        } catch (SQLException e) {
            rollback(connection);
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return wasCreated;
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

    private static final String SQL_UPDATE_GENRES =
            "INSERT INTO cinema_product_genre(cinema_product_id, genre_id) VALUE (?, ?)";
    public void updateProductGenres(Long id, List<Genre> genres,
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

    @Override
    public List<CinemaProduct> findAll(Connection connection) throws DatabaseInteractionException {
        List<CinemaProduct> products = new ArrayList<>();
        products.addAll(findAllMovies(connection));
        products.addAll(findAllTvSeries(connection));

        return products;
    }

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
    @Override
    public Optional<CinemaProduct> findById(Long id, Connection connection) throws DatabaseInteractionException {
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

    private Long getType(ResultSet set) throws SQLException {
        Long typeId = null;
        while (set.next()) {
            typeId = set.getLong("type_id");
        }

        return typeId;
    }

    @Override
    public boolean delete(CinemaProduct product, Connection connection) throws DatabaseInteractionException {
        return false;
    }

    @Override
    public CinemaProduct updateByCriteria(CinemaProduct product, CinemaProductCriteria criteria, Connection connection) throws DatabaseInteractionException {
        return null;
    }

    @Override
    public Long findIdByTitle(String title, Connection connection) throws DatabaseInteractionException {
        Long id = null;
        title = "'%" + title + "%'";
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_SELECT_ID_BY_TITLE + title);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                id = set.getLong("id");
            }
        } catch (SQLException e) {
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }


        return id;
    }

    @Override
    public List<CinemaProduct> findAllByType(ProductType productType, Connection connection)
            throws DatabaseInteractionException {
        if (productType == ProductType.MOVIE) {
            return findAllMovies(connection);
        } else {
            return findAllTvSeries(connection);
        }
    }

    private static final String SQL_SELECT_MOVIES =
        "SELECT * FROM cinema_product INNER JOIN movie m ON cinema_product.id = m.id " +
                "INNER JOIN cinema_product_genre cpg on cinema_product.id = cpg.cinema_product_id";
    private List<CinemaProduct> findAllMovies(Connection connection)  throws DatabaseInteractionException{
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

    private static final String SQL_SELECT_TV_SERIES =
            "SELECT * FROM cinema_product INNER JOIN tv_series ts on cinema_product.id = ts.id " +
                    "INNER JOIN cinema_product_genre cpg on cinema_product.id = cpg.cinema_product_id";
    private List<CinemaProduct> findAllTvSeries(Connection connection)  throws DatabaseInteractionException{
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
    public CinemaProduct updateByCriteria(CinemaProductCriteria criteria, CinemaProduct cinemaProduct) {
        return null;
    }


    private static final String SQL_SELECT_AMOUNT_OF_MOVIES =
        "SELECT * FROM cinema_product INNER JOIN movie m ON cinema_product.id = m.id " +
                        "ORDER BY current_rating DESC LIMIT ?, ?";
    private static final String SQL_SELECT_AMOUNT_OF_TV_SERIES =
            "SELECT * FROM cinema_product INNER JOIN tv_series ts on cinema_product.id = ts.id " +
                    "ORDER BY current_rating DESC LIMIT ?, ?";
    @Override
    public List<CinemaProduct> findConcreteAmountByType(ProductType type, long startIndex,
            int number, Connection connection) throws DatabaseInteractionException{
        List<CinemaProduct> products;
        PreparedStatement statement = null;

        try {
            if (type == ProductType.MOVIE) {
                statement = connection.prepareStatement(SQL_SELECT_AMOUNT_OF_MOVIES);
                statement.setLong(1, startIndex);
                statement.setInt(2, number);
                products = getMoviesFromResultSet(statement.executeQuery());
            } else {
                statement = connection.prepareStatement(SQL_SELECT_AMOUNT_OF_TV_SERIES);
                statement.setLong(1, startIndex);
                statement.setInt(2, number);
                products = getTvSeriesFromResultSet(statement.executeQuery());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return products;
    }

    private static final String SQL_COUNT_ELEMENTS_BY_TYPE =
        "SELECT COUNT(*) AS number FROM cinema_product where type_id=?";
    @Override
    public int getNumberOfProducts(ProductType productType, Connection connection)
            throws DatabaseInteractionException {
        int number = 0;
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_COUNT_ELEMENTS_BY_TYPE);
            statement.setLong(1, productType.getId());
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

    private static final String SQL_SELECT_ID_BY_TITLE =
        "SELECT id FROM cinema_product WHERE title LIKE ";


    private static final String SQL_SELECT_BY_SEARCH_REQUEST =
            "SELECT * FROM cinema_product WHERE title LIKE ";
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

    private static final String SQL_SELECT_RECOMMENDATIONS =
            "SELECT id FROM cinema_product ORDER BY RAND() LIMIT ?";
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


//    private static final String SQL_INSERT_INTO_CINEMA_PRODUCT =
//            "INSERT INTO cinema_product(type_id, title, description, release_date, " +
//                    "running_time, country, age_rating, starring, poster_url) " +
//                            "VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//    private static final String SQL_SELECT_GENERATED_ID =
//            "SELECT id FROM cinema_product WHERE title=? AND release_date=?";
//    private static final String SQL_INSERT_INTO_MOVIE =
//            "INSERT INTO movie(id, directed_by, produced_by, budget, box_office) " +
//                    "VALUE (?, ?, ?, ?, ?)";
//    private static final String SQL_INSERT_INTO_TV_SERIES =
//            "INSERT INTO tv_series(id, number_of_seasons, number_of_episodes, " +
//                    "is_finished) VALUE (?, ?, ?, ?)";
//    private static final String SQL_SELECT_MOVIE_BY_ID =
//            "SELECT * FROM cinema_product INNER JOIN movie m ON cinema_product.id = m.id " +
//                    "WHERE cinema_product.id=?";
//    private static final String SQL_SELECT_MOVIE_BY_TITLE =
//            "SELECT * FROM cinema_product INNER JOIN movie m ON cinema_product.id = m.id " +
//                    "WHERE cinema_product.title=?";
//    private static final String SQL_SELECT_TV_SERIES_BY_ID =
//            "SELECT * FROM cinema_product INNER JOIN tv_series ts on cinema_product.id = ts.id " +
//                    "WHERE cinema_product.id=?";
//    private static final String SQL_SELECT_TV_SERIES_BY_TITLE =
//            "SELECT * FROM cinema_product INNER JOIN tv_series ts on cinema_product.id = ts.id " +
//                    "WHERE cinema_product.title=?";
//    private static final String SQL_SELECT_MOVIES =
//            "SELECT * FROM cinema_product INNER JOIN movie m ON cinema_product.id = m.id";
//    private static final String SQL_SELECT_AMOUNT_OF_MOVIES =
//            "SELECT * FROM cinema_product INNER JOIN movie m ON cinema_product.id = m.id " +
//                    "ORDER BY current_rating DESC LIMIT ?, ?";
//    private static final String SQL_SELECT_TV_SERIES =
//            "SELECT * FROM cinema_product INNER JOIN tv_series ts on cinema_product.id = ts.id";
//    private static final String SQL_SELECT_AMOUNT_OF_TV_SERIES =
//            "SELECT * FROM cinema_product INNER JOIN tv_series ts on cinema_product.id = ts.id " +
//                    "ORDER BY current_rating DESC LIMIT ?, ?";
//    private static final String SQL_DELETE_CINEMA_PRODUCT_GENRES =
//            "DELETE FROM cinema_product_genre WHERE cinema_product_id=?";
//    private static final String SQL_DELETE_CINEMA_PRODUCT =
//            "DELETE FROM cinema_product WHERE id=?";
//    private static final String SQL_COUNT_ELEMENTS_BY_TYPE =
//            "SELECT COUNT(*) AS number FROM cinema_product where type_id=?";
//
//    @Override
//    public boolean create(CinemaProduct cinemaProduct) {
//        boolean wasCreated = true;
//        Connection connection = null;
//        PreparedStatement statement = null;
//
//        try {
//            connection = ConnectionPool.INSTANCE.getAvailableConnection();
//            connection.setAutoCommit(false);
//
//            statement = connection.prepareStatement(SQL_INSERT_INTO_CINEMA_PRODUCT);
//            statement.setLong(1, cinemaProduct.getType().getId());
//            statement.setString(2, cinemaProduct.getTitle());
//            statement.setString(3, cinemaProduct.getDescription());
//            statement.setString(4, cinemaProduct.getReleaseDate().toString());
//            statement.setInt(5, cinemaProduct.getRunningTime());
//            statement.setString(6, cinemaProduct.getCountry());
//            statement.setByte(7, cinemaProduct.getAgeRating());
//            statement.setString(8, cinemaProduct.getStarring());
//            statement.setString(9, cinemaProduct.getPosterUrl());
//            statement.executeUpdate();
//
//            Long generatedForId = getGeneratedId(connection, statement, cinemaProduct);
//            List<Genre> genres = cinemaProduct.getGenres();
////            if (genres != null && genres.size() > 0) {
////                wasCreated = GenreDaoImpl.getInstance().createCinemaProductGenres(generatedForId, genres);
////            }
//
//            if (cinemaProduct.getType() == ProductType.MOVIE) {
//                statement = connection.prepareStatement(SQL_INSERT_INTO_MOVIE);
//                statement.setLong(1, generatedForId);
//                statement.setString(2, ((Movie) cinemaProduct).getDirectedBy());
//                statement.setString(3, ((Movie) cinemaProduct).getProducedBy());
//                statement.setInt(4, ((Movie) cinemaProduct).getBudget());
//                statement.setInt(5, ((Movie) cinemaProduct).getBoxOffice());
//            } else {
//                statement = connection.prepareStatement(SQL_INSERT_INTO_TV_SERIES);
//                statement.setLong(1, generatedForId);
//                statement.setInt(2, ((TvSeries) cinemaProduct).getNumberOfSeasons());
//                statement.setInt(3, ((TvSeries) cinemaProduct).getNumberOfEpisodes());
//                statement.setBoolean(4, ((TvSeries) cinemaProduct).getIsFinished());
//            }
//            statement.executeUpdate();
//
//            connection.commit();
//        } catch (SQLException e) {
//            wasCreated = false;
//            rollback(connection);
//            e.printStackTrace();
//        } finally {
//            closeStatement(statement);
//            closeConnection(connection);
//        }
//
//        return wasCreated;
//    }

//    @Override
//    public CinemaProduct updateByCriteria(CinemaProduct product, CinemaProductCriteria criteria) {
//        return null;
//    }
//
//    @Override
//    public CinemaProduct updateByCriteria(CinemaProductCriteria criteria, CinemaProduct cinemaProduct) {
//        Connection connection = null;
//        PreparedStatement statement = null;
//
//        try {
//            connection = ConnectionPool.INSTANCE.getAvailableConnection();
//            connection.setAutoCommit(false);
//            statement = connection.prepareStatement(buildSqlForUpdateCinemaProduct(criteria, cinemaProduct));
//            statement.executeUpdate();
//
//            if (criteria.getType() == ProductType.MOVIE) {
//                statement = connection.prepareStatement(buildSqlForUpdateMovie(criteria, cinemaProduct));
//            } else {
//                statement = connection.prepareStatement(buildSqlForUpdateTvSeries(criteria, cinemaProduct));
//            }
//            statement.executeUpdate();
//
//            List<Genre> newGenres = criteria.getGenres();
//            if (newGenres != null) {
//                statement = connection.prepareStatement(SQL_DELETE_CINEMA_PRODUCT_GENRES);
//                statement.setLong(1, cinemaProduct.getId());
//                statement.executeUpdate();
////                GenreDaoImpl.getInstance().createCinemaProductGenres(cinemaProduct.getId(), newGenres);
//            }
//            connection.commit();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            rollback(connection);
//        } finally {
//            closeStatement(statement);
//            closeConnection(connection);
//        }
//
//        return cinemaProduct;
//    }

//    private String buildSqlForUpdateCinemaProduct(CinemaProductCriteria criteria, CinemaProduct product) {
//        Map<String, Object> criteriaValues = createCinemaProductValuesMap(criteria);
//        Map<String, String> fieldNames = createCinemaProductFieldNamesMap();
//        StringBuilder builder = new StringBuilder("UPDATE cinema_product SET ");
//
//        for (Map.Entry<String,Object> entry : criteriaValues.entrySet()) {
//            if (entry.getValue() != null) {
//                if (entry.getValue().getClass().getSimpleName().equals(String.class.getSimpleName()) ||
//                        entry.getValue().getClass().getSimpleName().equals(LocalDate.class.getSimpleName())) {
//                    builder.append(fieldNames.get(entry.getKey()) + "='" + entry.getValue() + "', ");
//                } else if (entry.getValue().getClass().getSimpleName().equals(ProductType.class.getSimpleName())) {
//                    builder.append(fieldNames.get(entry.getKey()) + "=" + ((ProductType) entry.getValue()).getId() + ", ");
//                } else {
//                    builder.append(fieldNames.get(entry.getKey()) + "=" + entry.getValue() + ", ");
//                }
//            }
//        }
//
//        builder.append("WHERE id=" + product.getId());
//        builder.deleteCharAt(builder.lastIndexOf(","));
//
//        return builder.toString();
//
//    }
//
//    private String buildSqlForUpdateMovie(CinemaProductCriteria criteria, CinemaProduct product) {
//        Map<String, Object> criteriaValues = createMovieValuesMap(criteria);
//        Map<String, String> fieldNames = createMovieFieldNamesMap();
//        StringBuilder builder = new StringBuilder("UPDATE movie SET ");
//
//        for (Map.Entry<String,Object> entry : criteriaValues.entrySet()) {
//            if (entry.getValue() != null) {
//                if (entry.getValue().getClass().getSimpleName().equals(String.class.getSimpleName())) {
//                    builder.append(fieldNames.get(entry.getKey()) + "='" + entry.getValue() + "', ");
//                } else {
//                    builder.append(fieldNames.get(entry.getKey()) + "=" + entry.getValue() + ", ");
//                }
//            }
//        }
//
//        builder.append("WHERE id=" + product.getId());
//        builder.deleteCharAt(builder.lastIndexOf(","));
//
//        return builder.toString();
//    }
//
//    private String buildSqlForUpdateTvSeries(CinemaProductCriteria criteria, CinemaProduct product) {
//        Map<String, Object> criteriaValues = createTvSeriesValuesMap(criteria);
//        Map<String, String> fieldNames = createTvSeriesFieldNamesMap();
//        StringBuilder builder = new StringBuilder("UPDATE tv_series SET ");
//
//        for (Map.Entry<String,Object> entry : criteriaValues.entrySet()) {
//            if (entry.getValue() != null) {
//                if (entry.getValue().getClass().getSimpleName().equals(Boolean.class.getSimpleName())) {
//                    if (entry.getValue().equals(true)) {
//                        builder.append(fieldNames.get(entry.getKey()) + "=" + 1 + ", ");
//                    } else {
//                        builder.append(fieldNames.get(entry.getKey()) + "=" + 0 + ", ");
//                    }
//                } else {
//                    builder.append(fieldNames.get(entry.getKey()) + "=" + entry.getValue() + ", ");
//                }
//            }
//        }
//
//        builder.append("WHERE id=" + product.getId());
//        builder.deleteCharAt(builder.lastIndexOf(","));
//
//        return builder.toString();
//    }
//
//    private Map<String, Object> createMovieValuesMap(CinemaProductCriteria productCriteria) {
//        Map<String, Object> criteriaValues = new HashMap<>();
//        MovieCriteria criteria = (MovieCriteria) productCriteria;
//        criteriaValues.put("directedBy", criteria.getDirectedBy());
//        criteriaValues.put("producedBy", criteria.getProducedBy());
//        criteriaValues.put("budget", criteria.getBudget());
//        criteriaValues.put("boxOffice", criteria.getBoxOffice());
//
//        return criteriaValues;
//    }
//
//    private Map<String, String> createMovieFieldNamesMap() {
//        Map<String, String> fieldNames = new HashMap<>();
//        fieldNames.put("directedBy", "directed_by");
//        fieldNames.put("producedBy", "produced_by");
//        fieldNames.put("budget", "budget");
//        fieldNames.put("boxOffice", "box_office");
//
//        return fieldNames;
//    }
//
//    private Map<String, Object> createTvSeriesValuesMap(CinemaProductCriteria productCriteria) {
//        Map<String, Object> criteriaValues = new HashMap<>();
//        TvSeriesCriteria criteria = (TvSeriesCriteria) productCriteria;
//        criteriaValues.put("numberOfSeasons", criteria.getNumberOfSeasons());
//        criteriaValues.put("numberOfEpisodes", criteria.getNumberOfEpisodes());
//        criteriaValues.put("isFinished", criteria.getFinished());
//
//        return criteriaValues;
//    }
//
//    private Map<String, String> createTvSeriesFieldNamesMap() {
//        Map<String, String> fieldNames = new HashMap<>();
//        fieldNames.put("numberOfSeasons", "number_of_seasons");
//        fieldNames.put("numberOfEpisodes", "number_of_episodes");
//        fieldNames.put("isFinished", "is_finished");
//
//        return fieldNames;
//    }
//
//    private Map<String, Object> createCinemaProductValuesMap(CinemaProductCriteria criteria) {
//        Map<String, Object> criteriaValues = new HashMap<>();
//        criteriaValues.put("title", criteria.getTitle());
//        criteriaValues.put("description", criteria.getDescription());
//        criteriaValues.put("releaseDate", criteria.getReleaseDate());
//        criteriaValues.put("runningTime", criteria.getRunningTime());
//        criteriaValues.put("country", criteria.getCountry());
//        criteriaValues.put("ageRating", criteria.getAgeRating());
//        criteriaValues.put("starring", criteria.getStarring());
//        criteriaValues.put("posterUrl", criteria.getPosterUrl());
//
//        return criteriaValues;
//    }
//
//    private Map<String, String> createCinemaProductFieldNamesMap() {
//        Map<String, String> fieldNames = new HashMap<>();
//        fieldNames.put("title", "title");
//        fieldNames.put("description", "description");
//        fieldNames.put("releaseDate", "release_date");
//        fieldNames.put("runningTime", "running_time");
//        fieldNames.put("country", "country");
//        fieldNames.put("ageRating", "age_rating");
//        fieldNames.put("starring", "starring");
//        fieldNames.put("posterUrl", "poster_url");
//
//        return fieldNames;
//    }
//

}
