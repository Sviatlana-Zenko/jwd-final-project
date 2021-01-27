package com.epam.jwd.final_project.domain;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public enum Role implements AppEntity {

    ADMIN(1L),
    USER(2L);

    private final Long id;

    Role(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name().toLowerCase().replace("_", " ");
    }

    public static Role resolveRoleByName(String name) {
        return Role.valueOf(name.replace(" ", "-").toUpperCase());
    }

    public static Role resolveRoleById(Long id) {
        Role roleById = null;
        List<Role> roles = Arrays.asList(Role.values());
        Predicate<Role> rolePredicate = role -> role.getId() == id;

        if (roles.stream().anyMatch(rolePredicate)) {
            roleById = roles.stream()
                    .filter(rolePredicate)
                    .findFirst()
                    .get();
        } else {
            // log + throwing error
        }

        return roleById;
    }

}
