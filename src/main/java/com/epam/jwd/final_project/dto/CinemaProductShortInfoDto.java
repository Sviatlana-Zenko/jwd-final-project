package com.epam.jwd.final_project.dto;

public class CinemaProductShortInfoDto {

    private Long id;
    private Long typeId;
    private Double currentRating;
    private String title;
    private String releaseDate;
    private String country;
    private Byte ageRating;
    private String posterUrl;

    public CinemaProductShortInfoDto(Long id, Long typeId, Double currentRating,
                                     String title, String releaseDate, String country,
                                     Byte ageRating, String posterUrl) {
        this.id = id;
        this.typeId = typeId;
        this.currentRating = currentRating;
        this.title = title;
        this.releaseDate = releaseDate;
        this.country = country;
        this.ageRating = ageRating;
        this.posterUrl = posterUrl;
    }

    public Long getId() {
        return id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public Double getCurrentRating() {
        return currentRating;
    }

    public String getTitle() {
        return title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getCountry() {
        return country;
    }

    public Byte getAgeRating() {
        return ageRating;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public void setCurrentRating(Double currentRating) {
        this.currentRating = currentRating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAgeRating(Byte ageRating) {
        this.ageRating = ageRating;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", typeId=" + typeId +
                ", currentRating=" + currentRating +
                ", title='" + title + "'" +
                ", releaseDate='" + releaseDate + "'" +
                ", country='" + country + "'" +
                ", ageRating=" + ageRating +
                ", posterUrl='" + posterUrl + "'" +
                "}";
    }

}
