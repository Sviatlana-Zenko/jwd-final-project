package com.epam.jwd.final_project.controller.command.impl;

import com.epam.jwd.final_project.context.impl.RatingContext;
import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.ResponseContext.ResponseType;
import com.epam.jwd.final_project.domain.CinemaProduct;
import com.epam.jwd.final_project.service.impl.QuoteServiceImpl;

public class ToMainPageCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext req) {
        ResponseContext resp = new ResponseContextImpl(ResponseType.FORWARD,
                "/WEB-INF/jsp/main-page.jsp");

        req.setAttributes("random", QuoteServiceImpl.INSTANCE.getRandomQuote());
        req.setAttributes("recommendations", RatingContext.INSTANCE.retrieveList(CinemaProduct.class));

        return resp;
    }

}
