package com.epam.jwd.final_project.controller.command.impl;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.ResponseContext.ResponseType;

public class ToNewProductFormCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext req) {
        ResponseContext resp = new ResponseContextImpl(ResponseType.FORWARD);
        String type = req.getParameter("type");

        if (type.equals("movie")) {
            ((ResponseContextImpl) resp).setPage("/WEB-INF/jsp/new-movie-form.jsp");
        } else {
            ((ResponseContextImpl) resp).setPage("/WEB-INF/jsp/new-tvseries-form.jsp");
        }

        return resp;
    }

}
