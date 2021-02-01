package com.epam.jwd.final_project.util;

import com.epam.jwd.final_project.domain.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyReaderUtil {

    private static final Properties properties = new Properties();
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyReaderUtil.class);

    private PropertyReaderUtil() {
    }

    private static void loadProperties() {
        final String propertiesFileName = "app.properties";
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(propertiesFileName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("Loading properties file error", e);
        }
    }

    public static ApplicationProperties getApplicationProperties() {
        loadProperties();

        return new ApplicationProperties(properties.getProperty("dbUrl"),
                properties.getProperty("testDbUrl"),
                properties.getProperty("dbUserName"),
                properties.getProperty("dbUserPassword"),
                Integer.valueOf(properties.getProperty("defaultPoolSize")),
                Integer.valueOf(properties.getProperty("maxPoolSize")),
                properties.getProperty("dateFormat"));
    }

}
