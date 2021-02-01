package com.epam.jwd.final_project.controller.command.impl;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.domain.*;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.exception.ValidationException;
import com.epam.jwd.final_project.service.impl.CinemaProductServiceImpl;
import com.epam.jwd.final_project.util.DateConverterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreateMovieCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateMovieCommand.class);

    @Override
    public ResponseContext execute(RequestContext req) {

        ResponseContext resp = new ResponseContextImpl(ResponseContext.ResponseType.REDIRECT);

        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String releaseDate = req.getParameter("release-date");
        String time = checkInput(req.getParameter("time"));
        Integer runningTime = checkStringContainsNumber(time) ? Integer.valueOf(time) : null;
        String country = req.getParameter("country");
        String ageRating = req.getParameter("age");
        String starring = req.getParameter("starring");
        String posterUrl = req.getParameter("poster-url");
        String directedBy = req.getParameter("directed-by");
        String producedBy = req.getParameter("produced-by");
        String budget = req.getParameter("budget");
        String boxOffice = req.getParameter("box-office");
        Integer movieBudget = checkStringContainsNumber(budget) ? Integer.valueOf(budget) : null;
        Integer movieBoxOffice = checkStringContainsNumber(boxOffice) ? Integer.valueOf(boxOffice) : null;

        CinemaProduct movie = new Movie(ProductType.MOVIE, title, description,
                reverseDate(releaseDate),
                runningTime,
                country, Byte.valueOf(ageRating), getNewGenres(req),
                starring, posterUrl,
                directedBy, producedBy,
                movieBudget, movieBoxOffice);

        try {
            CinemaProductServiceImpl.INSTANCE.create(movie);
            ((ResponseContextImpl) resp).setPage("/home?command=product-operations");
        } catch (ValidationException e) {
            LOGGER.error(e.getMessage());
            req.getSession().setAttribute("errors", e.getValidationErrors());
            ((ResponseContextImpl) resp).setPage("/home?command=validation-error");
        } catch (DatabaseInteractionException e) {
            LOGGER.error(e.getMessage());
            ((ResponseContextImpl) resp).setPage("/home?command=db-error");
        }

        return resp;
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

    private boolean checkStringContainsNumber(String toCheck) {
        final String regex = "\\d+";
        return toCheck != null && toCheck.matches(regex);
    }

}
