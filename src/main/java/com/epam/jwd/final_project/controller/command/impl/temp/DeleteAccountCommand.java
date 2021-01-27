package com.epam.jwd.final_project.controller.command.impl.temp;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.impl.ResponseContextImpl;
import com.epam.jwd.final_project.dto.UserSessionInfoDto;

public class DeleteAccountCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        ResponseContext responseContext = new ResponseContextImpl(ResponseContext.ResponseType.FORWARD,
                "/WEB-INF/jsp/login-result.jsp");

        UserSessionInfoDto dto = (UserSessionInfoDto) requestContext.getSession().getAttribute("user");

//        AppUserDaoImpl.getInstance().delete(UserSessionInfoConverter.INSTANCE.toEntity(dto));

        System.out.println("ДО - " + requestContext.getSession().getAttribute("user"));

        requestContext.getSession().removeAttribute("user");

        System.out.println("ПОСЛЕ - " + requestContext.getSession().getAttribute("user"));


        return responseContext;
    }
}
