package com.epam.jwd.final_project.controller.command.impl;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.ResponseContext.ResponseType;
import com.epam.jwd.final_project.domain.CinemaProduct;
import com.epam.jwd.final_project.domain.ProductType;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.service.impl.CinemaProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowProductsCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShowProductsCommand.class);

    @Override
    public ResponseContext execute(RequestContext req) {
        ResponseContext resp = new ResponseContextImpl(ResponseType.FORWARD);
        String type = req.getParameter("type");
        String pageParam = req.getParameter("page");
        int numberOfProdOnPage = 10;
        int requestedPage = pageParam == null || pageParam.length() == 0 ? 1 : Integer.parseInt(pageParam);
        long startIndex = requestedPage * numberOfProdOnPage - numberOfProdOnPage;
        Map<String, String> sortFields = createMapOfFieldsToSort();
        Map<String, String> sortDirs = createMapOfDirectionsToSort();
        List<CinemaProduct> products;
        long numberOfProducts;
        int numberOfPages;

        setDefaultSortingParameters(req);
        setCustomFieldToSort(req);
        setCustomDirectionToSort(req);

        try {
            if (type.equals("movie")) {
                numberOfProducts = CinemaProductServiceImpl.INSTANCE.getNumberOfProducts(ProductType.MOVIE);
                numberOfPages = calculateNumberOfPages(numberOfProdOnPage, numberOfProducts);
                products = CinemaProductServiceImpl.INSTANCE
                        .findConcreteAmountByType(ProductType.MOVIE, startIndex, numberOfProdOnPage,
                                sortFields.get(req.getSession().getAttribute("field")),
                                sortDirs.get(req.getSession().getAttribute("dir")));
            } else {
                numberOfProducts = CinemaProductServiceImpl.INSTANCE.getNumberOfProducts(ProductType.TV_SERIES);
                numberOfPages = calculateNumberOfPages(numberOfProdOnPage, numberOfProducts);
                products = CinemaProductServiceImpl.INSTANCE
                        .findConcreteAmountByType(ProductType.TV_SERIES, startIndex, numberOfProdOnPage,
                                sortFields.get(req.getSession().getAttribute("field")),
                                sortDirs.get(req.getSession().getAttribute("dir")));
            }
            req.setAttributes("pages" , numberOfPages);
            req.setAttributes("products", products);
            ((ResponseContextImpl) resp).setPage("/WEB-INF/jsp/products-view.jsp");
        } catch (DatabaseInteractionException e) {
            LOGGER.error(e.getMessage());
            ((ResponseContextImpl) resp).setPage("/WEB-INF/jsp/database-error.jsp");
        }

        return resp;
    }

    private int calculateNumberOfPages(int numberOfProdOnPage, long numberOfProducts) {
        if (numberOfProducts % numberOfProdOnPage > 0) {
            return (int) (numberOfProducts / numberOfProdOnPage) + 1;
        } else {
            return (int) numberOfProducts / numberOfProdOnPage;
        }
    }

    private void setDefaultSortingParameters(RequestContext req) {
        if (req.getSession().getAttribute("field") == null &
                req.getSession().getAttribute("dir") == null) {
            req.getSession().setAttribute("field", "rating");
            req.getSession().setAttribute("dir", "descending");
        }
    }

    private void setCustomFieldToSort(RequestContext req) {
        String field = req.getParameter("field");
        if (field != null) {
            req.getSession().setAttribute("field", field);
        }
    }

    private void setCustomDirectionToSort(RequestContext req) {
        String dir = req.getParameter("dir");
        if (dir != null) {
            req.getSession().setAttribute("dir", dir);
        }
    }

    private Map<String,String> createMapOfFieldsToSort() {
        Map<String,String> sortFields = new HashMap<>();
        sortFields.put("rating", "current_rating");
        sortFields.put("release", "release_date");
        sortFields.put("title", "title");

        return sortFields;
    }

    private Map<String,String> createMapOfDirectionsToSort() {
        Map<String,String> sortDirs = new HashMap<>();
        sortDirs.put("descending", "DESC");
        sortDirs.put("ascending", "ASC");

        return sortDirs;
    }

}
