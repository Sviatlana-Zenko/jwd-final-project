package com.epam.jwd.final_project.service;

import com.epam.jwd.final_project.criteria.CinemaProductCriteria;
import com.epam.jwd.final_project.domain.CinemaProduct;
import com.epam.jwd.final_project.domain.ProductType;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public interface CinemaProductService extends EntityService<CinemaProduct, CinemaProductCriteria> {

    List<CinemaProduct> getProductsBySearchRequest(String searchRequest) throws DatabaseInteractionException;

    List<CinemaProduct> findRecommendations() throws DatabaseInteractionException;

    int getNumberOfProducts(ProductType productType) throws DatabaseInteractionException;

    List<CinemaProduct> findConcreteAmountByType(ProductType type, long startIndex, int number)
            throws DatabaseInteractionException;

    List<CinemaProduct> findByTitle(String title) throws DatabaseInteractionException;

    boolean updateProductRating(Long id) throws DatabaseInteractionException;


}
