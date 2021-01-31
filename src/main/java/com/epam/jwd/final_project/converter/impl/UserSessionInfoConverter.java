package com.epam.jwd.final_project.converter.impl;

import com.epam.jwd.final_project.converter.AppEntityConverter;
import com.epam.jwd.final_project.domain.AppUser;
import com.epam.jwd.final_project.domain.Role;
import com.epam.jwd.final_project.dto.UserSessionInfoDto;

public class UserSessionInfoConverter implements AppEntityConverter<AppUser, UserSessionInfoDto> {

    public static final UserSessionInfoConverter INSTANCE = new UserSessionInfoConverter();

    private UserSessionInfoConverter() {
    }

    @Override
    public AppUser toEntity(UserSessionInfoDto dto) {
        return new AppUser(dto.getId(),
                dto.getNickname(),
                Role.resolveRoleByName(dto.getRole()),
                dto.getIsBanned(),
                dto.getReviewedProducts(),
                dto.getRatedReviews());
    }

    @Override
    public UserSessionInfoDto toDto(AppUser entity) {
        return new UserSessionInfoDto(entity.getId(),
                entity.getNickname(),
                entity.getRole().getName(),
                entity.getBanned(),
                entity.getReviewedProducts(),
                entity.getRatedReviews());
    }

}
