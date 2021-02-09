package com.epam.jwd.final_project.util;

import com.epam.jwd.final_project.domain.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Final class that provides static methods for creating
 * ApplicationProperties object that contains full info about
 * all properties required for the application working.
 */
public final class PropertyReaderUtil {

    private static final Properties properties = new Properties();
    /**
     * A constant that is the Logger class object to log information
     * about exceptions that may arise during the creation of
     * an {@link ApplicationProperties} object.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyReaderUtil.class);

    private PropertyReaderUtil() {
    }

    /**
     * Returns created ApplicationProperties object.
     *
     * @return created ApplicationProperties object
     */
    public static ApplicationProperties getApplicationProperties() {
        loadProperties();

        return new ApplicationProperties(properties.getProperty("dbUrl"),
                properties.getProperty("testDbUrl"),
                properties.getProperty("dbUserName"),
                properties.getProperty("dbUserPassword"),
                Integer.valueOf(properties.getProperty("defaultPoolSize")),
                Integer.valueOf(properties.getProperty("maxPoolSize")),
                properties.getProperty("dateFormat"),
                properties.getProperty("appEmail"),
                properties.getProperty("emailPassword"));
    }

    /**
     * Loads properties from a specified file into a Properties object.
     */
    private static void loadProperties() {
        final String propertiesFileName = "app.properties";
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(propertiesFileName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("Loading properties file error", e);
        }
    }

}
