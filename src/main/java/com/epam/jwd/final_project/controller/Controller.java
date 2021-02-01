package com.epam.jwd.final_project.controller;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.CommandFactory;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.ResponseContext.ResponseType;
import com.epam.jwd.final_project.controller.command.impl.CustomRequestContext;
import com.epam.jwd.final_project.controller.command.impl.ResponseContextImpl;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@WebServlet(value = "/home") 
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        String commandParamName = req.getParameter("command");
        Command command = CommandFactory.command(commandParamName);
        RequestContext reqContext = new CustomRequestContext(req);
        ResponseContext respContext = command.execute(reqContext);
        if (reqContext.getSession().getAttribute("user") != null) {
            System.out.println("зашла в if в servlet process");
            LocalDateTime localDateTime = LocalDateTime.now();
            ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
            reqContext.getSession().setAttribute("start", zdt.toInstant().toEpochMilli());
        }

        if (((ResponseContextImpl) respContext).getResponseType() == ResponseType.FORWARD) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(((ResponseContextImpl) respContext).getPage());
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect(((ResponseContextImpl) respContext).getPage());
        }
    }

}
