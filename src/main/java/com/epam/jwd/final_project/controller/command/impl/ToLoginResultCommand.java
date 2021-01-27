package com.epam.jwd.final_project.controller.command.impl;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.impl.ResponseContextImpl;

public class ToLoginResultCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext requestContext) {

        ResponseContext responseContext = new ResponseContextImpl(ResponseContext.ResponseType.FORWARD, "/WEB-INF/jsp/login-result.jsp");

        System.out.println("зашла в ту логин резалт команд");

        return responseContext;
    }
}
