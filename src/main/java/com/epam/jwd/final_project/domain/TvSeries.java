package com.epam.jwd.final_project.domain;

import java.time.LocalDate;
import java.util.List;

public class TvSeries extends CinemaProduct {

    private Byte numberOfSeasons;
    private Short numberOfEpisodes;
    private Boolean isFinished;

    public TvSeries(Long id) {
        super(id);
    }

//    public TvSeries(ProductType type, Byte currentRating,
//                    String title, String description,
//                    LocalDate releaseDate, Byte runningTime,
//                    String country, Byte ageRating, List<Genre> genres,
//                    String starring, String posterUrl,
//                    Byte numberOfSeasons, Short numberOfEpisodes, Boolean isFinished) {
//        super(type, currentRating, title, description, releaseDate,
//                runningTime, country, ageRating, genres, starring, posterUrl);
//        this.numberOfSeasons = numberOfSeasons;
//        this.numberOfEpisodes = numberOfEpisodes;
//        this.isFinished = isFinished;
//    }
//
//    public TvSeries(Long id, ProductType type, Byte currentRating,
//                    String title, String description,
//                    LocalDate releaseDate, Byte runningTime,
//                    String country, Byte ageRating, List<Genre> genres,
//                    String starring, String posterUrl, List<Review> reviews,
//                    Byte numberOfSeasons, Short numberOfEpisodes, Boolean isFinished) {
//        super(id, type, currentRating, title, description, releaseDate,
//                runningTime, country, ageRating, genres, starring, posterUrl, reviews);
//        this.numberOfSeasons = numberOfSeasons;
//        this.numberOfEpisodes = numberOfEpisodes;
//        this.isFinished = isFinished;
//    }

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


    //new TvSeries(set.getLong("id"),
    //                    ProductType.TV_SERIES,
    //                    set.getDouble("current_rating"),
    //                    set.getString("title"),
    //                    set.getString("description"),
    //                    DateConverterUtil.convertToLocalDate(set.getString("release_date")),
    //                    set.getInt("running_time"),
    //                    set.getString("country"),
    //                    set.getByte("age_rating"),
    //                    set.getString("starring"),
    //                    set.getString("poster_url"),
    //                    set.getByte("number_of_seasons"),
    //                    set.getShort("number_of_episodes"),
    //                    set.getBoolean("is_finished"));

    public Byte getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public Short getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public Boolean getIsFinished() {
        return isFinished;
    }


    @Override
    public String toString() {
        return "TvSeries{" +
                "numberOfSeasons=" + numberOfSeasons +
                ", numberOfEpisodes=" + numberOfEpisodes +
                ", isFinished=" + isFinished +
                "} " + super.toString();
    }
}
