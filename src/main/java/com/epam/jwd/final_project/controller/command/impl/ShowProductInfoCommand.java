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

import java.util.Optional;

public class ShowProductInfoCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShowProductInfoCommand.class);

    @Override
    public ResponseContext execute(RequestContext req) {
        ResponseContext resp = new ResponseContextImpl(ResponseType.FORWARD);
        String productId = req.getParameter("id");

        try {
            Optional<CinemaProduct> product = CinemaProductServiceImpl.INSTANCE.findById(Long.valueOf(productId));
            if (product.isPresent()) {
                req.setAttributes("product", product.get());
                ((ResponseContextImpl) resp).setPage("/WEB-INF/jsp/product-info.jsp");
            } else {
                ((ResponseContextImpl) resp).setPage("/WEB-INF/jsp/database-error.jsp");
            }
        } catch (DatabaseInteractionException e) {
            LOGGER.error(e.getMessage());
            ((ResponseContextImpl) resp).setPage("/WEB-INF/jsp/database-error.jsp");
        }

        return resp;
    }
}
