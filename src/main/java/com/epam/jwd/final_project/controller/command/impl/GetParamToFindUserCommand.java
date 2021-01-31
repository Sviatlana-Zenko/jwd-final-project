package com.epam.jwd.final_project.controller.command.impl;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.ResponseContext.ResponseType;

public class GetParamToFindUserCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext req) {
        ResponseContext resp = new ResponseContextImpl(ResponseType.REDIRECT);

        String param = req.getParameter("param");

        if (param.equals("id")) {
            String id = req.getParameter("id-to-find");
            System.out.println("ID + " + id);
            ((ResponseContextImpl) resp).setPage("/home?command=found-user&param=" + param + "&id=" + id);
        } else {
            String nickname = req.getParameter("nickname-to-find");
            System.out.println("nickname + " + nickname);
            ((ResponseContextImpl) resp).setPage("/home?command=found-user&param=" + param + "&nickname=" + nickname);
        }

        return resp;
    }
}
