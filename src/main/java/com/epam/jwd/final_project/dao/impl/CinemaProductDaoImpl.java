package com.epam.jwd.final_project.dao.impl;

import com.epam.jwd.final_project.criteria.*;
import com.epam.jwd.final_project.dao.CinemaProductDao;
import com.epam.jwd.final_project.domain.*;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.pool.ConnectionPool;
import com.epam.jwd.final_project.util.DateConverterUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class CinemaProductDaoImpl implements CinemaProductDao {

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
    private static final String SQL_SELECT_MOVIE_BY_ID =
            "SELECT * FROM cinema_product INNER JOIN movie m ON cinema_product.id = m.id " +
                    "WHERE cinema_product.id=?";
    private static final String SQL_SELECT_MOVIE_BY_TITLE =
            "SELECT * FROM cinema_product INNER JOIN movie m ON cinema_product.id = m.id " +
                    "WHERE cinema_product.title=?";
    private static final String SQL_SELECT_TV_SERIES_BY_ID =
            "SELECT * FROM cinema_product INNER JOIN tv_series ts on cinema_product.id = ts.id " +
                    "WHERE cinema_product.id=?";
    private static final String SQL_SELECT_TV_SERIES_BY_TITLE =
            "SELECT * FROM cinema_product INNER JOIN tv_series ts on cinema_product.id = ts.id " +
                    "WHERE cinema_product.title=?";
    private static final String SQL_SELECT_MOVIES =
            "SELECT * FROM cinema_product INNER JOIN movie m ON cinema_product.id = m.id";
    private static final String SQL_SELECT_AMOUNT_OF_MOVIES =
            "SELECT * FROM cinema_product INNER JOIN movie m ON cinema_product.id = m.id " +
                    "ORDER BY current_rating DESC LIMIT ?, ?";
    private static final String SQL_SELECT_TV_SERIES =
            "SELECT * FROM cinema_product INNER JOIN tv_series ts on cinema_product.id = ts.id";
    private static final String SQL_SELECT_AMOUNT_OF_TV_SERIES =
            "SELECT * FROM cinema_product INNER JOIN tv_series ts on cinema_product.id = ts.id " +
                    "ORDER BY current_rating DESC LIMIT ?, ?";
    private static final String SQL_DELETE_CINEMA_PRODUCT_GENRES =
            "DELETE FROM cinema_product_genre WHERE cinema_product_id=?";
    private static final String SQL_DELETE_CINEMA_PRODUCT =
            "DELETE FROM cinema_product WHERE id=?";
    private static final String SQL_COUNT_ELEMENTS_BY_TYPE =
            "SELECT COUNT(*) AS number FROM cinema_product where type_id=?";

    @Override
    public boolean create(CinemaProduct cinemaProduct) {
        boolean wasCreated = true;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.INSTANCE.getAvailableConnection();
            connection.setAutoCommit(false);

            statement = connection.prepareStatement(SQL_INSERT_INTO_CINEMA_PRODUCT);
            statement.setLong(1, cinemaProduct.getType().getId());
            statement.setString(2, cinemaProduct.getTitle());
            statement.setString(3, cinemaProduct.getDescription());
            statement.setString(4, cinemaProduct.getReleaseDate().toString());
            statement.setInt(5, cinemaProduct.getRunningTime());
            statement.setString(6, cinemaProduct.getCountry());
            statement.setByte(7, cinemaProduct.getAgeRating());
            statement.setString(8, cinemaProduct.getStarring());
            statement.setString(9, cinemaProduct.getPosterUrl());
            statement.executeUpdate();

            Long generatedForId = getGeneratedId(connection, statement, cinemaProduct);
            List<Genre> genres = cinemaProduct.getGenres();
//            if (genres != null && genres.size() > 0) {
//                wasCreated = GenreDaoImpl.getInstance().createCinemaProductGenres(generatedForId, genres);
//            }

            if (cinemaProduct.getType() == ProductType.MOVIE) {
                statement = connection.prepareStatement(SQL_INSERT_INTO_MOVIE);
                statement.setLong(1, generatedForId);
                statement.setString(2, ((Movie) cinemaProduct).getDirectedBy());
                statement.setString(3, ((Movie) cinemaProduct).getProducedBy());
                statement.setInt(4, ((Movie) cinemaProduct).getBudget());
                statement.setInt(5, ((Movie) cinemaProduct).getBoxOffice());
            } else {
                statement = connection.prepareStatement(SQL_INSERT_INTO_TV_SERIES);
                statement.setLong(1, generatedForId);
                statement.setInt(2, ((TvSeries) cinemaProduct).getNumberOfSeasons());
                statement.setInt(3, ((TvSeries) cinemaProduct).getNumberOfEpisodes());
                statement.setBoolean(4, ((TvSeries) cinemaProduct).getIsFinished());
            }
            statement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            wasCreated = false;
            rollback(connection);
            e.printStackTrace();
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return wasCreated;
    }

    @Override
    public List<CinemaProduct> findAll() {
        List<CinemaProduct> products = new ArrayList<>();
        products.addAll(findAllMovies());
        products.addAll(findAllTvSeries());

        return products;
    }

    @Override
    public Optional<CinemaProduct> findById(Long id) {
        CinemaProduct product = null;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.INSTANCE.getAvailableConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SQL_SELECT_MOVIE_BY_ID);
            statement.setLong(1, id);
            product = parseSetToGetMovie(statement.executeQuery());

            if (product == null) {
                statement = connection.prepareStatement(SQL_SELECT_TV_SERIES_BY_ID);
                statement.setLong(1, id);
                product = parseSetToGetTvSeries(statement.executeQuery());
            }

            product.setReviews(ReviewDaoImpl.getInstance().findAllForParticularCinemaProduct(id));
//            product.setGenres(GenreDaoImpl.getInstance().findCinemaProductGenres(id));
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return Optional.ofNullable(product);
    }

    public List<CinemaProduct> findByTitle(String title) {
        List<CinemaProduct> products = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.INSTANCE.getAvailableConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SQL_SELECT_MOVIE_BY_TITLE);
            statement.setString(1, title);
            ResultSet set = statement.executeQuery();
            CinemaProduct product;
            while (set.next()) {
                product = new Movie(set.getLong("id"),
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

                product.setReviews(ReviewDaoImpl.getInstance().findAllForParticularCinemaProduct(product.getId()));
//                product.setGenres(GenreDaoImpl.getInstance().findCinemaProductGenres(product.getId()));
                products.add(product);
            }

            set = statement.executeQuery();
            while (set.next()) {
                product = new TvSeries(set.getLong("id"),
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

                product.setReviews(ReviewDaoImpl.getInstance().findAllForParticularCinemaProduct(product.getId()));
//                product.setGenres(GenreDaoImpl.getInstance().findCinemaProductGenres(product.getId()));
                products.add(product);
            }

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return products;
    }

    @Override
    public boolean delete(CinemaProduct cinemaProduct) {
        boolean wasDeleted = true;
        Connection connection = null;
        PreparedStatement statement = null;
        List<Review> productReviews = null;

        productReviews = ReviewDaoImpl.getInstance().findAllForParticularCinemaProduct(cinemaProduct.getId()).stream()
                .peek(review -> review.setProductTitle(review.getProductTitle() + "(deleted)"))
                .collect(Collectors.toList());

        if (productReviews != null && productReviews.size() > 0) {
            ReviewDaoImpl.getInstance().transferInHistoryTable(productReviews);
        }

        try {
            connection = ConnectionPool.INSTANCE.getAvailableConnection();
            statement = connection.prepareStatement(SQL_DELETE_CINEMA_PRODUCT);
            statement.setLong(1, cinemaProduct.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            wasDeleted = false;
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return wasDeleted;
    }

    @Override
    public CinemaProduct updateByCriteria(CinemaProduct product, CinemaProductCriteria criteria) {
        return null;
    }


    public CinemaProduct update(CinemaProduct cinemaProduct) {
        return null;
    }

    @Override
    public CinemaProduct updateByCriteria(CinemaProductCriteria criteria, CinemaProduct cinemaProduct) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionPool.INSTANCE.getAvailableConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(buildSqlForUpdateCinemaProduct(criteria, cinemaProduct));
            statement.executeUpdate();

            if (criteria.getType() == ProductType.MOVIE) {
                statement = connection.prepareStatement(buildSqlForUpdateMovie(criteria, cinemaProduct));
            } else {
                statement = connection.prepareStatement(buildSqlForUpdateTvSeries(criteria, cinemaProduct));
            }
            statement.executeUpdate();

            List<Genre> newGenres = criteria.getGenres();
            if (newGenres != null) {
                statement = connection.prepareStatement(SQL_DELETE_CINEMA_PRODUCT_GENRES);
                statement.setLong(1, cinemaProduct.getId());
                statement.executeUpdate();
//                GenreDaoImpl.getInstance().createCinemaProductGenres(cinemaProduct.getId(), newGenres);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            rollback(connection);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return cinemaProduct;
    }

    @Override
    public List<CinemaProduct> findConcreteAmount(ProductType type, Long start, Long number) {
        List<CinemaProduct> products = new ArrayList<>();
        String sql;

        if (type == ProductType.MOVIE) {
            sql = SQL_SELECT_AMOUNT_OF_MOVIES;
        } else {
            sql = SQL_SELECT_AMOUNT_OF_TV_SERIES;
        }

        try (Connection connection = ConnectionPool.INSTANCE.getAvailableConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, start);
            statement.setLong(2, number);
            ResultSet set = statement.executeQuery();
            CinemaProduct product;
            while (set.next()) {
                if (type == ProductType.MOVIE) {
                    product = new Movie(set.getLong("id"),
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
                } else {
                    product = new TvSeries(set.getLong("id"),
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
                product.setReviews(ReviewDaoImpl.getInstance().findAllForParticularCinemaProduct(product.getId()));
//                product.setGenres(GenreDaoImpl.getInstance().findCinemaProductGenres(product.getId()));
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public Long getNumberOfElements(ProductType productType) {
        Long number = null;

        try (Connection connection = ConnectionPool.INSTANCE.getAvailableConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_COUNT_ELEMENTS_BY_TYPE)) {
            statement.setLong(1, productType.getId());
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                number = set.getLong("number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return number;
    }

    @Override
    public List<CinemaProduct> findBySearchRequest(String searchRequest) {
        List<CinemaProduct> products = new ArrayList<>();
        String str = "'%" + searchRequest + "%'";

        try (Connection connection = ConnectionPool.INSTANCE.getAvailableConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM cinema_product WHERE title LIKE " + str)) {
            System.out.println("search-request statement === " + statement);
            ResultSet set = statement.executeQuery();
            CinemaProduct product;
            while (set.next()) {
                product = new CinemaProduct(set.getLong("id"),
                        ProductType.MOVIE,
                        set.getDouble("current_rating"),
                        set.getString("title"),
                        DateConverterUtil.convertToLocalDate(set.getString("release_date")),
                        set.getString("country"),
                        set.getString("poster_url"));
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }


    private static final String SQL_SELECT_RECOMMENDATION =
            "SELECT * FROM cinema_product ORDER BY RAND() LIMIT ?";
    @Override
    public List<CinemaProduct> findRecommendations(Connection connection)
            throws DatabaseInteractionException {
        final int numberOfProducts = 6;
        List<CinemaProduct> recommendation = new ArrayList<>();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_SELECT_RECOMMENDATION);
            statement.setInt(1, numberOfProducts);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                recommendation.add(new CinemaProduct(set.getLong("id"),
                        set.getDouble("current_rating"),
                        set.getString("title"),
                        set.getString("poster_url")));
            }
        } catch (SQLException e) {
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return recommendation;
    }

//    @Override
//    public List<CinemaProduct> findTopOfProducts(ProductType type) {
//        List<CinemaProduct> products = new ArrayList<>();
//        final Integer rating = 9;
//        String sql;
//
//        if (type == ProductType.MOVIE) {
//            sql = SQL_SELECT_TOP_MOVIES;
//        } else {
//            sql = SQL_SELECT_TOP_TV_SERIES;
//        }
//
//        try (Connection connection = ConnectionPool.INSTANCE.getAvailableConnection();
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, rating);
//            ResultSet set = statement.executeQuery();
//            CinemaProduct product;
//            while (set.next()) {
//                if (type == ProductType.MOVIE) {
//                    product = new Movie(set.getLong("id"),
//                            ProductType.MOVIE,
//                            set.getDouble("current_rating"),
//                            set.getString("title"),
//                            set.getString("description"),
//                            DateConverterUtil.convertToLocalDate(set.getString("release_date")),
//                            set.getInt("running_time"),
//                            set.getString("country"),
//                            set.getByte("age_rating"),
//                            set.getString("starring"),
//                            set.getString("poster_url"),
//                            set.getString("directed_by"),
//                            set.getString("produced_by"),
//                            set.getInt("budget"),
//                            set.getInt("box_office"));
//                } else {
//                    product = new Movie(set.getLong("id"),
//                            ProductType.MOVIE,
//                            set.getDouble("current_rating"),
//                            set.getString("title"),
//                            set.getString("description"),
//                            DateConverterUtil.convertToLocalDate(set.getString("release_date")),
//                            set.getInt("running_time"),
//                            set.getString("country"),
//                            set.getByte("age_rating"),
//                            set.getString("starring"),
//                            set.getString("poster_url"),
//                            set.getString("directed_by"),
//                            set.getString("produced_by"),
//                            set.getInt("budget"),
//                            set.getInt("box_office"));
//                }
//                product.setReviews(ReviewDaoImpl.getInstance().findAllForParticularCinemaProduct(product.getId()));
//                product.setGenres(GenreDaoImpl.getInstance().findCinemaProductGenres(product.getId()));
//                products.add(product);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return products;
//    }

    @Override
    public List<CinemaProduct> findAllByType(ProductType productType) {
        if (productType == ProductType.MOVIE) {
            return findAllMovies();
        } else {
            return findAllTvSeries();
        }
    }

    private List<CinemaProduct> findAllMovies() {
        List<CinemaProduct> movies = new ArrayList<>();

        try (Connection connection = ConnectionPool.INSTANCE.getAvailableConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_MOVIES)) {
            ResultSet set = statement.executeQuery();
            CinemaProduct movie;
            while (set.next()) {
                movie = new Movie(set.getLong("id"),
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

                movie.setReviews(ReviewDaoImpl.getInstance().findAllForParticularCinemaProduct(movie.getId()));
//                movie.setGenres(GenreDaoImpl.getInstance().findCinemaProductGenres(movie.getId()));
                movies.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movies;
    }

    private List<CinemaProduct> findAllTvSeries() {
        List<CinemaProduct> tvSeries = new ArrayList<>();

        try (Connection connection = ConnectionPool.INSTANCE.getAvailableConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_TV_SERIES)) {
            ResultSet set = statement.executeQuery();
            CinemaProduct tvSeriesEpisode;
            while (set.next()) {
                tvSeriesEpisode = new TvSeries(set.getLong("id"),
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

                tvSeriesEpisode.setReviews(ReviewDaoImpl.getInstance().findAllForParticularCinemaProduct(tvSeriesEpisode.getId()));
//                tvSeriesEpisode.setGenres(GenreDaoImpl.getInstance().findCinemaProductGenres(tvSeriesEpisode.getId()));
                tvSeries.add(tvSeriesEpisode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tvSeries;
    }

    private String buildSqlForUpdateCinemaProduct(CinemaProductCriteria criteria, CinemaProduct product) {
        Map<String, Object> criteriaValues = createCinemaProductValuesMap(criteria);
        Map<String, String> fieldNames = createCinemaProductFieldNamesMap();
        StringBuilder builder = new StringBuilder("UPDATE cinema_product SET ");

        for (Map.Entry<String,Object> entry : criteriaValues.entrySet()) {
            if (entry.getValue() != null) {
                if (entry.getValue().getClass().getSimpleName().equals(String.class.getSimpleName()) ||
                        entry.getValue().getClass().getSimpleName().equals(LocalDate.class.getSimpleName())) {
                    builder.append(fieldNames.get(entry.getKey()) + "='" + entry.getValue() + "', ");
                } else if (entry.getValue().getClass().getSimpleName().equals(ProductType.class.getSimpleName())) {
                    builder.append(fieldNames.get(entry.getKey()) + "=" + ((ProductType) entry.getValue()).getId() + ", ");
                } else {
                    builder.append(fieldNames.get(entry.getKey()) + "=" + entry.getValue() + ", ");
                }
            }
        }

        builder.append("WHERE id=" + product.getId());
        builder.deleteCharAt(builder.lastIndexOf(","));

        return builder.toString();

    }

    private String buildSqlForUpdateMovie(CinemaProductCriteria criteria, CinemaProduct product) {
        Map<String, Object> criteriaValues = createMovieValuesMap(criteria);
        Map<String, String> fieldNames = createMovieFieldNamesMap();
        StringBuilder builder = new StringBuilder("UPDATE movie SET ");

        for (Map.Entry<String,Object> entry : criteriaValues.entrySet()) {
            if (entry.getValue() != null) {
                if (entry.getValue().getClass().getSimpleName().equals(String.class.getSimpleName())) {
                    builder.append(fieldNames.get(entry.getKey()) + "='" + entry.getValue() + "', ");
                } else {
                    builder.append(fieldNames.get(entry.getKey()) + "=" + entry.getValue() + ", ");
                }
            }
        }

        builder.append("WHERE id=" + product.getId());
        builder.deleteCharAt(builder.lastIndexOf(","));

        return builder.toString();
    }

    private String buildSqlForUpdateTvSeries(CinemaProductCriteria criteria, CinemaProduct product) {
        Map<String, Object> criteriaValues = createTvSeriesValuesMap(criteria);
        Map<String, String> fieldNames = createTvSeriesFieldNamesMap();
        StringBuilder builder = new StringBuilder("UPDATE tv_series SET ");

        for (Map.Entry<String,Object> entry : criteriaValues.entrySet()) {
            if (entry.getValue() != null) {
                if (entry.getValue().getClass().getSimpleName().equals(Boolean.class.getSimpleName())) {
                    if (entry.getValue().equals(true)) {
                        builder.append(fieldNames.get(entry.getKey()) + "=" + 1 + ", ");
                    } else {
                        builder.append(fieldNames.get(entry.getKey()) + "=" + 0 + ", ");
                    }
                } else {
                    builder.append(fieldNames.get(entry.getKey()) + "=" + entry.getValue() + ", ");
                }
            }
        }

        builder.append("WHERE id=" + product.getId());
        builder.deleteCharAt(builder.lastIndexOf(","));

        return builder.toString();
    }

    private Map<String, Object> createMovieValuesMap(CinemaProductCriteria productCriteria) {
        Map<String, Object> criteriaValues = new HashMap<>();
        MovieCriteria criteria = (MovieCriteria) productCriteria;
        criteriaValues.put("directedBy", criteria.getDirectedBy());
        criteriaValues.put("producedBy", criteria.getProducedBy());
        criteriaValues.put("budget", criteria.getBudget());
        criteriaValues.put("boxOffice", criteria.getBoxOffice());

        return criteriaValues;
    }

    private Map<String, String> createMovieFieldNamesMap() {
        Map<String, String> fieldNames = new HashMap<>();
        fieldNames.put("directedBy", "directed_by");
        fieldNames.put("producedBy", "produced_by");
        fieldNames.put("budget", "budget");
        fieldNames.put("boxOffice", "box_office");

        return fieldNames;
    }

    private Map<String, Object> createTvSeriesValuesMap(CinemaProductCriteria productCriteria) {
        Map<String, Object> criteriaValues = new HashMap<>();
        TvSeriesCriteria criteria = (TvSeriesCriteria) productCriteria;
        criteriaValues.put("numberOfSeasons", criteria.getNumberOfSeasons());
        criteriaValues.put("numberOfEpisodes", criteria.getNumberOfEpisodes());
        criteriaValues.put("isFinished", criteria.getFinished());

        return criteriaValues;
    }

    private Map<String, String> createTvSeriesFieldNamesMap() {
        Map<String, String> fieldNames = new HashMap<>();
        fieldNames.put("numberOfSeasons", "number_of_seasons");
        fieldNames.put("numberOfEpisodes", "number_of_episodes");
        fieldNames.put("isFinished", "is_finished");

        return fieldNames;
    }

    private Map<String, Object> createCinemaProductValuesMap(CinemaProductCriteria criteria) {
        Map<String, Object> criteriaValues = new HashMap<>();
        criteriaValues.put("title", criteria.getTitle());
        criteriaValues.put("description", criteria.getDescription());
        criteriaValues.put("releaseDate", criteria.getReleaseDate());
        criteriaValues.put("runningTime", criteria.getRunningTime());
        criteriaValues.put("country", criteria.getCountry());
        criteriaValues.put("ageRating", criteria.getAgeRating());
        criteriaValues.put("starring", criteria.getStarring());
        criteriaValues.put("posterUrl", criteria.getPosterUrl());

        return criteriaValues;
    }

    private Map<String, String> createCinemaProductFieldNamesMap() {
        Map<String, String> fieldNames = new HashMap<>();
        fieldNames.put("title", "title");
        fieldNames.put("description", "description");
        fieldNames.put("releaseDate", "release_date");
        fieldNames.put("runningTime", "running_time");
        fieldNames.put("country", "country");
        fieldNames.put("ageRating", "age_rating");
        fieldNames.put("starring", "starring");
        fieldNames.put("posterUrl", "poster_url");

        return fieldNames;
    }

    public Long getGeneratedId(Connection connection, PreparedStatement statement,
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

    private CinemaProduct parseSetToGetMovie(ResultSet set) throws SQLException {
        CinemaProduct movie = null;

        while (set.next()){
            movie = new Movie(set.getLong("id"),
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

        return movie;
    }

    private CinemaProduct parseSetToGetTvSeries(ResultSet set) throws SQLException {
        CinemaProduct tvSeries = null;

        while (set.next()){
            tvSeries = new TvSeries(set.getLong("id"),
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


        return tvSeries;
    }

}
