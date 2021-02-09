package com.epam.jwd.final_project.criteria;

import com.epam.jwd.final_project.domain.Genre;
import com.epam.jwd.final_project.domain.ProductType;
import java.time.LocalDate;
import java.util.List;

public class TvSeriesCriteria extends CinemaProductCriteria {

    private Byte numberOfSeasons;
    private Short numberOfEpisodes;
    private Boolean isFinished;

    public TvSeriesCriteria(Long id, ProductType type, String title, String description,
                            LocalDate releaseDate, Integer runningTime, String country,
                            Byte ageRating, List<Genre> genres, String starring, String posterUrl,
                            Byte numberOfSeasons, Short numberOfEpisodes, Boolean isFinished) {
        super(id, type, title, description, releaseDate, runningTime, country,
                ageRating, genres, starring, posterUrl);
        this.numberOfSeasons = numberOfSeasons;
        this.numberOfEpisodes = numberOfEpisodes;
        this.isFinished = isFinished;
    }

    public Byte getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public Short getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public static class TvSeriesCriteriaBuilder extends CinemaProductCriteriaBuilder {
        private Byte numberOfSeasons;
        private Short numberOfEpisodes;
        private Boolean isFinished;

        public void numberOfSeasons(Byte numberOfSeasons) {
            this.numberOfSeasons = numberOfSeasons;
        }

        public void numberOfEpisodes(Short numberOfEpisodes) {
            this.numberOfEpisodes = numberOfEpisodes;
        }

        public void isFinished(Boolean isFinished) {
            this.isFinished = isFinished;
        }

        public TvSeriesCriteria build() {
            return new TvSeriesCriteria(id, type, title, description, releaseDate,
                    runningTime, country, ageRating, genres, starring, posterUrl,
                    numberOfSeasons, numberOfEpisodes, isFinished);
        }
    }

}
