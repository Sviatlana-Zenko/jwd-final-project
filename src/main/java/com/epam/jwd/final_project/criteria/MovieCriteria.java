package com.epam.jwd.final_project.criteria;

import com.epam.jwd.final_project.domain.Genre;
import com.epam.jwd.final_project.domain.ProductType;

import java.time.LocalDate;
import java.util.List;

public class MovieCriteria extends CinemaProductCriteria {

    private String directedBy;
    private String producedBy;
    private Integer budget;
    private Integer boxOffice;

    public MovieCriteria(Long id, ProductType type, String title, String description,
                         LocalDate releaseDate, Integer runningTime, String country,
                         Byte ageRating, List<Genre> genres, String starring, String posterUrl,
                         String directedBy, String producedBy, Integer budget, Integer boxOffice) {
        super(id, type, title, description, releaseDate, runningTime,
                country, ageRating, genres, starring, posterUrl);
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

    public static class MovieCriteriaBuilder extends CinemaProductCriteriaBuilder {
        private String directedBy;
        private String producedBy;
        private Integer budget;
        private Integer boxOffice;

        public void directedBy(String directedBy) {
            this.directedBy = directedBy;
        }

        public void producedBy(String producedBy) {
            this.producedBy = producedBy;
        }

        public void budget(Integer budget) {
            this.budget = budget;
        }

        public void boxOffice(Integer boxOffice) {
            this.boxOffice = boxOffice;
        }

        public MovieCriteria build() {
            return new MovieCriteria(id, type, title, description, releaseDate,
                    runningTime, country, ageRating, genres, starring, posterUrl, directedBy,
                    producedBy, budget, boxOffice);
        }
    }

}
