package com.epam.jwd.final_project.controller.command.impl.temp;

import com.epam.jwd.final_project.context.impl.RatingContext;
import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.impl.ResponseContextImpl;
import com.epam.jwd.final_project.domain.Movie;

public class ShowTopMoviesCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        ResponseContext responseContext = new ResponseContextImpl(ResponseContext.ResponseType.FORWARD,
                                                            "/WEB-INF/jsp/top-movies.jsp");

        requestContext.setAttributes("movies", RatingContext.INSTANCE.retrieveList(Movie.class));

        return responseContext;
    }

}
