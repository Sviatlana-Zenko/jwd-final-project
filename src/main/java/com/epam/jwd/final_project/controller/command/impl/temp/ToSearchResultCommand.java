package com.epam.jwd.final_project.controller.command.impl.temp;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.impl.ResponseContextImpl;
import com.epam.jwd.final_project.domain.CinemaProduct;
import com.epam.jwd.final_project.service.impl.CinemaProductServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class ToSearchResultCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext req) {
        ResponseContext resp = new ResponseContextImpl(ResponseContext.ResponseType.FORWARD);
        String search = req.getParameter("search");
        System.out.println("search === " + search);
        List<CinemaProduct> products = CinemaProductServiceImpl.INSTANCE.getProductsBySearchRequest(search);

        if (products.size() > 0) {
            int numberOfPages = calculateNumberOfPages(products);
            int pageNumber = req.getParameter("page") == null ? 1 : Integer.valueOf(req.getParameter("page"));
            List<CinemaProduct> productsForOnePage = getProductsForOnePage(products, pageNumber);

            req.setAttributes("pages", numberOfPages);
            req.setAttributes("products", productsForOnePage);
            ((ResponseContextImpl) resp).setPage("/WEB-INF/jsp/search-result.jsp");
        } else {
            ((ResponseContextImpl) resp).setPage("/WEB-INF/jsp/empty-search-result.jsp");
        }

        return resp;
    }

    private int calculateNumberOfPages(List<CinemaProduct> products) {
        int numberOfProducts = products.size();
        int numberOfPages = numberOfProducts % 10 > 0 ? (numberOfProducts / 10) + 1 : numberOfProducts / 10;

        return numberOfPages;
    }

    private List<CinemaProduct> getProductsForOnePage(List<CinemaProduct> products, int pageNumber) {
        int firstIndex = pageNumber * 10 - 10;
        int lastIndex = firstIndex + 9;
        List<CinemaProduct> productsForOnePage = new ArrayList<>();

        for (int i = 0; i < products.size(); i++) {
            if (i >= firstIndex && i <= lastIndex) {
                productsForOnePage.add(products.get(i));
            }
        }

        return productsForOnePage;
    }

}
