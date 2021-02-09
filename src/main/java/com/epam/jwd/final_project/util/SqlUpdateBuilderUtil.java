package com.epam.jwd.final_project.util;

import com.epam.jwd.final_project.criteria.AppUserCriteria;
import com.epam.jwd.final_project.criteria.QuoteCriteria;
import com.epam.jwd.final_project.domain.AppUser;
import com.epam.jwd.final_project.domain.Quote;
import com.epam.jwd.final_project.domain.Role;
import com.epam.jwd.final_project.domain.Status;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Final class that provides static methods for building SQL
 * queries for application elements updating.
 */
public final class SqlUpdateBuilderUtil {

    private SqlUpdateBuilderUtil() {
    }

    /**
     * Builds and returns the SQL query for updating Quote based on
     * QuoteCriteria not-null fields.
     *
     * @param quoteCriteria object that contains info about
     *                      how {@link Quote} should be updated
     * @param quote object to update
     * @return the SQL update query
     */
    public static String buildSqlQuoteUpdate(QuoteCriteria quoteCriteria, Quote quote) {
        Map<String,Object> criteriaValues = createQuoteCriteriaValuesMap(quoteCriteria);
        Map<String,String> quoteFieldNames = createQuoteFieldsMap();
        StringBuilder sqlBuilder = new StringBuilder("UPDATE quote SET ");

        addFieldValuesToUpdate(sqlBuilder, criteriaValues, quoteFieldNames);
        deleteLastComma(sqlBuilder);
        sqlBuilder.append("WHERE id=" + quote.getId());

        return sqlBuilder.toString();
    }

    /**
     * Builds and returns the SQL query for updating AppUser based on
     * AppUserCriteria not-null fields.
     *
     * @param userCriteria object that contains info about
     *                     how {@link AppUser} should be updated
     * @param user object to update
     * @return the SQL update query
     */
    public static String buildSqlUserUpdate(AppUserCriteria userCriteria, AppUser user) {
        Map<String,Object> criteriaValues = createUserCriteriaValuesMap(userCriteria);
        Map<String,String> userFieldNames = createUserFieldsMap();
        StringBuilder sqlBuilder = new StringBuilder("UPDATE app_user SET ");

        addFieldValuesToUpdate(sqlBuilder, criteriaValues, userFieldNames);
        deleteLastComma(sqlBuilder);
        sqlBuilder.append("WHERE id=" + user.getId());

        return sqlBuilder.toString();
    }

    /**
     * Iterate over the HashMap object that contains criteria fields
     * values for updating. If the field value is not null, then
     * the field and its value are adding to the SQL query for updating.
     *
     * @param sqlBuilder {@link StringBuilder} object that contains the SQL query for updating
     * @param criteriaValues {@link HashMap} object that contains criteria fields
     * @param fieldNames {@link HashMap} object that contains database table fields names
     */
    private static void addFieldValuesToUpdate(StringBuilder sqlBuilder, Map<String,Object> criteriaValues,
                                               Map<String,String> fieldNames) {
        for (Map.Entry<String,Object> entry : criteriaValues.entrySet()) {
            Object obj = entry.getValue();
            if (obj != null) {
                String objClass = obj.getClass().getSimpleName();
                if (objClass.equals(String.class.getSimpleName())) {
                    if (obj.toString().length() > 0) {
                        sqlBuilder.append(fieldNames.get(entry.getKey()) + "='" + obj + "', ");
                    }
                } else if (objClass.equals(LocalDate.class.getSimpleName())) {
                    sqlBuilder.append(fieldNames.get(entry.getKey()) + "='" + obj + "', ");
                } else if (objClass.equals(Role.class.getSimpleName())) {
                    sqlBuilder.append(fieldNames.get(entry.getKey()) + "=" + ((Role) obj).getId() + ", ");
                } else if (objClass.equals(Status.class.getSimpleName())) {
                    sqlBuilder.append(fieldNames.get(entry.getKey()) + "=" + ((Status) obj).getId() + ", ");
                } else if (objClass.equals(Long.class.getSimpleName())) {
                    sqlBuilder.append(fieldNames.get(entry.getKey()) + "=" + obj + ", ");
                } else {
                    if (obj.equals(true)) {
                        sqlBuilder.append(fieldNames.get(entry.getKey()) + "=" + 1 + ", ");
                    } else {
                        sqlBuilder.append(fieldNames.get(entry.getKey()) + "=" + 0 + ", ");
                    }
                }
            }
        }
    }

