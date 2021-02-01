package com.epam.jwd.final_project.converter;

import com.epam.jwd.final_project.domain.AppEntity;

/**
 * An interface provides methods to convert object to its
 * DTO (Data Transfer Object) version and vice versa.
 *
 * @param <T> the type of elements in this interface to
 *           convert to DTO
 * @param <K> the type of DTO elements in this interface
 */
public interface AppEntityConverter<T extends AppEntity, K> {

    /**
     * Convert DTO object to original object;
     *
     * @param dto DTO object to convert
     * @return original object
     */
    T toEntity(K dto);

    /**
     * Convert original given object to its DTO version.
     *
     * @param entity original object to convert
     * @return DTO version of given object
     */
    K toDto(T entity);

}
