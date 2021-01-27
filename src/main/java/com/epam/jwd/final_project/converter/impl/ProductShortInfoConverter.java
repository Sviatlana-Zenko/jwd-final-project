package com.epam.jwd.final_project.converter.impl;

import com.epam.jwd.final_project.converter.AppEntityConverter;
import com.epam.jwd.final_project.domain.CinemaProduct;
import com.epam.jwd.final_project.domain.ProductType;
import com.epam.jwd.final_project.dto.CinemaProductShortInfoDto;
import com.epam.jwd.final_project.util.DateConverterUtil;

public class ProductShortInfoConverter implements AppEntityConverter<CinemaProduct, CinemaProductShortInfoDto> {

    public static final ProductShortInfoConverter INSTANCE = new ProductShortInfoConverter();

    private ProductShortInfoConverter() {
    }

    @Override
    public CinemaProduct toEntity(CinemaProductShortInfoDto dto) {
        return new CinemaProduct(dto.getId(),
                ProductType.resolveTypeById(dto.getId()),
                dto.getCurrentRating(),
                dto.getTitle(),
                DateConverterUtil.convertToLocalDate(dto.getReleaseDate()),
                dto.getCountry(),
                dto.getAgeRating(),
                dto.getPosterUrl());
    }

    @Override
    public CinemaProductShortInfoDto toDto(CinemaProduct entity) {
//        return new CinemaProductShortInfoDto(entity.getId(),
//                entity.getType().getId(),
//                entity.getCurrentRating(),
//                entity.getTitle(),
//                DateConverterUtil.convertToFrontFormat(entity.getReleaseDate()),
//                entity.getCountry(),
//                entity.getAgeRating(),
//                entity.getPosterUrl());
        return null;
    }

}
