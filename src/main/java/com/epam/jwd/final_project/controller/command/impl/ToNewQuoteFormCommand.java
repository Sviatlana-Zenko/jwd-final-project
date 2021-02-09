package com.epam.jwd.final_project.controller.command.impl;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.ResponseContext.ResponseType;

public class ToNewQuoteFormCommand implements Command {
    @Override
    public ResponseContext execute(RequestContext req) {
        return new ResponseContextImpl(ResponseType.FORWARD, "/WEB-INF/jsp/new-quote-form.jsp");
    }
}
