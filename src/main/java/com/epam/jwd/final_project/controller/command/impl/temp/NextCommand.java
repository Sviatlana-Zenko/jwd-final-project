package com.epam.jwd.final_project.controller.command.impl.temp;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.impl.ResponseContextImpl;

public class NextCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext requestContext) {

        System.out.println("зашла в NextCommand, здесь forward to jsp");

        ResponseContext responseContext = new ResponseContextImpl(ResponseContext.ResponseType.FORWARD, "/WEB-INF/jsp/next.jsp");
        return responseContext;
    }
}



//1-ый вариант
//    public static final ResponseContext NEXT = new ResponseContext() {
//        @Override
//        public String getPage() {
//            return "/WEB-INF/jsp/next.jsp";
////            return "home?command=next";
//        }
//    };
//
//    @Override
//    public ResponseContext execute(RequestContext requestContext) {
//        return NEXT;
//    }