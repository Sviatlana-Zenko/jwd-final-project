package com.epam.jwd.final_project.controller;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.CommandFactory;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.impl.temp.CustomRequestContext;
import com.epam.jwd.final_project.controller.command.impl.ResponseContextImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@WebServlet(value = "/home") //, loadOnStartup = 0
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get");
        process(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("post");
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandParamName = req.getParameter("command");
        Command command = CommandFactory.command(commandParamName);
        RequestContext requestContext = new CustomRequestContext(req);
        ResponseContext responseContext = command.execute(requestContext);
        if (requestContext.getSession().getAttribute("user") != null) {
            System.out.println("зашла в if в servlet process");
            LocalDateTime localDateTime = LocalDateTime.now();
            ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
            System.out.println(zdt.toInstant().toEpochMilli());
//            long l = requestContext.getSession().getCreationTime();
            requestContext.getSession().setAttribute("start", zdt.toInstant().toEpochMilli());
        }

        if (((ResponseContextImpl) responseContext).getResponseType() == ResponseContext.ResponseType.FORWARD) {
            System.out.println("forward");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(((ResponseContextImpl) responseContext).getPage());
            requestDispatcher.forward(req, resp);
        } else {
            System.out.println("redirect");
            resp.sendRedirect(((ResponseContextImpl) responseContext).getPage());
        }
    }

}


//        System.out.println(req.getSession().getAttribute("init"));
//        if (req.getSession().getAttribute("init") == null) {
//            req.getSession().setAttribute("init", QuoteDaoImpl.getInstance().getRandomQuote());
//        }
//        System.out.println(req.getSession().getAttribute("init"));


/*
@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandParamName = req.getParameter("command");
        Command command = CommandFactory.command(commandParamName);
        ResponseContext responseContext = command.execute(new CustomRequestContext(req));
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher(responseContext.getPage());
//        System.out.println(req.getServletContext().getContextPath() + responseContext.getPage());
//        requestDispatcher.forward(req, resp);

//        resp.sendRedirect("/jsp/next.jsp");

        if (i) {
//            System.out.println("." + req.getServletContext().getContextPath() + ".");
            resp.sendRedirect("/jsp/next.jsp");
//            resp.sendRedirect("/noShow.jsp");
            i = false;
        }

    }
 */


















/*
@Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("post");

//        String user = req.getParameter("email");
//
//        resp.setContentType("text/html");
//        resp.setCharacterEncoding("UTF-8");
//
//        // create HTML response
//        PrintWriter writer = resp.getWriter();
//        writer.append("<!DOCTYPE html>\r\n")
//                .append("<html>\r\n")
//                .append("        <head>\r\n")
//                .append("            <title>Welcome message</title>\r\n")
//                .append("        </head>\r\n")
//                .append("        <body>\r\n")
//                .append(user)
//        .append("        </body>\r\n")
//                .append("</html>\r\n");

//        resp.sendRedirect("home?command=result");

//        String commandParamName = req.getParameter("command");
//        System.out.println(commandParamName);
//        Command command = CommandFactory.command(commandParamName);
//        System.out.println(commandParamName);
//        ResponseContext responseContext = command.execute(new CustomRequestContext(req));
//        resp.sendRedirect("home?command=login");
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher(((ResponseContextImpl) responseContext).getPage());
//        requestDispatcher.forward(req, resp);

        //        resp.sendRedirect(((ResponseContextImpl) responseContext).getPage());
////        RequestDispatcher requestDispatcher = req.getRequestDispatcher(((ResponseContextImpl) responseContext).getPage());
////        requestDispatcher.forward(req, resp);
        process(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get");
//        String commandParamName = req.getParameter("command");
//        System.out.println(commandParamName);
//        Command command = CommandFactory.command(commandParamName);
//        ResponseContext responseContext = command.execute(new CustomRequestContext(req));
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher(((ResponseContextImpl) responseContext).getPage());
//        requestDispatcher.forward(req, resp);
////        resp.sendRedirect("home?command=lo");
////        resp.sendRedirect(((ResponseContextImpl) responseContext).getPage());
//        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandParamName = req.getParameter("command");
        System.out.println(commandParamName);
        Command command = CommandFactory.command(commandParamName);
        RequestContext requestContext = new CustomRequestContext(req);
//        requestContext.setAttributes("login", "attr");
        System.out.println(requestContext.getParameter("login"));

        ResponseContext responseContext = command.execute(new CustomRequestContext(req));
        System.out.println(requestContext.getParameter("login"));
////
        if (((ResponseContextImpl) responseContext).getResponseType() == ResponseContext.ResponseType.FORWARD) {
            System.out.println("forward");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher(((ResponseContextImpl) responseContext).getPage());
            requestDispatcher.forward(req, resp);
        } else {
            System.out.println("redirect");
            resp.sendRedirect(req.getContextPath() + ((ResponseContextImpl) responseContext).getPage());
//            resp.sendRedirect(req.getContextPath() + ((ResponseContextImpl) responseContext).getPage());
       }
    }

 */
