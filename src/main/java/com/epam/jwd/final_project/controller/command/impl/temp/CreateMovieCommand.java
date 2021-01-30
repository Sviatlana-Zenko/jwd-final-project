package com.epam.jwd.final_project.controller.command.impl.temp;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.impl.ResponseContextImpl;
import com.epam.jwd.final_project.dao.impl.CinemaProductDaoImpl;
import com.epam.jwd.final_project.domain.*;
import com.epam.jwd.final_project.pool.ConnectionPool;
import com.epam.jwd.final_project.util.DateConverterUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreateMovieCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext requestContext) {

        ResponseContext responseContext = new ResponseContextImpl(ResponseContext.ResponseType.REDIRECT);

        String title = requestContext.getParameter("product-title");
        String description = requestContext.getParameter("description");
        String releaseDate = requestContext.getParameter("release-date");
        String runningTime = requestContext.getParameter("running-time");
        String country = requestContext.getParameter("country");
        String ageRating = requestContext.getParameter("age_rating");
        String starring = requestContext.getParameter("starring");
        String posterUrl = requestContext.getParameter("poster_url");
        String directedBy = requestContext.getParameter("directed_by");
        String producedBy = requestContext.getParameter("produced_by");
        String budget = requestContext.getParameter("budget");
        String boxOffice = requestContext.getParameter("box_office");

        String[] str = releaseDate.split("-");
        releaseDate = str[2] + "-" + str[1] + "-" + str[0];

        List<String> allGenres = Genre.getListOfGenres();
        List<Genre> genres = new ArrayList<>();

        for (int i = 0; i < allGenres.size(); i++) {
            if (requestContext.getParameter(allGenres.get(i)) != null && requestContext.getParameter(allGenres.get(i)).equals("on")) {
                genres.add(Genre.resolveGenreByName(allGenres.get(i)));
            }
        }

        CinemaProduct movie = new Movie(ProductType.MOVIE, title, description,
                DateConverterUtil.convertToLocalDate(releaseDate), Integer.valueOf(runningTime),
                country, Byte.valueOf(ageRating), genres,
                starring, posterUrl,
                directedBy, producedBy,
                Integer.valueOf(budget), Integer.valueOf(boxOffice));


//        CinemaProductDaoImpl.getInstance().create(movie);
//        PreparedStatement statement = null;
//
//        Long generatedForId = null;
//        try {
//            generatedForId = CinemaProductDaoImpl.getInstance().getGeneratedId(ConnectionPool.INSTANCE.getAvailableConnection(), statement, movie);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        for (int i = 0; i < genres.size(); i++) {
//            try(Connection connection2 = ConnectionPool.INSTANCE.getAvailableConnection();
//                PreparedStatement preparedStatement = connection2.prepareStatement("INSERT INTO cinema_product_genre(cinema_product_id, genre_id) VALUE (?, ?)")) {
//                preparedStatement.setLong(1, generatedForId);
//                preparedStatement.setLong(2, genres.get(i).getId());
//                preparedStatement.executeUpdate();
//            } catch (SQLException e) {
////                wasCreated = false;
//                e.printStackTrace();
//            }
//        }

        ((ResponseContextImpl) responseContext).setPage("/home?command=product-actions");

        return responseContext;
    }

}
