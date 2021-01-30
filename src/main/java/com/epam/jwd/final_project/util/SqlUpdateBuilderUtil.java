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
            if (entry.getValue() != null) {
                sqlBuilder.append(quoteFieldNames.get(entry.getKey()) + "='" + entry.getValue() + "', ");
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
            if (entry.getValue() != null) {
                if (entry.getValue().getClass().getSimpleName().equals(String.class.getSimpleName()) ||
                        entry.getValue().getClass().getSimpleName().equals(LocalDate.class.getSimpleName())) {
                    builder.append(userFieldNames.get(entry.getKey()) + "='" + entry.getValue() + "', ");
                } else if (entry.getValue().getClass().getSimpleName().equals(Role.class.getSimpleName())) {
                    builder.append(userFieldNames.get(entry.getKey()) + "=" + ((Role) entry.getValue()).getId() + ", ");
                } else if (entry.getValue().getClass().getSimpleName().equals(Status.class.getSimpleName())) {
                    builder.append(userFieldNames.get(entry.getKey()) + "=" + ((Status) entry.getValue()).getId() + ", ");
                } else if (entry.getValue().getClass().getSimpleName().equals(Long.class.getSimpleName())) {
                    builder.append(userFieldNames.get(entry.getKey()) + "=" + entry.getValue() + ", ");
                } else {
                    if (entry.getValue().equals(true)) {
                        builder.append(userFieldNames.get(entry.getKey()) + "=" + 1 + ", ");
                    } else {
                        builder.append(userFieldNames.get(entry.getKey()) + "=" + 0 + ", ");
                    }
                }
            }
        }

        builder.append("WHERE id=" + user.getId());
        builder.deleteCharAt(builder.lastIndexOf(","));

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
        if (criteria.getPassword() == null) {
            criteriaValues.put("password", criteria.getPassword());
        } else {
            criteriaValues.put("password", PasswordHasherUtil.generatePasswordHash(criteria.getPassword()));
        }
        criteriaValues.put("role", criteria.getRole());
        criteriaValues.put("status", criteria.getStatus());
        criteriaValues.put("isBanned", criteria.getBanned());

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
        fieldNames.put("role", "role_id");
        fieldNames.put("status", "status_id");
        fieldNames.put("isBanned", "is_banned");

        return fieldNames;
    }

}

