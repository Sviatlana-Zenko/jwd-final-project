package com.epam.jwd.final_project.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppUser extends AbstractAppEntity {
    private String firstName;
    private String lastName;
    private String nickname;
    private LocalDate dateOfBirth;
    private String email;
    private String password;
    private Role role;
    private Status status;
    private Boolean isBanned;
    private List<Genre> favouriteGenres;
    private List<Long> reviewedProducts;
    private Map<Long, Boolean> ratedReviews;

    public AppUser(Long id) {
        super(id);
    }

    public AppUser(Long id, String nickname, String email, Role role, Boolean isBanned) {
        super(id);
        this.nickname = nickname;
        this.email = email;
        this.role = role;
        this.isBanned = isBanned;
        this.reviewedProducts = new ArrayList<>();
        this.ratedReviews = new HashMap<>();
    }

    // +++ для UserSessionDtoConverter
    public AppUser(Long id, String nickname, Role role, Boolean isBanned,
                   List<Long> reviewedProducts, Map<Long, Boolean> ratedReviews) {
        super(id);
        this.nickname = nickname;
        this.role = role;
        this.isBanned = isBanned;
        this.reviewedProducts = reviewedProducts;
        this.ratedReviews = ratedReviews;
    }

    //id, nickname, email, status_id, is_banned

    public AppUser(Long id, String nickname, String email, Status status, Boolean isBanned) {
        super(id);
        this.nickname = nickname;
        this.email = email;
        this.status = status;
        this.isBanned = isBanned;
    }

    public AppUser(String firstName, String lastName, String nickname,
                   LocalDate dateOfBirth, String email, String password,
                   Role role, List<Genre> favouriteGenres) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.role = role;
        this.favouriteGenres = favouriteGenres;
    }


    // +++ для findAll()
    public AppUser(Long id, String firstName, String lastName, String nickname,
                   LocalDate dateOfBirth, String email, String password,
                   Role role, Status status, Boolean isBanned) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
        this.isBanned = isBanned;
        this.favouriteGenres = new ArrayList<>();
        this.reviewedProducts = new ArrayList<>();
        this.ratedReviews = new HashMap<>();
    }

    public AppUser(Long id, String firstName, String lastName, String nickname,
                   LocalDate dateOfBirth, String email, String password,
                   List<Genre> favouriteGenres) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.favouriteGenres = favouriteGenres;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public Status getStatus() {
        return status;
    }

    public Boolean getBanned() {
        return isBanned;
    }

    public List<Genre> getFavouriteGenres() {
        return favouriteGenres;
    }

    // Нужны ли мне здесь setter-ы, toString(), equals() и hashCode()?
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setBanned(Boolean banned) {
        isBanned = banned;
    }

    public void setFavouriteGenres(List<Genre> favouriteGenres) {
        this.favouriteGenres = favouriteGenres;
    }

    public List<Long> getReviewedProducts() {
        return reviewedProducts;
    }
    public void setReviewedProducts(List<Long> reviewedProducts) {
        this.reviewedProducts = reviewedProducts;
    }

    public Map<Long, Boolean> getRatedReviews() {
        return ratedReviews;
    }

    public void setRatedReviews(Map<Long, Boolean> ratedReviews) {
        this.ratedReviews = ratedReviews;
    }


    // Нормально ли такое переопределение?
//    @Override
//    public String toString() {
//        return getClass().getSimpleName() + "{" +
//                super.toString() +
//                ", firstName='" + firstName +
//                "', lastName='" + lastName +
//                "', nickname='" + nickname +
//                "', dateOfBirth=" + dateOfBirth +
//                ", email='" + email +
//                "', password='" + password +
//                "', role=" + role.getName() +
//                ", status=" + status.getName() +
//                ", isBanned=" + isBanned +
//                ", favouriteGenres=" + favouriteGenres +
//                "}";
//    }

    @Override
    public String toString() {
        return "AppUser{" +
                super.toString() +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", status=" + status +
                ", isBanned=" + isBanned +
                ", favouriteGenres=" + favouriteGenres +
                ", reviewedProducts=" + reviewedProducts +
                ", ratedReviews=" + ratedReviews +
                "}";
    }

}