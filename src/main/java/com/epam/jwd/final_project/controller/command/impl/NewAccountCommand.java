package com.epam.jwd.final_project.controller.command.impl;

import com.epam.jwd.final_project.context.impl.RatingContext;
import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.ResponseContext.ResponseType;
import com.epam.jwd.final_project.criteria.AppUserCriteria;
import com.epam.jwd.final_project.criteria.Criteria;
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
        ResponseContext responseContext = new ResponseContextImpl(ResponseType.REDIRECT,
                "/home?command=login-result");

        String firstName = checkInput(requestContext.getParameter("first-name"));
        String lastName = checkInput(requestContext.getParameter("last-name"));
        String nickname = checkInput(requestContext.getParameter("nickname"));
        String dateOfBirth = checkInput(requestContext.getParameter("date-of-birth"));
        String email = checkInput(requestContext.getParameter("email"));
        String password = checkInput(requestContext.getParameter("password"));

        AppUser user = new AppUser(firstName, lastName, nickname,
                reverseDate(dateOfBirth), email, password, Role.USER, getNewGenres(requestContext));

//        System.out.println("user " + user);

//        Criteria<AppUser> criteria = new AppUserCriteria.AppUserCriteriaBuilder() {{
//                        firstName(firstName);
//                        lastName(lastName);
//                        nickname(nickname);
//                        dateOfBirth(reverseDate(dateOfBirth));
//                        email(email);
//                        password(password);
//                        favouriteGenres(getNewGenres(requestContext));
//                    }}.build();

        try {
            AppUserServiceImpl.getInstance().create(user);
            ((ResponseContextImpl) responseContext).setPage("/home?command=login-result");
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

    private LocalDate reverseDate(String date) {
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

    private String checkInput(String input) {
        if (input.length() == 0) {
            return null;
        } else {
            return input;
        }

    }
}
