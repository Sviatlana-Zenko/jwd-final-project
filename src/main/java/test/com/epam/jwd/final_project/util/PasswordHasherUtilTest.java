package test.com.epam.jwd.final_project.util;

import com.epam.jwd.final_project.util.PasswordHasherUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordHasherUtilTest {

    private String rightPassword = "Password1";
    private String wrongPassword = "Password2";
    private String hash = "$2a$12$GFMq1efB8laf4ezG092r.OTWggGgP4IFaEDjWaT7MrguqJry3sdia";

    @Test
    void checkPasswordTestTrue() {
        assertTrue(PasswordHasherUtil.checkPassword(rightPassword, hash));
    }

    @Test
    void checkPasswordTestFalse() {
        assertFalse(PasswordHasherUtil.checkPassword(wrongPassword, hash));
    }



}
