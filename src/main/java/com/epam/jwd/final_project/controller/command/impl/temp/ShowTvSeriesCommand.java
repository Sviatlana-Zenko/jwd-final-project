package com.epam.jwd.final_project.controller.command.impl.temp;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.impl.ResponseContextImpl;
import com.epam.jwd.final_project.dao.impl.CinemaProductDaoImpl;
import com.epam.jwd.final_project.domain.CinemaProduct;
import com.epam.jwd.final_project.domain.ProductType;

import java.util.List;

public class ShowTvSeriesCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        ResponseContext responseContext = new ResponseContextImpl(ResponseContext.ResponseType.FORWARD,
                "/WEB-INF/jsp/tvseries.jsp");

        Long numb = 0L;


        Long l;
        if (requestContext.getParameter("page") == null) {
            l = 1L;
        } else {
            l = Long.valueOf(requestContext.getParameter("page"));
        }

        Long start = l * 10 - 10;
        Long end = 10L;


        List<CinemaProduct> toPage = CinemaProductDaoImpl.getInstance().findConcreteAmount(ProductType.TV_SERIES, start, end);
        numb = CinemaProductDaoImpl.getInstance().getNumberOfElements(ProductType.TV_SERIES);

        Long numberOfPages = numb % 10 > 0 ? (numb / 10) + 1 : numb / 10;

        requestContext.setAttributes("pages", numberOfPages);
        requestContext.setAttributes("movies", toPage);

        return responseContext;
    }
}
