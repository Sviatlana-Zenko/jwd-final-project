package com.epam.jwd.final_project.domain;

public class ApplicationProperties {

    private String dbUrl;
    private String testDbUrl;
    private String dbUserName;
    private String dbUserPassword;
    private Integer defaultPoolSize;
    private Integer maxPoolSize;
    private String dateFormat;

    public ApplicationProperties() {
    }

    public ApplicationProperties(String dbUrl, String testDbUrl, String dbUserName,
                                 String dbUserPassword, Integer defaultPoolSize,
                                 Integer maxPoolSize, String dateFormat) {
        this.dbUrl = dbUrl;
        this.testDbUrl = testDbUrl;
        this.dbUserName = dbUserName;
        this.dbUserPassword = dbUserPassword;
        this.defaultPoolSize = defaultPoolSize;
        this.maxPoolSize = maxPoolSize;
        this.dateFormat = dateFormat;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getTestDbUrl() {
        return testDbUrl;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public String getDbUserPassword() {
        return dbUserPassword;
    }

    public Integer getDefaultPoolSize() {
        return defaultPoolSize;
    }

    public Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    public String getDateFormat() {
        return dateFormat;
    }

}
