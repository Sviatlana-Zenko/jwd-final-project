package com.epam.jwd.final_project.controller.command.impl;

import com.epam.jwd.final_project.controller.command.RequestContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CustomRequestContext implements RequestContext {

    private final HttpServletRequest httpServletRequest;

    public CustomRequestContext(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
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
    public Object getAttribute(String name) {
        return httpServletRequest.getAttribute(name);
    }

}
