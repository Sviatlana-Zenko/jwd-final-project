package com.epam.jwd.final_project.controller.command.impl.temp;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.impl.ResponseContextImpl;

public class ChangeLocaleCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext requestContext) {
//        String command = requestContext.getParameter("command");
//        System.out.println(command);

        requestContext.getSession().setAttribute("lang", "by");

        System.out.println(requestContext.getSession());

        System.out.println(requestContext.getParameter("sessionLocale"));

        ResponseContext responseContext = new ResponseContextImpl(ResponseContext.ResponseType.REDIRECT, "/home?command=refresh");
//        requestContext.setAttributes("allShow", showService.findAll());
        return responseContext;
    }
}
