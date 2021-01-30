package com.epam.jwd.final_project.criteria;

import com.epam.jwd.final_project.domain.AppUser;
import com.epam.jwd.final_project.domain.Genre;
import com.epam.jwd.final_project.domain.Role;
import com.epam.jwd.final_project.domain.Status;

import java.time.LocalDate;
import java.util.List;

public class AppUserCriteria extends Criteria<AppUser> {

    private String firstName;
    private String lastName;
    private String nickname;
    private LocalDate dateOfBirth;
    private String email;
    private String password;
//    private Role role;
//    private Status status;
//    private Boolean isBanned;
    private List<Genre> favouriteGenres;

    public AppUserCriteria(Long id, String firstName, String lastName, String nickname,
                           LocalDate dateOfBirth, String email, String password, /*Role role,
                           Status status, Boolean isBanned,*/ List<Genre> favouriteGenres) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
//        this.role = role;
//        this.status = status;
//        this.isBanned = isBanned;
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

//    public Role getRole() {
//        return role;
//    }
//
//    public Status getStatus() {
//        return status;
//    }
//
//    public Boolean getBanned() {
//        return isBanned;
//    }

    public List<Genre> getFavouriteGenres() {
        return favouriteGenres;
    }

    public static class AppUserCriteriaBuilder extends CriteriaBuilder {
        private String firstName;
        private String lastName;
        private String nickname;
        private LocalDate dateOfBirth;
        private String email;
        private String password;
//        private Role role;
//        private Status status;
//        private Boolean isBanned;
        private List<Genre> favouriteGenres;

        public void firstName(String firstName) {
            this.firstName = firstName;
        }

        public void lastName(String lastName) {
            this.lastName = lastName;
        }

        public void nickname(String nickname) {
            this.nickname = nickname;
        }

        public void dateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public void email(String email) {
            this.email = email;
        }

        public void password(String password) {
            this.password = password;
        }

//        public void role(Role role) {
//            this.role = role;
//        }
//
//        public void status(Status status) {
//            this.status = status;
//        }
//
//        public void isBanned(Boolean isBanned) {
//            this.isBanned = isBanned;
//        }

        public void favouriteGenres(List<Genre> favouriteGenres) {
            this.favouriteGenres = favouriteGenres;
        }


        public AppUserCriteria build() {
            return new AppUserCriteria(id, firstName, lastName, nickname, dateOfBirth,
                                       email, password, /*role, status, isBanned,*/ favouriteGenres);
        }
    }

    @Override
    public String toString() {
        return "AppUserCriteria{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
//                ", role=" + role +
//                ", status=" + status +
//                ", isBanned=" + isBanned +
                ", favouriteGenres=" + favouriteGenres +
                "} " + super.toString();
    }
}
