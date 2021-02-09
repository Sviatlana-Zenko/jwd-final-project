package com.epam.jwd.final_project.controller.command;

import javax.servlet.http.HttpSession;

public interface RequestContext {

    void setAttributes(String name, Object attr);

    String getParameter(String paramName);

    HttpSession getSession();

    Object getAttribute(String name);

}
