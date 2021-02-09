package com.epam.jwd.final_project.controller.command.impl;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.ResponseContext.ResponseType;
import com.epam.jwd.final_project.domain.AppUser;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.service.impl.AppUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

public class ShowFoundUserCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShowFoundUserCommand.class);

    @Override
    public ResponseContext execute(RequestContext req) {
        ResponseContext resp = new ResponseContextImpl(ResponseType.FORWARD);
        String param = req.getParameter("param");
        AppUser found = null;

        try {
            Optional<AppUser> user;
            if (param.equals("id")) {
                user = AppUserServiceImpl.getInstance().findById(Long.valueOf(req.getParameter("id")));
            } else {
                user = AppUserServiceImpl.getInstance().findByNickname(req.getParameter("nickname"));
            }
            if (user.isPresent()) {
                found = user.get();
            }
            req.setAttributes("user", found);
            ((ResponseContextImpl) resp).setPage("/WEB-INF/jsp/found-user.jsp");
        } catch (DatabaseInteractionException e) {
            LOGGER.error(e.getMessage());
            ((ResponseContextImpl) resp).setPage("/WEB-INF/jsp/database-error.jsp");
        }

        return resp;
    }

}
