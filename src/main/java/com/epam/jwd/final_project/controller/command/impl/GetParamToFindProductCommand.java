package com.epam.jwd.final_project.controller.command.impl;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.ResponseContext.ResponseType;

public class GetParamToFindProductCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext req) {
        ResponseContext resp = new ResponseContextImpl(ResponseType.REDIRECT);

        String title = req.getParameter("title-to-find");

        req.getSession().setAttribute("title", title);
        ((ResponseContextImpl) resp).setPage("/home?command=found-product");

        System.out.println("1title" + title);

        return resp;
    }
}
