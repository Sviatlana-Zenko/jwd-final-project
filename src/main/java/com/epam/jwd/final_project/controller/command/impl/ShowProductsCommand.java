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
import java.util.List;

public class ShowProductsCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShowProductsCommand.class);

    @Override
    public ResponseContext execute(RequestContext req) {
        ResponseContext resp = new ResponseContextImpl(ResponseType.FORWARD);

        String type = req.getParameter("type");
        String pageParam = req.getParameter("page");
        int numberOfProductsOnPage = 10;
        int requestedPage = pageParam == null ? 1 : Integer.parseInt(pageParam);
        long startIndex = requestedPage * numberOfProductsOnPage - numberOfProductsOnPage;
        List<CinemaProduct> products;
        long numberOfProducts;
        int numberOfPages;

        try {
            if (type.equals("movie")) {
                numberOfProducts = CinemaProductServiceImpl.INSTANCE.getNumberOfProducts(ProductType.MOVIE);
                numberOfPages = (int) (numberOfProducts % 10 > 0 ? (numberOfProducts / 10) + 1 : numberOfProducts / 10);
                products = CinemaProductServiceImpl.INSTANCE.findConcreteAmountByType(
                        ProductType.MOVIE, startIndex, numberOfProductsOnPage);
            } else {
                numberOfProducts = CinemaProductServiceImpl.INSTANCE.getNumberOfProducts(ProductType.TV_SERIES);
                numberOfPages = (int) (numberOfProducts % 10 > 0 ? (numberOfProducts / 10) + 1 : numberOfProducts / 10);
                products = CinemaProductServiceImpl.INSTANCE.findConcreteAmountByType(
                        ProductType.TV_SERIES, startIndex, numberOfProductsOnPage);
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
}
