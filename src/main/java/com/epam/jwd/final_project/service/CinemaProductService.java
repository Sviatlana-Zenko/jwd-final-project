package com.epam.jwd.final_project.service;

import com.epam.jwd.final_project.domain.CinemaProduct;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;

import java.sql.Connection;
import java.util.List;

public interface CinemaProductService {

    CinemaProduct getById(Long id);

    List<CinemaProduct> getProductsBySearchRequest(String searchRequest);

    List<CinemaProduct> findRecommendations() throws DatabaseInteractionException;

}
