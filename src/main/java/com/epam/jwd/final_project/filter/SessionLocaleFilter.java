package com.epam.jwd.final_project.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@WebFilter("/*")
public class SessionLocaleFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        Locale.setDefault(Locale.ENGLISH);
        rebuildQuery(req, req.getQueryString());

        if (req.getParameter("sessionLocale") != null) {
            req.getSession().setAttribute("lang", req.getParameter("sessionLocale"));
        }

        chain.doFilter(req, res);
    }

    private void rebuildQuery(HttpServletRequest req, String query) {
        if (query == null) {
            req.getSession().setAttribute("queryString", "?");
        } else {
            if (query.startsWith("sessionLocale")) {
                req.getSession().setAttribute("queryString", "?");
            } else {
                String newQueryString = "";
                List<String> parameters = Arrays.asList(query.split("&")).stream()
                        .filter(parameter -> !parameter.contains("sessionLocale"))
                        .collect(Collectors.toList());

                newQueryString = parameters.toString()
                        .replace(", ", "&")
                        .replace("[", "")
                        .replace("]", "");

                req.getSession().setAttribute("queryString", "home?" +  newQueryString + "&");
            }
        }
    }
}