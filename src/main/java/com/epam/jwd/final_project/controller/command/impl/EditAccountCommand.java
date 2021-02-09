package com.epam.jwd.final_project.controller.command.impl;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.ResponseContext.ResponseType;
import com.epam.jwd.final_project.criteria.AppUserCriteria;
import com.epam.jwd.final_project.criteria.Criteria;
import com.epam.jwd.final_project.domain.AppUser;
import com.epam.jwd.final_project.domain.Genre;
import com.epam.jwd.final_project.dto.UserSessionInfoDto;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.exception.ValidationException;
import com.epam.jwd.final_project.service.impl.AppUserServiceImpl;
import com.epam.jwd.final_project.util.DateConverterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EditAccountCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(EditAccountCommand.class);

    @Override
    public ResponseContext execute(RequestContext req) {
        ResponseContext responseContext = new ResponseContextImpl(ResponseType.REDIRECT);
        String firstName = req.getParameter("first-name");
        String lastName = req.getParameter("last-name");
        String nickname = req.getParameter("nickname");
        String date = req.getParameter("date-of-birth");
        LocalDate dateOfBirth = reversedDate(date) == null ? null : DateConverterUtil.convertToLocalDate(reversedDate(date));
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserSessionInfoDto userDto = (UserSessionInfoDto) req.getSession().getAttribute("user");
        AppUser newUser = new AppUser(userDto.getId(), firstName, lastName, nickname,
                dateOfBirth, email, password, getNewGenres(req));

        Criteria<AppUser> criteria = new AppUserCriteria.AppUserCriteriaBuilder() {{
            firstName(firstName);
            lastName(lastName);
            nickname(nickname);
            dateOfBirth(dateOfBirth);
            email(email);
            password(password);
            favouriteGenres(getNewGenres(req));
        }}.build();

        try {
            AppUserServiceImpl.getInstance().updateByCriteria(newUser, (AppUserCriteria) criteria);
            ((ResponseContextImpl) responseContext).setPage("/home?command=account-info");
        } catch (ValidationException e) {
            LOGGER.error(e.getMessage());
            req.getSession().setAttribute("errors", e.getValidationErrors());
            ((ResponseContextImpl) responseContext).setPage("/home?command=validation-error");
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

    private String reversedDate(String date) {
        String converted = null;
        if (date != null && date.length() > 0) {
            if (date.matches("\\d{2}-\\d{2}-\\d{4}")) {
                String[] str = date.split("-");
                converted = str[2] + "-" + str[1] + "-" + str[0];
            }
        }

        return converted;
    }

}
