package com.epam.jwd.final_project.controller.command.impl;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.converter.impl.UserSessionInfoConverter;
import com.epam.jwd.final_project.domain.AppUser;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.exception.ValidationException;
import com.epam.jwd.final_project.service.impl.AppUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignInCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(SignInCommand.class);

    @Override
    public ResponseContext execute(RequestContext req) {
        ResponseContext resp = new ResponseContextImpl(ResponseContext.ResponseType.REDIRECT);
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try {
            AppUser foundUser = AppUserServiceImpl.getInstance().findByEmail(email).get();
            if (foundUser != null &&
                    AppUserServiceImpl.getInstance().checkPassword(foundUser, password)) {
                AppUserServiceImpl.getInstance().updateUserStatus(foundUser);
                long startOfUse = req.getSession().getCreationTime();
                req.getSession().setAttribute("start", startOfUse);
                req.getSession().setAttribute("user", UserSessionInfoConverter.INSTANCE.toDto(foundUser));
                req.getSession().setAttribute("failed", Boolean.valueOf("false"));
                ((ResponseContextImpl) resp).setPage("/home?command=main-page");
            } else {
                req.getSession().setAttribute("failed", Boolean.valueOf("true"));
                ((ResponseContextImpl) resp).setPage("/home?command=sign-in-form");
            }
        } catch (DatabaseInteractionException e) {
            LOGGER.error(e.getMessage());
            ((ResponseContextImpl) resp).setPage("/home?command=db-error");
        }

        return resp;
    }

}
