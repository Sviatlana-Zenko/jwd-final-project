package com.epam.jwd.final_project.service;

import com.epam.jwd.final_project.criteria.AppUserCriteria;
import com.epam.jwd.final_project.criteria.Criteria;
import com.epam.jwd.final_project.domain.AppUser;
import com.epam.jwd.final_project.domain.Review;
import com.epam.jwd.final_project.domain.Status;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.exception.ValidationException;

import java.sql.Connection;
import java.util.Optional;

public interface UserService extends EntityService<AppUser, AppUserCriteria> {

    Optional<AppUser> findByEmail(String email) throws DatabaseInteractionException;

    boolean checkPassword(AppUser user, String passwordToCheck);

    Status updateUserStatus(AppUser user) throws DatabaseInteractionException, ValidationException;

    boolean addReviewedProduct(Long userId, Long productId) throws DatabaseInteractionException;

    boolean addRatedReview(Long userId, Long reviewId, boolean isPositiveMark)
            throws DatabaseInteractionException;



    boolean createUserReview(AppUser appUser, Review review);

}
