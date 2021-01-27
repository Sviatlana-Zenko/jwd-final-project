package com.epam.jwd.final_project.controller.command;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface RequestContext {

    List<String> getParamList();

    void setAttributes(String name, Object attr);

    String getParameter(String paramName);

    HttpSession getSession();

    String getRequestURL();

    String getQueryString();

    Object getAttribute(String name);

}
