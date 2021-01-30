package com.epam.jwd.final_project.service.impl;

import com.epam.jwd.final_project.criteria.CinemaProductCriteria;
import com.epam.jwd.final_project.dao.impl.CinemaProductDaoImpl;
import com.epam.jwd.final_project.domain.CinemaProduct;
import com.epam.jwd.final_project.domain.ProductType;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.exception.ValidationException;
import com.epam.jwd.final_project.pool.ConnectionPool;
import com.epam.jwd.final_project.service.CinemaProductService;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class CinemaProductServiceImpl implements CinemaProductService {

    public static final CinemaProductServiceImpl INSTANCE = new CinemaProductServiceImpl();

    private CinemaProductServiceImpl() {
    }



    @Override
    public List<CinemaProduct> findRecommendations() throws DatabaseInteractionException {
        return CinemaProductDaoImpl.getInstance().findRecommendations(
                ConnectionPool.INSTANCE.getAvailableConnection());
    }

    @Override
    public int getNumberOfProducts(ProductType productType) throws DatabaseInteractionException {
        return CinemaProductDaoImpl.getInstance().getNumberOfProducts(
                productType, ConnectionPool.INSTANCE.getAvailableConnection());
    }

    @Override
    public List<CinemaProduct> findConcreteAmountByType(ProductType type, long startIndex,
            int number) throws DatabaseInteractionException {
        return CinemaProductDaoImpl.getInstance().findConcreteAmountByType(
                type, startIndex, number, ConnectionPool.INSTANCE.getAvailableConnection());
    }

    @Override
    public List<CinemaProduct> getProductsBySearchRequest(String searchRequest) throws DatabaseInteractionException {
        return CinemaProductDaoImpl.getInstance().findBySearchRequest(searchRequest, ConnectionPool.INSTANCE.getAvailableConnection());
    }

    @Override
    public boolean create(CinemaProduct product) throws ValidationException, DatabaseInteractionException {
        return false;
    }

    @Override
    public List<CinemaProduct> findAll() throws DatabaseInteractionException {
        return null;
    }

    @Override
    public Optional<CinemaProduct> findById(Long id) throws DatabaseInteractionException {
        return CinemaProductDaoImpl.getInstance().findById(id, ConnectionPool.INSTANCE.getAvailableConnection());
    }

    @Override
    public boolean delete(CinemaProduct product) throws DatabaseInteractionException {
        return false;
    }

    @Override
    public CinemaProduct updateByCriteria(CinemaProduct product, CinemaProductCriteria criteria) throws ValidationException, DatabaseInteractionException {
        return null;
    }
}
