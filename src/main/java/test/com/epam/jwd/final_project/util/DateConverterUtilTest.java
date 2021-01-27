package test.com.epam.jwd.final_project.util;

import com.epam.jwd.final_project.util.DateConverterUtil;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class DateConverterUtilTest {

    private LocalDate expectedDate = LocalDate.of(2020, 1, 1);
    private String correctString = "2020-01-01";
    private String wrongString = "2020-01-02";

    @Test
    public void convertToLocalDateTestTrue() {
        LocalDate actual = DateConverterUtil.convertToLocalDate(correctString);
        assertEquals(expectedDate, actual);
    }

    @Test
    public void convertToLocalDateTestFalse() {
        LocalDate actual = DateConverterUtil.convertToLocalDate(wrongString);
        assertNotEquals(expectedDate, actual);

    }

}