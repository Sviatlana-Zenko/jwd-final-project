package com.epam.jwd.final_project.domain;

import java.time.LocalDate;
import java.util.List;

public class Movie extends CinemaProduct {

    private String directedBy;
    private String producedBy;
    private Integer budget;
    private Integer boxOffice;

    public Movie(Long id) {
        super(id);
    }

    public Movie(ProductType type, Double currentRating,
                 String title, String description,
                 LocalDate releaseDate, Integer runningTime,
                 String country, Byte ageRating, List<Genre> genres,
                 String starring, String posterUrl,
                 String directedBy, String producedBy,
                 Integer budget, Integer boxOffice) {
        super(type, currentRating, title, description, releaseDate,
                runningTime, country, ageRating, genres, starring, posterUrl);
        this.directedBy = directedBy;
        this.producedBy = producedBy;
        this.budget = budget;
        this.boxOffice = boxOffice;
    }

    public Movie(ProductType type, String title, String description,
                 LocalDate releaseDate, Integer runningTime,
                 String country, Byte ageRating, List<Genre> genres,
                 String starring, String posterUrl,
                 String directedBy, String producedBy,
                 Integer budget, Integer boxOffice) {
        super(type, title, description, releaseDate,
                runningTime, country, ageRating, genres, starring, posterUrl);
        this.directedBy = directedBy;
        this.producedBy = producedBy;
        this.budget = budget;
        this.boxOffice = boxOffice;
    }

    public Movie(Long id, ProductType type, Double currentRating,
                 String title, String description,
                 LocalDate releaseDate, Integer runningTime,
                 String country, Byte ageRating, String starring,
                 String posterUrl, String directedBy, String producedBy,
                 Integer budget, Integer boxOffice) {
        super(id, type, currentRating, title, description, releaseDate,
                runningTime, country, ageRating, starring, posterUrl);
        this.directedBy = directedBy;
        this.producedBy = producedBy;
        this.budget = budget;
        this.boxOffice = boxOffice;
    }


    public String getDirectedBy() {
        return directedBy;
    }

    public String getProducedBy() {
        return producedBy;
    }

    public Integer getBudget() {
        return budget;
    }

    public Integer getBoxOffice() {
        return boxOffice;
    }

    public void setDirectedBy(String directedBy) {
        this.directedBy = directedBy;
    }

    public void setProducedBy(String producedBy) {
        this.producedBy = producedBy;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public void setBoxOffice(Integer boxOffice) {
        this.boxOffice = boxOffice;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                super.toString() +
                ", directedBy='" + directedBy + "'" +
                ", producedBy='" + producedBy + "'" +
                ", budget=" + budget +
                ", boxOffice=" + boxOffice +
                "}";
    }

}
