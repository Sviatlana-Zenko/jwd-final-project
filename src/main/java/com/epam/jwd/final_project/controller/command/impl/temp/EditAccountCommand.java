package com.epam.jwd.final_project.controller.command.impl.temp;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.impl.ResponseContextImpl;
import com.epam.jwd.final_project.criteria.AppUserCriteria;
import com.epam.jwd.final_project.criteria.Criteria;
import com.epam.jwd.final_project.criteria.QuoteCriteria;
import com.epam.jwd.final_project.domain.AppUser;
import com.epam.jwd.final_project.domain.Genre;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.exception.ValidationException;
import com.epam.jwd.final_project.pool.ConnectionPool;
import com.epam.jwd.final_project.service.impl.AppUserServiceImpl;
import com.epam.jwd.final_project.service.impl.QuoteServiceImpl;
import com.epam.jwd.final_project.util.DateConverterUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EditAccountCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext req) {
        ResponseContext responseContext = new ResponseContextImpl(ResponseContext.ResponseType.REDIRECT,
                "/home?command=account-info");

        String id = req.getParameter("user-id");
        String firstName = req.getParameter("first-name");
        String lastName = req.getParameter("last-name");
        String nickname = req.getParameter("nickname");
        String dateOfBirth = req.getParameter("date-of-birth");
        String email = req.getParameter("email");
        String password = req.getParameter("password");


        AppUser newUser = new AppUser(Long.valueOf(id), firstName, lastName, nickname,
                DateConverterUtil.convertToLocalDate(reverseDate(dateOfBirth)),
                email, password, getNewGenres(req));

        Criteria<AppUser> criteria = new AppUserCriteria.AppUserCriteriaBuilder() {{
            firstName(firstName);
            lastName(lastName);
            nickname(nickname);
            dateOfBirth(DateConverterUtil.convertToLocalDate(reverseDate(dateOfBirth)));
            email(email);
            password(password);
            favouriteGenres(getNewGenres(req));
        }}.build();

//        try {
//            QuoteServiceImpl.INSTANCE.updateByCriteria(newQuote, (QuoteCriteria) criteria);
//            ((ResponseContextImpl) responseContext).setPage("/home?command=quote-operations");
//        } catch (ValidationException e) {
//            LOGGER.error(e.getMessage());
//            requestContext.getSession().setAttribute("errors", e.getValidationErrors());
//            ((ResponseContextImpl) responseContext).setPage("/home?command=quote-error");
//        } catch (DatabaseInteractionException e) {
//            LOGGER.error(e.getMessage());
//            ((ResponseContextImpl) responseContext).setPage("/home?command=db-error");
//        }

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

    private String reverseDate(String date) {
        String converted = null;
        if (date != null || date.length() > 0) {
            String[] str = date.split("-");
            converted = str[2] + "-" + str[1] + "-" + str[0];
        }

        return converted;
    }

}
