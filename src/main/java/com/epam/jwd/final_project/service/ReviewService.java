package com.epam.jwd.final_project.service;

import com.epam.jwd.final_project.criteria.Criteria;
import com.epam.jwd.final_project.domain.Review;

import java.util.List;

public interface ReviewService extends EntityService<Review, Criteria<Review>> {

    List<Review> getAllForParticularUser(Long userId);

}
