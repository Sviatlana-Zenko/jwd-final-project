package com.epam.jwd.final_project.dao;

import com.epam.jwd.final_project.criteria.CinemaProductCriteria;
import com.epam.jwd.final_project.domain.CinemaProduct;
import com.epam.jwd.final_project.domain.ProductType;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;

import java.sql.Connection;
import java.util.List;


public interface CinemaProductDao extends EntityDao<CinemaProduct, CinemaProductCriteria> {

    List<CinemaProduct> findAllByType(ProductType productType, Connection connection)
            throws DatabaseInteractionException;

    CinemaProduct updateByCriteria(CinemaProductCriteria criteria, CinemaProduct cinemaProduct);

    List<CinemaProduct> findConcreteAmountByType(ProductType type, long startIndex,
            int number, Connection connection) throws DatabaseInteractionException;

    int getNumberOfProducts(ProductType productType, Connection connection)
            throws DatabaseInteractionException;

    List<CinemaProduct> findBySearchRequest(String searchRequest, Connection connection)
            throws DatabaseInteractionException;

    List<CinemaProduct> findRecommendations(Connection connection) throws DatabaseInteractionException;


//    List<CinemaProduct> findTopOfProducts(ProductType type);

}
