package com.epam.jwd.final_project.domain;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public enum Genre implements AppEntity {

    ADVENTURE(1L),
    ANIME(2L),
    BIOGRAPHY(3L),
    CARTOON(4L),
    CRIME(5L),
    DETECTIVE(6L),
    DRAMA(7L),
    FAMILY(8L),
    FANTASY(9L),
    FICTION(10L),
    HISTORICAL(11L),
    HORROR(12L),
    MELODRAMA(13L),
    MILITARY(14L),
    MUSICAL(15L),
    SITCOM(16L),
    SPORT(17L),
    THRILLER(18L),
    WESTERN(19L),
    COMEDY(20L);

    private final Long id;

    Genre(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name().toLowerCase().replace("_", " ");
    }

    public static Genre resolveGenreByName(String name) {
        return Genre.valueOf(name.toUpperCase().replace(" ", "_"));
    }

    public static Genre resolveGenreById(Long id) {
        Genre genreById = null;
        List<Genre> genres = Arrays.asList(Genre.values());
        Predicate<Genre> genrePredicate = genre -> genre.getId() == id;

        if (genres.stream().anyMatch(genrePredicate)) {
            genreById = genres.stream()
                    .filter(genrePredicate)
                    .findFirst()
                    .get();
        }

        return genreById;
    }

    public static List<String> getListOfGenres() {
        List<String> genres = Arrays.asList(Genre.values()).stream()
                .map(genre -> genre.getName())
                .collect(Collectors.toList());

        return genres;
    }

}
