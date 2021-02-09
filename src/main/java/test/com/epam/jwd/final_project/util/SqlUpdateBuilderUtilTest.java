package test.com.epam.jwd.final_project.util;

import com.epam.jwd.final_project.criteria.AppUserCriteria;
import com.epam.jwd.final_project.criteria.QuoteCriteria;
import com.epam.jwd.final_project.domain.AppUser;
import com.epam.jwd.final_project.domain.Quote;
import com.epam.jwd.final_project.util.SqlUpdateBuilderUtil;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class SqlUpdateBuilderUtilTest {

    private Quote quote = new Quote(1L, "Quote text", "Title", "Poster url");
    private QuoteCriteria criteriaToUpdateQuote = new QuoteCriteria.QuoteCriteriaBuilder() {{
                productTitle("New title");
                quoteText("New quote text");
                posterUrl("New poster url");
            }}.build();
    private String quoteUpdateSql = "UPDATE quote SET product_title='New title', " +
            "quote_text='New quote text', poster_url='New poster url' WHERE id=1";
    private AppUser user = new AppUser(1L, "FirstName", "LastName", "Nickname",
            LocalDate.of(1990, 1, 1), "user.email@mail.com", "passwordHash");
    private AppUserCriteria criteriaToUpdateUser = new AppUserCriteria.AppUserCriteriaBuilder() {{
                firstName("NewFirstName");
                lastName("NewLastName");
                dateOfBirth(LocalDate.of(1990, 1, 2));
            }}.build();
    private String userUpdateSql = "UPDATE app_user SET first_name='NewFirstName', " +
            "last_name='NewLastName', date_of_birth='1990-01-02' WHERE id=1";

    @Test
    public void buildSqlQuoteUpdateTest() {
        assertEquals(quoteUpdateSql, SqlUpdateBuilderUtil.buildSqlQuoteUpdate(criteriaToUpdateQuote, quote));
    }

    @Test
    public void buildSqlUserUpdateTest() {
        assertEquals(userUpdateSql, SqlUpdateBuilderUtil.buildSqlUserUpdate(criteriaToUpdateUser, user));
    }

}
