package com.epam.jwd.final_project.controller.command.impl;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.ResponseContext.ResponseType;
import com.epam.jwd.final_project.domain.AppUser;
import com.epam.jwd.final_project.domain.CinemaProduct;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.service.impl.AppUserServiceImpl;
import com.epam.jwd.final_project.service.impl.CinemaProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class ShowFoundProductCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShowFoundProductCommand.class);

    @Override
    public ResponseContext execute(RequestContext req) {
        ResponseContext resp = new ResponseContextImpl(ResponseType.FORWARD);
        ((ResponseContextImpl) resp).setPage("/WEB-INF/jsp/database-error.jsp");

        String title = String.valueOf(req.getSession().getAttribute("title"));

        System.out.println("title" + title);
        req.getSession().removeAttribute("title");

        try {
            System.out.println("found product + " + CinemaProductServiceImpl.INSTANCE.findByTitle(title));
        } catch (DatabaseInteractionException e) {
            e.printStackTrace();
        }

//        CinemaProduct found = null;
//        try {
//            Optional<CinemaProduct> product;
//            product = CinemaProductServiceImpl.INSTANCE.findBy();
//            if (product.isPresent()) {
//                found = product.get();
//            }
//            req.setAttributes("product", found);
//            ((ResponseContextImpl) resp).setPage("/WEB-INF/jsp/found-product.jsp");
//        } catch (DatabaseInteractionException e) {
//            LOGGER.error(e.getMessage());
//            ((ResponseContextImpl) resp).setPage("/WEB-INF/jsp/database-error.jsp");
//        }
//
        return resp;
    }
}
