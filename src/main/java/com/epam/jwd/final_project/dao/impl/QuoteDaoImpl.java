package com.epam.jwd.final_project.dao.impl;

import com.epam.jwd.final_project.criteria.Criteria;
import com.epam.jwd.final_project.criteria.QuoteCriteria;
import com.epam.jwd.final_project.dao.EntityDao;
import com.epam.jwd.final_project.dao.ReleaseResources;
import com.epam.jwd.final_project.domain.AppEntity;
import com.epam.jwd.final_project.domain.Quote;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.util.SqlUpdateBuilderUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuoteDaoImpl implements EntityDao<Quote, QuoteCriteria>, ReleaseResources {

    private static QuoteDaoImpl INSTANCE;

    public static QuoteDaoImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new QuoteDaoImpl();
        }
        return INSTANCE;
    }

    /**
     * A constant that is the SQL statement to be sent to the database
     * to insert {@link Quote} into specified inside this statement table.
     * Contains three '?' IN parameters.
     */
    private static final String SQL_INSERT_INTO_QUOTE =
            "INSERT INTO quote(quote_text, product_title, poster_url) VALUE (?, ?, ?)";

    /**
     * A constant that is the SQL statement to be sent to the database
     * to select all {@link Quote} objects from the database.
     */
    private static final String SQL_SELECT_QUOTES = "SELECT * FROM quote";

    /**
     * A constant that is the SQL statement to be sent to the database
     * to select {@link Quote} a specific id number.
     */
    private static final String SQL_SELECT_QUOTE_BY_ID = "SELECT * FROM quote WHERE id=?";

    /**
     * A constant that is the SQL statement to be sent to the database
     * to remove the specific {@link Quote}.
     */
    private static final String SQL_DELETE_QUOTE = "DELETE FROM quote WHERE id=?";
    
    /**
     * Overrides {@link EntityDao#create(AppEntity, Connection)} method.
     * Inserts the specified {@link Quote} into the specified database table.
     * @param quote the Quote to add to the database
     * @param connection a {@link java.sql.Connection} to the database URL
     * @return true if this Quote was added to the database, else false
     * @throws DatabaseInteractionException if an SQLException occurs
     */
    @Override
    public boolean create(Quote quote, Connection connection) throws DatabaseInteractionException {
        boolean wasCreated = false;
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_INSERT_INTO_QUOTE);
            statement.setString(1, quote.getQuoteText());
            statement.setString(2, quote.getProductTitle());
            statement.setString(3, quote.getPosterUrl());
            statement.execute();
            wasCreated = true;
        } catch (SQLException e) {
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return wasCreated;
    }

    /**
     * Overrides {@link EntityDao#findAll(Connection)} method.
     * Returns all {@link Quote} objects, which are present in the database.
     * @param connection a {@link java.sql.Connection} to the database URL
     * @return {@link java.util.List} of Quote objects retrieved from the database
     * @throws DatabaseInteractionException if an SQLException occurs
     */
    @Override
    public List<Quote> findAll(Connection connection) throws DatabaseInteractionException {
        List<Quote> quotes = new ArrayList<>();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_SELECT_QUOTES);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                quotes.add(new Quote(set.getLong("id"),
                        set.getString("quote_text"),
                        set.getString("product_title"),
                        set.getString("poster_url")));
            }
        } catch (SQLException e) {
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return quotes;
    }

    /**
     * Overrides {@link EntityDao#findById(Long, Connection)} method.
     * Returns an {@link java.util.Optional} describing {@link Quote} with a specific
     * id number, or an empty Optional if there is no such Quote.
     * @param id id of the Quote to find
     * @param connection a {@link java.sql.Connection} to the database URL
     * @return an Optional describing this Quote, or an empty Optional
     * @throws DatabaseInteractionException if an SQLException occurs
     */
    @Override
    public Optional<Quote> findById(Long id, Connection connection) throws DatabaseInteractionException {
        Quote quote = null;
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_SELECT_QUOTE_BY_ID);
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                quote = new Quote(id,
                        set.getString("quote_text"),
                        set.getString("product_title"),
                        set.getString("poster_url"));
            }
        } catch (SQLException e) {
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return Optional.ofNullable(quote);
    }

    /**
     * Overrides {@link EntityDao#delete(AppEntity, Connection)} method.
     * Removes the specified {@link Quote} from the database.
     * @param quote the Quote to remove
     * @param connection a {@link java.sql.Connection} to the database URL
     * @return true if Quote was removed, else false
     * @throws DatabaseInteractionException if an SQLException occurs
     */
    @Override
    public boolean delete(Quote quote, Connection connection) throws DatabaseInteractionException {
        boolean wasDeleted = false;
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(SQL_DELETE_QUOTE);
            statement.setLong(1, quote.getId());
            statement.execute();
            wasDeleted = true;
        } catch (SQLException e) {
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return wasDeleted;
    }

    /**
     * Overrides {@link EntityDao#updateByCriteria(AppEntity, Criteria, Connection)} method.
     * Updates {@link Quote} according to the given {@link QuoteCriteria}.
     * @param quote the Quote to update
     * @param quoteCriteria the {@link QuoteCriteria} object that contains
     *                      information about how Quote should be updated
     * @param connection    a {@link java.sql.Connection} to the database URL
     * @return updated Quote
     * @throws DatabaseInteractionException if an SQLException occurs
     */
    @Override
    public Quote updateByCriteria(Quote quote, QuoteCriteria quoteCriteria, Connection connection)
        throws DatabaseInteractionException{
        final String updateSql = SqlUpdateBuilderUtil.buildSqlQuoteUpdate(quoteCriteria, quote);
        System.out.println("updateSql + " + updateSql);
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(updateSql);
            statement.executeUpdate();
            quote = findById(quote.getId(), connection).get();
        } catch (SQLException e) {
            throw new DatabaseInteractionException(e);
        } finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return quote;
    }

}
