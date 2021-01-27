package com.epam.jwd.final_project.controller.command.impl.temp;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.impl.ResponseContextImpl;

public class MainCommand implements Command {

    // должно появиться поле boolean isRedirect

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        ResponseContext responseContext = new ResponseContextImpl(ResponseContext.ResponseType.REDIRECT, "/jsp/home.jsp");
        return responseContext;
    }

}

//    public static final ResponseContext MAIN_PAGE = new ResponseContext() {
//        @Override
//        public String getPage() {
//            return "/WEB-INF/jsp/home.jsp";
//        }
//    };
//
//    @Override
//    public ResponseContext execute(RequestContext requestContext) {
//        return MAIN_PAGE;
//    }
