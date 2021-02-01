package com.epam.jwd.final_project.filter;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.CommandFactory;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.impl.CustomRequestContext;
import com.epam.jwd.final_project.controller.command.impl.ResponseContextImpl;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@WebFilter("/*")
public class SessionTimeOutFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (req.getSession().getAttribute("user") != null) {
            long begin = (Long) req.getSession().getAttribute("start");
//            System.out.println("begin = " + begin);
            long end = begin + 3600000;
//            System.out.println("end = " + end);

//            System.out.println("req.getParameterMap() + " + req.get);

            LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(end), ZoneId.systemDefault());

            if (LocalDateTime.now().isAfter(date)) {
                req.getSession().removeAttribute("user");
                Command command = CommandFactory.command("main-page");
                ResponseContext responseContext = null;
                responseContext = command.execute(new CustomRequestContext(req));
                RequestDispatcher requestDispatcher = req.getRequestDispatcher(((ResponseContextImpl) responseContext).getPage());
                requestDispatcher.forward(req, res);
            }
        }

        chain.doFilter(req, res);
    }

}
