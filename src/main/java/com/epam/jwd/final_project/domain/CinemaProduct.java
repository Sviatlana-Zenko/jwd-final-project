package com.epam.jwd.final_project.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CinemaProduct extends AbstractAppEntity {

    private ProductType type; //+
    private Double currentRating; //+
    private String title; //+
    private String description; //+
    private LocalDate releaseDate; //+
    private Integer runningTime; //+
    private String country; //+
    private Byte ageRating; //+
    private List<Genre> genres; //+
    private String starring; //+
    private String posterUrl; //+
//    private List<Review> reviews;

    public CinemaProduct() {
    }

    public CinemaProduct(Long id) {
        super(id);
    }

    //++++
    public CinemaProduct(Long id, ProductType type, Double currentRating,
                         String title, LocalDate releaseDate, String country,
                         Byte ageRating, String posterUrl) {
        super(id);
        this.type = type;
        this.currentRating = currentRating;
        this.title = title;
        this.releaseDate = releaseDate;
        this.country = country;
        this.ageRating = ageRating;
        this.posterUrl = posterUrl;
    }

    public CinemaProduct(ProductType type, Double currentRating,
                         String title, String description,
                         LocalDate releaseDate, Integer runningTime,
                         String country, Byte ageRating, List<Genre> genres,
                         String starring, String posterUrl) {
        this.type = type;
        this.currentRating = currentRating;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.runningTime = runningTime;
        this.country = country;
        this.ageRating = ageRating;
        this.genres = genres;
        this.starring = starring;
        this.posterUrl = posterUrl;
//        this.reviews = new ArrayList<>();
    }

    public CinemaProduct(ProductType type, String title, String description,
                         LocalDate releaseDate, Integer runningTime,
                         String country, Byte ageRating, List<Genre> genres,
                         String starring, String posterUrl) {
        this.type = type;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.runningTime = runningTime;
        this.country = country;
        this.ageRating = ageRating;
        this.genres = genres;
        this.starring = starring;
        this.posterUrl = posterUrl;
//        this.reviews = new ArrayList<>();
    }

    //++++
    public CinemaProduct(Long id, ProductType type, Double currentRating, String title,
                         String description, LocalDate releaseDate, Integer runningTime,
                         String country, Byte ageRating, String starring, String posterUrl) {
        super(id);
        this.type = type;
        this.currentRating = currentRating;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.runningTime = runningTime;
        this.country = country;
        this.ageRating = ageRating;
        this.genres = new ArrayList<>();
        this.starring = starring;
        this.posterUrl = posterUrl;
//        this.reviews = new ArrayList<>();
    }

    //для рекомендованных продуктов
    public CinemaProduct(Long id, Double currentRating, String title, String posterUrl) {
        super(id);
        this.currentRating = currentRating;
        this.title = title;
        this.posterUrl = posterUrl;
    }

    public CinemaProduct(Long id, ProductType type, Double currentRating,
                         String title, LocalDate releaseDate,
                         String country, String posterUrl) {
        super(id);
        this.type = type;
        this.currentRating = currentRating;
        this.title = title;
        this.releaseDate = releaseDate;
        this.country = country;
        this.posterUrl = posterUrl;
    }

    public ProductType getType() {
        return type;
    }

    public Double getCurrentRating() {
        return currentRating;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Integer getRunningTime() {
        return runningTime;
    }

    public String getCountry() {
        return country;
    }

    public Byte getAgeRating() {
        return ageRating;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getStarring() {
        return starring;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

//    public List<Review> getReviews() {
//        return reviews;
//    }

    // Нужны ли мне здесь setter-ы, toString(), equals() и hashCode()?
    public void setType(ProductType type) {
        this.type = type;
    }

    public void setCurrentRating(Double currentRating) {
        this.currentRating = currentRating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setRunningTime(Integer runningTime) {
        this.runningTime = runningTime;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAgeRating(Byte ageRating) {
        this.ageRating = ageRating;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void setStarring(String starring) {
        this.starring = starring;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

//    public void setReviews(List<Review> reviews) {
//        this.reviews = reviews;
//    }

    // Нормально ли такое переопределение?
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "type=" + type +
                ", currentRating=" + currentRating +
                ", title='" + title +
                "', description='" + description +
                "', releaseDate=" + releaseDate +
                ", runningTime=" + runningTime +
                ", country='" + country +
                "', ageRating=" + ageRating +
                ", genres=" + genres +
                ", starring='" + starring +
                "', posterUrl='" + posterUrl +
                "}";
    }

}
