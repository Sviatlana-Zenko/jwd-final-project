package com.epam.jwd.final_project.controller.command.impl.temp;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.impl.ResponseContextImpl;
import com.epam.jwd.final_project.dao.impl.ReviewDaoImpl;

public class ShowReviewsCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        ResponseContext responseContext = new ResponseContextImpl(ResponseContext.ResponseType.FORWARD,
                "/WEB-INF/jsp/reviews.jsp");

        Long id = Long.valueOf(requestContext.getParameter("id"));
        requestContext.setAttributes("reviews", ReviewDaoImpl.getInstance().findAllForParticularCinemaProduct(id));

        return responseContext;
    }

}
