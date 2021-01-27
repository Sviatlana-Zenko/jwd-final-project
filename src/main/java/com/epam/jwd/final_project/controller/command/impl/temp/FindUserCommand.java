package com.epam.jwd.final_project.controller.command.impl.temp;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.impl.ResponseContextImpl;
import com.epam.jwd.final_project.domain.AppUser;

public class FindUserCommand implements Command {
    @Override
    public ResponseContext execute(RequestContext requestContext) {
        ResponseContext responseContext = new ResponseContextImpl(ResponseContext.ResponseType.REDIRECT);

        String searchParameter = requestContext.getParameter("param");
        AppUser appUser;

        if (searchParameter.equals("id")) {
            Long id = Long.valueOf(requestContext.getParameter("id"));
            ((ResponseContextImpl) responseContext).setPage("/home?command=show-found-user&id=" + id);
        } else {
            String nickname = requestContext.getParameter("nickname");
            ((ResponseContextImpl) responseContext).setPage("/home?command=show-found-user&nn=" + nickname);
        }

        return responseContext;
    }
}
