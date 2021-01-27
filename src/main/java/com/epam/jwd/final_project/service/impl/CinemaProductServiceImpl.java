package com.epam.jwd.final_project.service.impl;

import com.epam.jwd.final_project.dao.impl.CinemaProductDaoImpl;
import com.epam.jwd.final_project.domain.CinemaProduct;
import com.epam.jwd.final_project.domain.ProductType;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.pool.ConnectionPool;
import com.epam.jwd.final_project.service.CinemaProductService;

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
    public CinemaProduct getById(Long id) {
        Optional<CinemaProduct> product = CinemaProductDaoImpl.getInstance().findById(id);

        if (product.isPresent()) {
            return product.get();
        } else {
            return null;
        }
    }

    @Override
    public List<CinemaProduct> getProductsBySearchRequest(String searchRequest) {
        return CinemaProductDaoImpl.getInstance().findBySearchRequest(searchRequest);
    }

//    @Override
//    public List<CinemaProduct> getProductsForSlideshow() {
//        List<CinemaProduct> products = new ArrayList<>();
//        Long startId = (long) getRandomId(getRange());
//        int numberOfProducts = 5;
//
//        while (numberOfProducts > 0) {
//            Optional<CinemaProduct> product = CinemaProductDaoImpl.getInstance().findById(startId);
//            if (product.isPresent()) {
//                products.add(product.get());
//                numberOfProducts--;
//            }
//            startId++;
//        }
//
//        return products;
//    }

    private int getRandomId(int range) {
        return new Random().nextInt(range);
    }

    private int getRange() {
        return (int) (CinemaProductDaoImpl.getInstance().getNumberOfElements(ProductType.MOVIE) +
                CinemaProductDaoImpl.getInstance().getNumberOfElements(ProductType.TV_SERIES));
    }

}
