package test.com.epam.jwd.final_project.dao.impl;

import com.epam.jwd.final_project.criteria.QuoteCriteria;
import com.epam.jwd.final_project.dao.impl.QuoteDaoImpl;
import com.epam.jwd.final_project.domain.Quote;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.pool.ConnectionPool;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

public class QuoteDaoImplTest {

    private Connection connection = ConnectionPool.INSTANCE.testConnection;
    private Quote quote = new Quote("Plastics.", "The Graduate",
            "https://drive.google.com/uc?export=download&id=1O3ZMjXqI_haVs6Z_jZdSn4EMyWf_IpfX");
    private QuoteCriteria criteriaToUpdate = new QuoteCriteria.QuoteCriteriaBuilder() {{
                productTitle("The Sixth Sense");
                quoteText("I see dead people.");
                posterUrl("https://drive.google.com/uc?export=download&id=1ehz8aEbt4n69gWAnE3JUmP49LB7qrB2U");
            }}.build();
    private Quote updatedQuote = new Quote("I see dead people.", "The Sixth Sense",
            "https://drive.google.com/uc?export=download&id=1ehz8aEbt4n69gWAnE3JUmP49LB7qrB2U");

    @Test
    public void createTest() throws DatabaseInteractionException {
        assertTrue(QuoteDaoImpl.getInstance().create(quote, connection));
    }

    @Test
    public void findAllTest() throws SQLException, DatabaseInteractionException {
        long expectedNumberOfQuotes = getNumberOfQuotes(connection);
        long actualNumberOfQuotes = QuoteDaoImpl.getInstance().findAll(connection).size();

        assertEquals(expectedNumberOfQuotes, actualNumberOfQuotes);
    }

    @Test
    public void findByIdTest() throws DatabaseInteractionException, SQLException {
        Long idToFind = getLastId();
        assertEquals(idToFind, QuoteDaoImpl.getInstance()
                .findById(idToFind, connection).get().getId());
    }

    @Test
    public void updateByCriteriaTest() throws DatabaseInteractionException, SQLException {
        Quote beforeUpdate = QuoteDaoImpl.getInstance().findById(getLastId(), connection).get();
        Quote afterUpdate = QuoteDaoImpl.getInstance()
                .updateByCriteria(beforeUpdate, criteriaToUpdate, connection);

        assertEquals(updatedQuote, afterUpdate);
    }

    @Test
    public void deleteTest() throws DatabaseInteractionException, SQLException {
        quote.setId(getLastId());
        assertTrue(QuoteDaoImpl.getInstance().delete(quote, connection));
    }

    private int getNumberOfQuotes(Connection connection) throws SQLException {
        int number = 0;
        final String sql = "SELECT COUNT(*) AS number FROM quote";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet set = statement.executeQuery();

        while (set.next()) {
            number = set.getInt("number");
        }

        return number;
    }

    private Long getLastId() throws SQLException {
        Long lastId = null;
        final String sql = "SELECT id FROM quote ORDER BY id DESC LIMIT 1";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet set = statement.executeQuery();

        while (set.next()) {
            lastId = set.getLong("id");
        }

        return lastId;
    }

}
