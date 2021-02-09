package com.epam.jwd.final_project.controller.command.impl;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.ResponseContext.ResponseType;
import com.epam.jwd.final_project.domain.CinemaProduct;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.service.impl.CinemaProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class ToSearchResultCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(ToSearchResultCommand.class);

    @Override
    public ResponseContext execute(RequestContext req) {
        ResponseContext resp = new ResponseContextImpl(ResponseType.FORWARD);
        String search = (String) req.getSession().getAttribute("search");
        List<CinemaProduct> products;

        try {
            products = CinemaProductServiceImpl.INSTANCE.getProductsBySearchRequest(search);
            if (products != null && !products.isEmpty()) {
                req.setAttributes("products", products);
                ((ResponseContextImpl) resp).setPage("/WEB-INF/jsp/search-result.jsp");
            } else {
                ((ResponseContextImpl) resp).setPage("/WEB-INF/jsp/empty-search-result.jsp");
            }
        } catch (DatabaseInteractionException e) {
            LOGGER.error(e.getMessage());
            ((ResponseContextImpl) resp).setPage("/WEB-INF/jsp/database-error.jsp");
        }

        return resp;
    }

}
