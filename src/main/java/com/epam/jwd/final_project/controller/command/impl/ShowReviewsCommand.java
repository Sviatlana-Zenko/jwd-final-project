package com.epam.jwd.final_project.controller.command.impl;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.ResponseContext.ResponseType;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.service.impl.ReviewServiceImpl;

public class ShowReviewsCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext req) {
        ResponseContext resp = new ResponseContextImpl(ResponseType.FORWARD,
                "/WEB-INF/jsp/reviews.jsp");

        Long id = Long.valueOf(req.getParameter("id"));

        try {
            req.setAttributes("reviews", ReviewServiceImpl.INSTANCE.findAllForConcreteProduct(id));
        } catch (DatabaseInteractionException e) {
            e.printStackTrace();
        }

        return resp;
    }

}
