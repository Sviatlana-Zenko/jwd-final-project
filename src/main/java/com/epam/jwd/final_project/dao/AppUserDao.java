package com.epam.jwd.final_project.dao;

import com.epam.jwd.final_project.criteria.AppUserCriteria;
import com.epam.jwd.final_project.domain.AppUser;
import com.epam.jwd.final_project.domain.Status;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface AppUserDao extends EntityDao<AppUser, AppUserCriteria> {

    Optional<AppUser> findUserByEmail(String email, Connection connection) throws DatabaseInteractionException;

    List<Integer> getPositiveMarks(AppUser user, Connection connection) throws DatabaseInteractionException;

    List<Integer> getNegativeMarks(AppUser user, Connection connection) throws DatabaseInteractionException;

    boolean updateStatus(AppUser user, Status status, Connection connection) throws DatabaseInteractionException;

    boolean checkIfNickNameExists(String nickname) throws DatabaseInteractionException;

    boolean checkIfEmailExists(String email) throws DatabaseInteractionException;







    boolean addRatedReview(Long userId, Long reviewId, boolean isPositiveMark);

    boolean addReviewedProduct(Long userId, Long productId);

}
