package com.epam.jwd.final_project.domain;

public class ApplicationProperties {

    private String dbUrl;
    private String testDbUrl;
    private String dbUserName;
    private String dbUserPassword;
    private Integer defaultPoolSize;
    private Integer maxPoolSize;
    private String dateFormat;
    private String appEmail;
    private String emailPassword;

    public ApplicationProperties(String dbUrl, String testDbUrl, String dbUserName,
                                 String dbUserPassword, Integer defaultPoolSize,
                                 Integer maxPoolSize, String dateFormat,
                                 String appEmail, String emailPassword) {
        this.dbUrl = dbUrl;
        this.testDbUrl = testDbUrl;
        this.dbUserName = dbUserName;
        this.dbUserPassword = dbUserPassword;
        this.defaultPoolSize = defaultPoolSize;
        this.maxPoolSize = maxPoolSize;
        this.dateFormat = dateFormat;
        this.appEmail = appEmail;
        this.emailPassword = emailPassword;
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

    public String getAppEmail() {
        return appEmail;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "dbUrl='" + dbUrl + "'" +
                ", testDbUrl='" + testDbUrl + "'" +
                ", dbUserName='" + dbUserName + "'" +
                ", dbUserPassword='" + dbUserPassword + "'" +
                ", defaultPoolSize=" + defaultPoolSize +
                ", maxPoolSize=" + maxPoolSize +
                ", dateFormat='" + dateFormat + "'" +
                ", appEmail='" + appEmail + "'" +
                ", emailPassword='" + emailPassword + "'" +
                "}";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((appEmail == null) ? 0 : appEmail.hashCode());
        result = prime * result + ((dateFormat == null) ? 0 : dateFormat.hashCode());
        result = prime * result + ((dbUrl == null) ? 0 : dbUrl.hashCode());
        result = prime * result + ((dbUserName == null) ? 0 : dbUserName.hashCode());
        result = prime * result + ((dbUserPassword == null) ? 0 : dbUserPassword.hashCode());
        result = prime * result + ((defaultPoolSize == null) ? 0 : defaultPoolSize.hashCode());
        result = prime * result + ((emailPassword == null) ? 0 : emailPassword.hashCode());
        result = prime * result + ((maxPoolSize == null) ? 0 : maxPoolSize.hashCode());
        result = prime * result + ((testDbUrl == null) ? 0 : testDbUrl.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ApplicationProperties other = (ApplicationProperties) obj;
        if (appEmail == null) {
            if (other.appEmail != null)
                return false;
        } else if (!appEmail.equals(other.appEmail))
            return false;
        if (dateFormat == null) {
            if (other.dateFormat != null)
                return false;
        } else if (!dateFormat.equals(other.dateFormat))
            return false;
        if (dbUrl == null) {
            if (other.dbUrl != null)
                return false;
        } else if (!dbUrl.equals(other.dbUrl))
            return false;
        if (dbUserName == null) {
            if (other.dbUserName != null)
                return false;
        } else if (!dbUserName.equals(other.dbUserName))
            return false;
        if (dbUserPassword == null) {
            if (other.dbUserPassword != null)
                return false;
        } else if (!dbUserPassword.equals(other.dbUserPassword))
            return false;
        if (defaultPoolSize == null) {
            if (other.defaultPoolSize != null)
                return false;
        } else if (!defaultPoolSize.equals(other.defaultPoolSize))
            return false;
        if (emailPassword == null) {
            if (other.emailPassword != null)
                return false;
        } else if (!emailPassword.equals(other.emailPassword))
            return false;
        if (maxPoolSize == null) {
            if (other.maxPoolSize != null)
                return false;
        } else if (!maxPoolSize.equals(other.maxPoolSize))
            return false;
        if (testDbUrl == null) {
            if (other.testDbUrl != null)
                return false;
        } else if (!testDbUrl.equals(other.testDbUrl))
            return false;
        return true;
    }

}
