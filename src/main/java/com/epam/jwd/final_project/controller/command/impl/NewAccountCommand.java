package com.epam.jwd.final_project.controller.command.impl;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.ResponseContext.ResponseType;
import com.epam.jwd.final_project.domain.AppUser;
import com.epam.jwd.final_project.domain.Genre;
import com.epam.jwd.final_project.domain.Role;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.exception.ValidationException;
import com.epam.jwd.final_project.service.impl.AppUserServiceImpl;
import com.epam.jwd.final_project.util.DateConverterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NewAccountCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewAccountCommand.class);

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        ResponseContext responseContext = new ResponseContextImpl(ResponseType.REDIRECT);
        String firstName = requestContext.getParameter("first-name");
        String lastName = requestContext.getParameter("last-name");
        String nickname = requestContext.getParameter("nickname");
        String dateOfBirth = requestContext.getParameter("date-of-birth");
        String email = requestContext.getParameter("email");
        String password = requestContext.getParameter("password");

        try {
            if (AppUserServiceImpl.getInstance().checkIfEmailExists(email)) {
                requestContext.getSession().setAttribute("emailError", true);
                ((ResponseContextImpl) responseContext).setPage("/home?command=new-account-form");
            }
            if (AppUserServiceImpl.getInstance().checkIfNickNameExists(nickname)) {
                requestContext.getSession().setAttribute("nicknameError", true);
                ((ResponseContextImpl) responseContext).setPage("/home?command=new-account-form");
            } else {
                AppUser user = new AppUser(firstName, lastName, nickname, reversedDate(dateOfBirth),
                        email, password, Role.USER, getNewGenres(requestContext));
                AppUserServiceImpl.getInstance().create(user);
                requestContext.getSession().setAttribute("emailError", false);
                requestContext.getSession().setAttribute("nicknameError", false);
                ((ResponseContextImpl) responseContext).setPage("/home?command=sign-in-form");
            }
        } catch (ValidationException e) {
            LOGGER.error(e.getMessage());
            requestContext.getSession().setAttribute("errors", e.getValidationErrors());
            ((ResponseContextImpl) responseContext).setPage("/home?command=creation-error");
        } catch (DatabaseInteractionException e) {
            LOGGER.error(e.getMessage());
            ((ResponseContextImpl) responseContext).setPage("/home?command=db-error");
        }

        return responseContext;
    }

    private List<Genre> getNewGenres(RequestContext req) {
        List<String> allGenres = Genre.getListOfGenres();
        List<Genre> newFavoriteGenres = new ArrayList<>();

        for (int i = 0; i < allGenres.size(); i++) {
            if (req.getParameter(allGenres.get(i)) != null &&
                    req.getParameter(allGenres.get(i)).equals("on")) {
                newFavoriteGenres.add(Genre.resolveGenreByName(allGenres.get(i)));
            }
        }

        return newFavoriteGenres;
    }

    private LocalDate reversedDate(String date) {
        LocalDate localDate;
        if (date != null && date.length() == 10) {
            String[] str = date.split("-");
            date = str[2] + "-" + str[1] + "-" + str[0];
            localDate = DateConverterUtil.convertToLocalDate(date);
        } else {
            localDate = null;
        }

        return localDate;
    }

}
