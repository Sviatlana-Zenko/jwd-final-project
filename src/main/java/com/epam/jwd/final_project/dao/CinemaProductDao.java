package com.epam.jwd.final_project.dao;

import com.epam.jwd.final_project.criteria.CinemaProductCriteria;
import com.epam.jwd.final_project.domain.CinemaProduct;
import com.epam.jwd.final_project.domain.ProductType;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;

import java.sql.Connection;
import java.util.List;


public interface CinemaProductDao extends AppEntityDao<CinemaProduct, CinemaProductCriteria> {

    List<CinemaProduct> findAllByType(ProductType productType);

    CinemaProduct updateByCriteria(CinemaProductCriteria criteria, CinemaProduct cinemaProduct);

    List<CinemaProduct> findConcreteAmount(ProductType type, Long start, Long number);

    Long getNumberOfElements(ProductType productType);

    List<CinemaProduct> findBySearchRequest(String searchRequest);

    List<CinemaProduct> findRecommendations(Connection connection) throws DatabaseInteractionException;


//    List<CinemaProduct> findTopOfProducts(ProductType type);

}
