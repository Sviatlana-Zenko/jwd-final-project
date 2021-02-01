package com.epam.jwd.final_project.service.impl;

import com.epam.jwd.final_project.criteria.CinemaProductCriteria;
import com.epam.jwd.final_project.dao.impl.CinemaProductDaoImpl;
import com.epam.jwd.final_project.domain.CinemaProduct;
import com.epam.jwd.final_project.domain.ProductType;
import com.epam.jwd.final_project.domain.Review;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.exception.ValidationException;
import com.epam.jwd.final_project.pool.ConnectionPool;
import com.epam.jwd.final_project.service.CinemaProductService;
import com.epam.jwd.final_project.validation.ValidationChain;
import com.epam.jwd.final_project.validation.ValidationChainFactory;
import com.epam.jwd.final_project.validation.ValidationType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CinemaProductServiceImpl implements CinemaProductService {

    public static final CinemaProductServiceImpl INSTANCE = new CinemaProductServiceImpl();

    private CinemaProductServiceImpl() {
    }


    @Override
    public List<CinemaProduct> findRecommendations() throws DatabaseInteractionException {
        return CinemaProductDaoImpl.getInstance()
                .findRecommendations(ConnectionPool.INSTANCE.getAvailableConnection());
    }

    @Override
    public int getNumberOfProducts(ProductType productType) throws DatabaseInteractionException {
        return CinemaProductDaoImpl.getInstance()
                .getNumberOfProducts(productType, ConnectionPool.INSTANCE.getAvailableConnection());
    }

    @Override
    public List<CinemaProduct> findConcreteAmountByType(ProductType type, long startIndex,
            int number) throws DatabaseInteractionException {
        return CinemaProductDaoImpl.getInstance()
                .findConcreteAmountByType(type, startIndex,
                        number, ConnectionPool.INSTANCE.getAvailableConnection());
    }

    @Override
    public List<CinemaProduct> findByTitle(String title) throws DatabaseInteractionException {
        List<CinemaProduct> products = new ArrayList<>();
        List<Long> idList = CinemaProductDaoImpl.getInstance()
                .findIdByTitle(title, ConnectionPool.INSTANCE.getAvailableConnection());

        for (int i = 0; i < idList.size(); i++) {
            Optional<CinemaProduct> found = CinemaProductDaoImpl.getInstance()
                    .findById(idList.get(i), ConnectionPool.INSTANCE.getAvailableConnection());
            if (found.isPresent()) {
                products.add(found.get());
            }
        }

        return products;
    }

    @Override
    public boolean updateProductRating(Long id) throws DatabaseInteractionException {
        boolean wasUpdated;
        List<Integer> marks = ReviewServiceImpl.INSTANCE.getAllProductMarks(id);
        int sum = marks.stream()
                .mapToInt((mark) -> Integer.parseInt(String.valueOf(mark)))
                .sum();

        Double newRating = sum * 1.0 / marks.size();
        wasUpdated = CinemaProductDaoImpl.getInstance()
                .updateProductRating(id, newRating, ConnectionPool.INSTANCE.getAvailableConnection());

        return wasUpdated;
    }

    @Override
    public List<CinemaProduct> getProductsBySearchRequest(String searchRequest)
            throws DatabaseInteractionException {
        return CinemaProductDaoImpl.getInstance()
                .findBySearchRequest(searchRequest, ConnectionPool.INSTANCE.getAvailableConnection());
    }

    @Override
    public boolean create(CinemaProduct product)
            throws ValidationException, DatabaseInteractionException {
        boolean wasCreated;
        ValidationChain<CinemaProduct> chain =
                ValidationChainFactory.INSTANCE.createValidationChain(product);
        List<String> validationErrors = chain.getValidationReport(product, ValidationType.CREATE_OBJECT);

        if (validationErrors.size() == 0) {
            wasCreated = CinemaProductDaoImpl.getInstance()
                    .create(product, ConnectionPool.INSTANCE.getAvailableConnection());
        } else {
            throw new ValidationException(CinemaProduct.class.getSimpleName(), validationErrors);
        }

        return wasCreated;
    }

    @Override
    public List<CinemaProduct> findAll() throws DatabaseInteractionException {
        return CinemaProductDaoImpl.getInstance()
                .findAll(ConnectionPool.INSTANCE.getAvailableConnection());
    }

    @Override
    public Optional<CinemaProduct> findById(Long id) throws DatabaseInteractionException {
        return CinemaProductDaoImpl.getInstance()
                .findById(id, ConnectionPool.INSTANCE.getAvailableConnection());
    }

    @Override
    public boolean delete(CinemaProduct product) throws DatabaseInteractionException {
        boolean wasDeleted = false;
        List<Review> toTransfer = ReviewServiceImpl.INSTANCE
                .findAllForConcreteProductInReview(product.getId());

        if (toTransfer.size() > 0) {
            toTransfer = toTransfer.stream()
                    .peek(review -> review.setProductTitle("deleted movie/TV series"))
                    .collect(Collectors.toList());
            if (ReviewServiceImpl.INSTANCE.transferInHistoryTable(toTransfer)) {
                wasDeleted = CinemaProductDaoImpl.getInstance()
                        .delete(product, ConnectionPool.INSTANCE.getAvailableConnection());
            }
        }

        return wasDeleted;
    }

    @Override
    public CinemaProduct updateByCriteria(CinemaProduct product, CinemaProductCriteria criteria)
            throws ValidationException, DatabaseInteractionException {
        return null;
    }

}
