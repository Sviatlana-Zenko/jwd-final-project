package com.epam.jwd.final_project.controller.command.impl;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.ResponseContext.ResponseType;
import com.epam.jwd.final_project.domain.AppUser;
import com.epam.jwd.final_project.dto.UserSessionInfoDto;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.service.impl.AppUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShowAccountInfoCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShowAccountInfoCommand.class);

    @Override
    public ResponseContext execute(RequestContext req) {
        ResponseContext resp = new ResponseContextImpl(ResponseType.FORWARD);
        UserSessionInfoDto userDto = (UserSessionInfoDto) req.getSession().getAttribute("user");

        try {
            AppUser user = AppUserServiceImpl.getInstance().findById(userDto.getId()).get();
            req.setAttributes("userFullInfo", user);
            ((ResponseContextImpl) resp).setPage("/WEB-INF/jsp/account-info.jsp");
        } catch (DatabaseInteractionException e) {
            LOGGER.error(e.getMessage());
            ((ResponseContextImpl) resp).setPage("/WEB-INF/jsp/database-error.jsp");
        }

        return resp;
    }
}
