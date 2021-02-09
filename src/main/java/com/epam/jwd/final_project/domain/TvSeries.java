package com.epam.jwd.final_project.domain;

import java.time.LocalDate;

public class TvSeries extends CinemaProduct {

    private Byte numberOfSeasons;
    private Short numberOfEpisodes;
    private Boolean isFinished;

    public TvSeries(Long id) {
        super(id);
    }

    public TvSeries(Long id, ProductType type, Double currentRating,
                    String title, String description, LocalDate releaseDate,
                    Integer runningTime, String country, Byte ageRating,
                    String starring, String posterUrl, Byte numberOfSeasons,
                    Short numberOfEpisodes, Boolean isFinished) {
        super(id, type, currentRating, title, description, releaseDate,
                runningTime, country, ageRating, starring, posterUrl);
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

    public Boolean getIsFinished() {
        return isFinished;
    }

    public void setNumberOfSeasons(Byte numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public void setNumberOfEpisodes(Short numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                super.toString() +
                "numberOfSeasons=" + numberOfSeasons +
                ", numberOfEpisodes=" + numberOfEpisodes +
                ", isFinished=" + isFinished +
                "}";
    }

}
