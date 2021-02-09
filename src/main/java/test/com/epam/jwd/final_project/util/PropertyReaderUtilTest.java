package test.com.epam.jwd.final_project.util;

import com.epam.jwd.final_project.domain.ApplicationProperties;
import com.epam.jwd.final_project.util.PropertyReaderUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PropertyReaderUtilTest {

    private ApplicationProperties properties =
            new ApplicationProperties("jdbc:mysql://localhost:3306/rating?serverTimezone=UTC",
                    "jdbc:mysql://localhost:3306/test_rating?serverTimezone=UTC", "root",
                    "20wrk_sq!L20", Integer.valueOf(20), Integer.valueOf(50), "yyyy-MM-dd",
                    "email@gmail.com", "password");

    @Test
    void getApplicationPropertiesTest() {
        assertEquals(properties, PropertyReaderUtil.getApplicationProperties());
    }

}