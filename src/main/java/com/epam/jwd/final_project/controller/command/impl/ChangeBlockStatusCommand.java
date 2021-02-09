package com.epam.jwd.final_project.controller.command.impl;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.ResponseContext.ResponseType;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.service.impl.AppUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChangeBlockStatusCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChangeBlockStatusCommand.class);

    @Override
    public ResponseContext execute(RequestContext req) {
        ResponseContext resp = new ResponseContextImpl(ResponseType.FORWARD);
        String id = req.getParameter("id");
        String ban = req.getParameter("status");
        Boolean isBanned = ban.equals("block");

        try {
            AppUserServiceImpl.getInstance().updateBan(Long.valueOf(id), isBanned);
            ((ResponseContextImpl) resp).setPage("/WEB-INF/jsp/work-with-users.jsp");
        } catch (DatabaseInteractionException e) {
            LOGGER.error(e.getMessage());
            ((ResponseContextImpl) resp).setPage("/WEB-INF/jsp/database-error.jsp");
        }

        return resp;
    }

}
