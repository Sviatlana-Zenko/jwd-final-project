package com.epam.jwd.final_project.controller.command.impl.temp;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.impl.ResponseContextImpl;
import com.epam.jwd.final_project.dao.impl.AppUserDaoImpl;
import com.epam.jwd.final_project.domain.AppUser;

public class ShowFoundUserCommand implements Command {
    @Override
    public ResponseContext execute(RequestContext requestContext) {
        ResponseContext responseContext = new ResponseContextImpl(ResponseContext.ResponseType.FORWARD,
                "/WEB-INF/jsp/found-user.jsp");


        AppUser user = null;
        if (requestContext.getParameter("id") != null) {
            Long id = Long.valueOf(requestContext.getParameter("id"));
//            user = AppUserDaoImpl.getInstance().findById(id).get();
        } else {
            String nickname = requestContext.getParameter("nn");
            user = AppUserDaoImpl.getInstance().findUserByNickname(nickname).get();
        }

        requestContext.setAttributes("user", user);

        return responseContext;
    }
}
