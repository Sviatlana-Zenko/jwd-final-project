package com.epam.jwd.final_project.domain;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public enum ProductType implements AppEntity {

    MOVIE(1L),
    TV_SERIES(2L);

    private final Long id;

    ProductType(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public static ProductType resolveTypeById(Long id) {
        ProductType typeById = null;
        List<ProductType> types = Arrays.asList(ProductType.values());
        Predicate<ProductType> typePredicate = type -> type.getId() == id;

        if (types.stream().anyMatch(typePredicate)) {
            typeById = types.stream()
                    .filter(typePredicate)
                    .findFirst()
                    .get();
        }

        return typeById;
    }

}

