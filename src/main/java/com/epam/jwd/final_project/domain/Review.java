package com.epam.jwd.final_project.domain;

public class Review extends AbstractAppEntity {

    private Long cinemaProductId;
    private Long userId;
    private String productTitle;
    private String userNickname;
    private Byte cinemaProductMark;
    private String reviewSummary;
    private String reviewText;
    private Integer reviewPositiveMarks;
    private Integer reviewNegativeMarks;
    private Boolean hasSpoilers;

    public Review(Long cinemaProductId, Long userId, Byte cinemaProductMark,
                  String reviewSummary, String reviewText, Boolean hasSpoilers) {
        this.cinemaProductId = cinemaProductId;
        this.userId = userId;
        this.cinemaProductMark = cinemaProductMark;
        this.reviewSummary = reviewSummary;
        this.reviewText = reviewText;
        this.hasSpoilers = hasSpoilers;
    }

    public Review(Long id, Byte cinemaProductMark,
                  String reviewSummary, String reviewText) {
        super(id);
        this.cinemaProductMark = cinemaProductMark;
        this.reviewSummary = reviewSummary;
        this.reviewText = reviewText;
    }

    public Review(Long id, Integer reviewPositiveMarks, Integer reviewNegativeMarks) {
        super(id);
        this.reviewPositiveMarks = reviewPositiveMarks;
        this.reviewNegativeMarks = reviewNegativeMarks;
    }

    public Review(Long id, Long cinemaProductId, Long userId,
                  String productTitle, String userNickname,
                  Byte cinemaProductMark, String reviewSummary, String reviewText,
                  Integer reviewPositiveMarks, Integer reviewNegativeMarks, Boolean hasSpoilers) {
        super(id);
        this.cinemaProductId = cinemaProductId;
        this.userId = userId;
        this.productTitle = productTitle;
        this.userNickname = userNickname;
        this.cinemaProductMark = cinemaProductMark;
        this.reviewSummary = reviewSummary;
        this.reviewText = reviewText;
        this.reviewPositiveMarks = reviewPositiveMarks;
        this.reviewNegativeMarks = reviewNegativeMarks;
        this.hasSpoilers = hasSpoilers;
    }

    public Long getCinemaProductId() {
        return cinemaProductId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public Byte getCinemaProductMark() {
        return cinemaProductMark;
    }

    public String getReviewSummary() {
        return reviewSummary;
    }

    public String getReviewText() {
        return reviewText;
    }

    public Integer getReviewPositiveMarks() {
        return reviewPositiveMarks;
    }

    public Integer getReviewNegativeMarks() {
        return reviewNegativeMarks;
    }

    public void setCinemaProductId(Long cinemaProductId) {
        this.cinemaProductId = cinemaProductId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public void setCinemaProductMark(Byte cinemaProductMark) {
        this.cinemaProductMark = cinemaProductMark;
    }

    public void setReviewSummary(String reviewSummary) {
        this.reviewSummary = reviewSummary;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public void setReviewPositiveMarks(Integer reviewPositiveMarks) {
        this.reviewPositiveMarks = reviewPositiveMarks;
    }

    public void setReviewNegativeMarks(Integer reviewNegativeMarks) {
        this.reviewNegativeMarks = reviewNegativeMarks;
    }

    public Boolean getHasSpoilers() {
        return hasSpoilers;
    }

    public void setHasSpoilers(Boolean hasSpoilers) {
        this.hasSpoilers = hasSpoilers;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                super.toString() +
                ", cinemaProductId=" + cinemaProductId +
                ", userId=" + userId +
                ", productTitle='" + productTitle + "'" +
                ", userNickname='" + userNickname + "'" +
                ", cinemaProductMark=" + cinemaProductMark +
                ", reviewSummary='" + reviewSummary + "'" +
                ", reviewText='" + reviewText + "'" +
                ", reviewPositiveMarks=" + reviewPositiveMarks +
                ", reviewNegativeMarks=" + reviewNegativeMarks +
                ", hasSpoilers=" + hasSpoilers +
                "}";
    }

}
