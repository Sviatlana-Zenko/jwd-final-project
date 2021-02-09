package com.epam.jwd.final_project.controller.command.impl;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.ResponseContext.ResponseType;
import com.epam.jwd.final_project.converter.impl.UserSessionInfoConverter;
import com.epam.jwd.final_project.domain.AppUser;
import com.epam.jwd.final_project.dto.UserSessionInfoDto;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.service.impl.AppUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteAccountCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteAccountCommand.class);

    @Override
    public ResponseContext execute(RequestContext req) {
        ResponseContext resp = new ResponseContextImpl(ResponseType.REDIRECT);
        UserSessionInfoDto dto = (UserSessionInfoDto) req.getSession().getAttribute("user");
        AppUser toDelete = UserSessionInfoConverter.INSTANCE.toEntity(dto);

        try {
            AppUserServiceImpl.getInstance().delete(toDelete);
            req.getSession().removeAttribute("user");
            ((ResponseContextImpl) resp).setPage("/home?command=main-page");
        } catch (DatabaseInteractionException e) {
            LOGGER.error(e.getMessage());
            ((ResponseContextImpl) resp).setPage("/home?command=db-error");
        }

        return resp;
    }

}
