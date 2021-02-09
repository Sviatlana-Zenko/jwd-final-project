package com.epam.jwd.final_project.controller.command.impl;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.ResponseContext.ResponseType;

public class SearchCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext req) {
        ResponseContext resp = new ResponseContextImpl(ResponseType.REDIRECT);
        String search = req.getParameter("search-request");
        req.getSession().setAttribute("search", search);
        ((ResponseContextImpl) resp).setPage("/home?command=search-result");

        return resp;
    }

}
