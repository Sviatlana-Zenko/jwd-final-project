package com.epam.jwd.final_project.validation;

import com.epam.jwd.final_project.domain.AppUser;

import java.util.List;

public class NicknameValidator extends Validator<AppUser> {

    private static final int MAX_LENGTH = 25;

    public void validate(AppUser user, List<String> validationErrors, ValidationType type) {
        String nickname = user.getNickname();

        if (nickname == null || nickname.length() == 0) {
            if (type == ValidationType.CREATE_OBJECT) {
                validationErrors.add("'nickname' field is not filled");
            }
        } else {
            if (nickname.length() > MAX_LENGTH) {
                validationErrors.add("'nickname' is longer than " +  MAX_LENGTH + " characters");
            }
        }

        super.validate(user, validationErrors, type);
    }

}
