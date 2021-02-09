package com.epam.jwd.final_project.criteria;

import com.epam.jwd.final_project.domain.*;
import java.time.LocalDate;
import java.util.List;

public class CinemaProductCriteria extends Criteria<CinemaProduct> {

    private ProductType type;
    private String title;
    private String description;
    private LocalDate releaseDate;
    private Integer runningTime;
    private String country;
    private Byte ageRating;
    private List<Genre> genres;
    private String starring;
    private String posterUrl;

    public CinemaProductCriteria(Long id, ProductType type, String title, String description,
                                 LocalDate releaseDate, Integer runningTime, String country,
                                 Byte ageRating, List<Genre> genres, String starring, String posterUrl) {
        super(id);
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
    }

    public ProductType getType() {
        return type;
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

    public static CinemaProductCriteriaBuilder cinemaProductCriteriaBuilder() {
        return new CinemaProductCriteriaBuilder();
    }

    public static class CinemaProductCriteriaBuilder extends CriteriaBuilder {
        ProductType type;
        String title;
        String description;
        LocalDate releaseDate;
        Integer runningTime;
        String country;
        Byte ageRating;
        List<Genre> genres;
        String starring;
        String posterUrl;

        public CinemaProductCriteriaBuilder() {
            this.configure();
        }

        protected void configure() {
        }

        public CinemaProductCriteriaBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CinemaProductCriteriaBuilder type(ProductType type) {
            this.id = id;
            return this;
        }

        public CinemaProductCriteriaBuilder title(String title) {
            this.title = title;
            return this;
        }

        public CinemaProductCriteriaBuilder description(String description) {
            this.description = description;
            return this;
        }

        public CinemaProductCriteriaBuilder releaseDate(LocalDate releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public CinemaProductCriteriaBuilder runningTime(Integer runningTime) {
            this.runningTime = runningTime;
            return this;
        }

        public CinemaProductCriteriaBuilder country(String country) {
            this.country = country;
            return this;
        }

        public CinemaProductCriteriaBuilder ageRating(Byte ageRating) {
            this.ageRating = ageRating;
            return this;
        }

        public CinemaProductCriteriaBuilder genres(List<Genre> genres) {
            this.genres = genres;
            return this;
        }

        public CinemaProductCriteriaBuilder starring(String starring) {
            this.starring = starring;
            return this;
        }

        public CinemaProductCriteriaBuilder posterUrl(String posterUrl) {
            this.posterUrl = posterUrl;
            return this;
        }
    }

}
