package com.epam.jwd.final_project.controller.command.impl.temp;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.impl.ResponseContextImpl;

public class ShowCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext requestContext) {

        System.out.println("зашла в ShowCommand, здесь redirect to command");

        ResponseContext responseContext = new ResponseContextImpl(ResponseContext.ResponseType.REDIRECT, "home?command=show");
//        requestContext.setAttributes("allShow", showService.findAll());
        return responseContext;
    }

}

//    public static final ResponseContext SHOW_PAGE = new ResponseContext() {
//        @Override
//        public String getPage() {
//            return "/WEB-INF/jsp/show.jsp";
//        }
//    };
//
//    @Override
//    public ResponseContext execute(RequestContext requestContext) {
//        requestContext.setAttributes("allShow", showService.findAll());
//        return SHOW_PAGE;
//    }
