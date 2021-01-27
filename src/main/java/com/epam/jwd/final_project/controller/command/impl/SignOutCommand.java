package com.epam.jwd.final_project.controller.command.impl;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.ResponseContext.ResponseType;

public class SignOutCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext req) {
        ResponseContext responseContext = new ResponseContextImpl(ResponseType.REDIRECT,
                "/home?command=main-page");
        req.getSession().removeAttribute("user");

        return responseContext;
    }
}
