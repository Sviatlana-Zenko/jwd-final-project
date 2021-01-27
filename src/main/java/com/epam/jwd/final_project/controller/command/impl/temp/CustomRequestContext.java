package com.epam.jwd.final_project.controller.command.impl.temp;

import com.epam.jwd.final_project.controller.command.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CustomRequestContext implements RequestContext {

    private final HttpServletRequest httpServletRequest;

    public CustomRequestContext(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @Override
    public List<String> getParamList() {
        return httpServletRequest.getParameterMap().values().stream().
                flatMap(Arrays::stream).collect(Collectors.toList());
    }

    @Override
    public void setAttributes(String name, Object attr) {
        httpServletRequest.setAttribute(name, attr);
    }

    @Override
    public String getParameter(String paramName) {
        return httpServletRequest.getParameter(paramName);
    }

    @Override
    public HttpSession getSession() {
        return httpServletRequest.getSession();
    }

    @Override
    public String getRequestURL() {
        return httpServletRequest.getRequestURI();
    }

    @Override
    public String getQueryString() {
        return httpServletRequest.getQueryString();
    }

    @Override
    public Object getAttribute(String name) {
        return httpServletRequest.getAttribute(name);
    }


}

//    private final HttpServletRequest httpServletRequest;
//
//    public CustomRequestContext(HttpServletRequest httpServletRequest) {
//        this.httpServletRequest = httpServletRequest;
//    }
//
//    @Override
//    public List<String> getParamList() {
//        return httpServletRequest.getParameterMap().values().stream().
//                flatMap(Arrays::stream).collect(Collectors.toList());
//    }
//
//    @Override
//    public void setAttributes(String name, Object attr) {
//        httpServletRequest.setAttribute(name, attr);
//    }
