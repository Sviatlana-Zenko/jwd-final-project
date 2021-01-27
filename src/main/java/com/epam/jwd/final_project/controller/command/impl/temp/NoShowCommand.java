package com.epam.jwd.final_project.controller.command.impl.temp;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.impl.ResponseContextImpl;

public class NoShowCommand implements Command {

    // должно появиться поле boolean isRedirect


    @Override
    public ResponseContext execute(RequestContext requestContext) {
        ResponseContext responseContext = new ResponseContextImpl(ResponseContext.ResponseType.REDIRECT,
                "/WEB-INF/jsp/noShow.jsp");
        return responseContext;
    }

}

//    public static final ResponseContext NO_SHOW_PAGE = new ResponseContext() {
//        @Override
//        public String getPage() {
//            return "/WEB-INF/jsp/noShow.jsp";
//        }
//    };
//
//    @Override
//    public ResponseContext execute(RequestContext requestContext) {
//        return NO_SHOW_PAGE;
//    }
