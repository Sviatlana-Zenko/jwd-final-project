package com.epam.jwd.final_project.converter;

import com.epam.jwd.final_project.domain.AppEntity;

public interface AppEntityConverter<T extends AppEntity, K> {

    T toEntity(K dto);

    K toDto(T entity);

}
