package com.epam.jwd.final_project.controller.command.impl.temp;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.impl.ResponseContextImpl;
import com.epam.jwd.final_project.dao.impl.CinemaProductDaoImpl;
import com.epam.jwd.final_project.domain.CinemaProduct;
import com.epam.jwd.final_project.domain.ProductType;

import java.util.List;

public class ShowMoviesCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        ResponseContext responseContext = new ResponseContextImpl(ResponseContext.ResponseType.FORWARD,
                "/WEB-INF/jsp/movies.jsp");

        Long numb = 0L;


        Long l;
        if (requestContext.getParameter("page") == null) {
            l = 1L;
        } else {
            l = Long.valueOf(requestContext.getParameter("page"));
        }

        Long start = l * 10 - 10;
        Long end = 10L;


        List<CinemaProduct> toPage = CinemaProductDaoImpl.getInstance().findConcreteAmount(ProductType.MOVIE, start, end);
        numb = CinemaProductDaoImpl.getInstance().getNumberOfElements(ProductType.MOVIE);

        Long numberOfPages = numb % 10 > 0 ? (numb / 10) + 1 : numb / 10;

        requestContext.setAttributes("pages", numberOfPages);
        requestContext.setAttributes("movies", toPage);
//        requestContext.setAttributes("movies", CinemaProductDaoImpl.getInstance().findAllByType(ProductType.MOVIE));

        return responseContext;
//        ResponseContext responseContext = new ResponseContextImpl(ResponseContext.ResponseType.FORWARD,
//                "/WEB-INF/jsp/movie.jsp");
//
////        System.out.println("movies - " + CinemaProductDaoImpl.getInstance().findAllByType(ProductType.MOVIE));
//
//        requestContext.setAttributes("movies", CinemaProductDaoImpl.getInstance().findAllByType(ProductType.MOVIE));
//        requestContext.getSession().setAttribute("reviewId", 0L);
//
////        System.out.println(AppUserDaoImpl.getInstance().findAll());
//
//        return responseContext;
    }
}
