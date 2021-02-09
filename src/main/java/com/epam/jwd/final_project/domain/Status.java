package com.epam.jwd.final_project.domain;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public enum Status implements AppEntity  {

    NEWBIE(1L),
    REVIEWER(2L),
    EXPERIENCED_REVIEWER(3L),
    ADVANCED_REVIEWER(4L),
    EXPERT(5L);

    private final Long id;

    Status(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name().replace("_", " ");
    }

    public static Status resolveStatusById(Long id) {
        Status statusById = null;
        List<Status> statuses = Arrays.asList(Status.values());
        Predicate<Status> statusPredicate = status -> status.getId() == id;

        if (statuses.stream().anyMatch(statusPredicate)) {
            statusById = statuses.stream()
                    .filter(statusPredicate)
                    .findFirst()
                    .get();
        }

        return statusById;
    }

}
