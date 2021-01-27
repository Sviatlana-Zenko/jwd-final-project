package com.epam.jwd.final_project.controller.command.impl.temp;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.impl.ResponseContextImpl;
import com.epam.jwd.final_project.domain.CinemaProduct;
import com.epam.jwd.final_project.service.impl.CinemaProductServiceImpl;

public class ShowTvSeriesInfoCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        ResponseContext responseContext = new ResponseContextImpl(ResponseContext.ResponseType.FORWARD,
                "/WEB-INF/jsp/tvseries-info.jsp");

        Long movieId = Long.valueOf(requestContext.getParameter("id"));

        CinemaProduct movie = CinemaProductServiceImpl.INSTANCE.getById(movieId);

        System.out.println(movie);

        requestContext.setAttributes("movie", movie);

        return responseContext;
    }

}