    /**
     * Searches for the last comma in a StringBuilder object and removes it.
     * If there are no commas, then the object remains unchanged.
     *
     * @param sqlBuilder {@link StringBuilder} object in which we need
     *                                     to find and delete the last comma
     */
    private static void deleteLastComma(StringBuilder sqlBuilder) {
        int lastCommaIndex = sqlBuilder.lastIndexOf(",");
        if (lastCommaIndex != -1) {
            sqlBuilder.deleteCharAt(sqlBuilder.lastIndexOf(","));
        }
    }

    /**
     * Returns a Map in which the keys are names of the QuoteCriteria fields
     * and the values are values of the QuoteCriteria fields respectively.
     *
     * @param criteria {@link QuoteCriteria} object that contains info about
     *                 how {@link Quote} should be updated
     * @return a Map of "field name - field value" mappings
     */
    private static Map<String,Object> createQuoteCriteriaValuesMap(QuoteCriteria criteria) {
        Map<String,Object> criteriaValues = new HashMap<>();
        criteriaValues.put("id", criteria.getId());
        criteriaValues.put("quoteText", criteria.getQuoteText());
        criteriaValues.put("productTitle", criteria.getProductTitle());
        criteriaValues.put("posterUrl", criteria.getPosterUrl());

        return criteriaValues;
    }

    /**
     * Returns a Map in which the keys are names of the QuoteCriteria fields
     * and the values are names of the database table fields respectively.
     *
     * @return a Map of "object field name - database table field name" mappings
     */
    private static Map<String,String> createQuoteFieldsMap() {
        Map<String,String> fieldNames = new HashMap<>();
        fieldNames.put("id", "id");
        fieldNames.put("quoteText", "quote_text");
        fieldNames.put("productTitle", "product_title");
        fieldNames.put("posterUrl", "poster_url");

        return fieldNames;
    }

    /**
     * Returns a Map in which the keys are names of the AppUserCriteria fields
     * and the values are values of the AppUserCriteria fields respectively.
     *
     * @param criteria {@link AppUserCriteria} object that contains info about
     *                 how {@link AppUser} should be updated
     * @return a Map of "field name - field value" mappings
     */
    private static Map<String,Object> createUserCriteriaValuesMap(AppUserCriteria criteria) {
        Map<String,Object> criteriaValues = new HashMap<>();
        criteriaValues.put("id", criteria.getId());
        criteriaValues.put("firstName", criteria.getFirstName());
        criteriaValues.put("lastName", criteria.getLastName());
        criteriaValues.put("nickname", criteria.getNickname());
        criteriaValues.put("dateOfBirth", criteria.getDateOfBirth());
        criteriaValues.put("email", criteria.getEmail());
        if (criteria.getPassword() == null || criteria.getPassword().length() == 0) {
            criteriaValues.put("password", criteria.getPassword());
        } else {
            String hash = PasswordHasherUtil.generatePasswordHash(criteria.getPassword());
            criteriaValues.put("password", hash);
        }

        return criteriaValues;
    }

    /**
     * Returns a Map in which the keys are names of the AppUserCriteria fields
     * and the values are names of the database table fields respectively.
     *
     * @return a Map of "object field name - database table field name" mappings
     */
    private static Map<String,String> createUserFieldsMap() {
        Map<String,String> fieldNames = new HashMap<>();
        fieldNames.put("id", "id");
        fieldNames.put("firstName", "first_name");
        fieldNames.put("lastName", "last_name");
        fieldNames.put("nickname", "nickname");
        fieldNames.put("dateOfBirth", "date_of_birth");
        fieldNames.put("email", "email");
        fieldNames.put("password", "password");

        return fieldNames;
    }

}
