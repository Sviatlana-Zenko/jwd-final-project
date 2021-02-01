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

@WebFilter("/*")
public class AuthFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        if (req.getParameter("command") != null) {
            if (req.getSession().getAttribute("user") == null &
                    (req.getParameter("command").equals("review-form") ||
                            req.getParameter("command").equals("edit-marks"))) {
                Command command = CommandFactory.command("sign-in-form");
                ResponseContext responseContext = command.execute(new CustomRequestContext(req));
                RequestDispatcher dispatcher = req.getRequestDispatcher(((ResponseContextImpl) responseContext).getPage());
                dispatcher.forward(req, res);
            }
        }

        chain.doFilter(req, res);
    }

}
