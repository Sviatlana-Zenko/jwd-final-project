package com.epam.jwd.final_project.dto;

import java.util.List;
import java.util.Map;

public class UserSessionInfoDto {

    private Long id;
    private String nickname;
    private String role;
    private Boolean isBanned;
    private List<Long> reviewedProducts;
    private Map<Long, Boolean> ratedReviews;

    public UserSessionInfoDto(Long id, String nickname, String role, Boolean isBanned,
                              List<Long> reviewedProducts, Map<Long, Boolean> ratedReviews) {
        this.id = id;
        this.nickname = nickname;
        this.role = role;
        this.isBanned = isBanned;
        this.reviewedProducts = reviewedProducts;
        this.ratedReviews = ratedReviews;
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getRole() {
        return role;
    }

    public Boolean getIsBanned() {
        return isBanned;
    }

    public List<Long> getReviewedProducts() {
        return reviewedProducts;
    }

    public Map<Long, Boolean> getRatedReviews() {
        return ratedReviews;
    }
}
