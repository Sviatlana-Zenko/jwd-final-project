package com.epam.jwd.final_project.util;

import com.epam.jwd.final_project.criteria.AppUserCriteria;
import com.epam.jwd.final_project.criteria.QuoteCriteria;
import com.epam.jwd.final_project.domain.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public final class SqlUpdateBuilderUtil {

    private SqlUpdateBuilderUtil() {
    }

    public static String buildSqlQuoteUpdate(QuoteCriteria quoteCriteria, Quote quote) {
        Map<String, Object> criteriaValues = createQuoteCriteriaValuesMap(quoteCriteria);
        Map<String, String> quoteFieldNames = createQuoteFieldsMap();
        StringBuilder sqlBuilder = new StringBuilder("UPDATE quote SET ");

        for (Map.Entry<String,Object> entry : criteriaValues.entrySet()) {
            Object obj = entry.getValue();
            if (obj != null) {
                sqlBuilder.append(quoteFieldNames.get(entry.getKey()) + "='" + obj + "', ");
            }
        }

        sqlBuilder.append("WHERE id=" + quote.getId());
        int lastCommaIndex = sqlBuilder.lastIndexOf(",");
        if (lastCommaIndex != -1) {
            sqlBuilder.deleteCharAt(sqlBuilder.lastIndexOf(","));
        }

        return sqlBuilder.toString();
    }

    public static String buildSqlUserUpdate(AppUserCriteria userCriteria, AppUser user) {
        Map<String, Object> criteriaValues = createUserCriteriaValuesMap(userCriteria);
        Map<String, String> userFieldNames = createUserFieldsMap();
        StringBuilder builder = new StringBuilder("UPDATE app_user SET ");

        for (Map.Entry<String,Object> entry : criteriaValues.entrySet()) {
            Object obj = entry.getValue();
            if (obj != null) {
                String objClass = obj.getClass().getSimpleName();
                if (objClass.equals(String.class.getSimpleName())) {
                    if (obj.toString().length() > 0) {
                        builder.append(userFieldNames.get(entry.getKey()) + "='" + obj + "', ");
                    }
                } else if (objClass.equals(LocalDate.class.getSimpleName())) {
                    builder.append(userFieldNames.get(entry.getKey()) + "='" + obj + "', ");
                } else if (objClass.equals(Role.class.getSimpleName())) {
                    builder.append(userFieldNames.get(entry.getKey()) + "=" + ((Role) obj).getId() + ", ");
                } else if (objClass.equals(Status.class.getSimpleName())) {
                    builder.append(userFieldNames.get(entry.getKey()) + "=" + ((Status) obj).getId() + ", ");
                } else if (objClass.equals(Long.class.getSimpleName())) {
                    builder.append(userFieldNames.get(entry.getKey()) + "=" + obj + ", ");
                } else {
                    if (obj.equals(true)) {
                        builder.append(userFieldNames.get(entry.getKey()) + "=" + 1 + ", ");
                    } else {
                        builder.append(userFieldNames.get(entry.getKey()) + "=" + 0 + ", ");
                    }
                }
            }
        }

        builder.append("WHERE id=" + user.getId());
        int lastCommaIndex = builder.lastIndexOf(",");
        if (lastCommaIndex != -1) {
            builder.deleteCharAt(builder.lastIndexOf(","));
        }

        return builder.toString();
    }

    private static Map<String, Object> createQuoteCriteriaValuesMap(QuoteCriteria criteria) {
        Map<String, Object> criteriaValues = new HashMap<>();
        criteriaValues.put("id", criteria.getId());
        criteriaValues.put("quoteText", criteria.getQuoteText());
        criteriaValues.put("productTitle", criteria.getProductTitle());
        criteriaValues.put("posterUrl", criteria.getPosterUrl());

        return criteriaValues;
    }

    private static Map<String, String> createQuoteFieldsMap() {
        Map<String, String> fieldNames = new HashMap<>();
        fieldNames.put("id", "id");
        fieldNames.put("quoteText", "quote_text");
        fieldNames.put("productTitle", "product_title");
        fieldNames.put("posterUrl", "poster_url");

        return fieldNames;
    }

    private static Map<String, Object> createUserCriteriaValuesMap(AppUserCriteria criteria) {
        Map<String, Object> criteriaValues = new HashMap<>();
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

    private static Map<String, String> createUserFieldsMap() {
        Map<String, String> fieldNames = new HashMap<>();
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

