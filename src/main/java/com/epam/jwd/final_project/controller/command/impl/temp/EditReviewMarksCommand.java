package com.epam.jwd.final_project.controller.command.impl.temp;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.impl.ResponseContextImpl;
import com.epam.jwd.final_project.converter.impl.UserSessionInfoConverter;
import com.epam.jwd.final_project.dao.impl.AppUserDaoImpl;
import com.epam.jwd.final_project.dao.impl.ReviewDaoImpl;
import com.epam.jwd.final_project.domain.AppUser;
import com.epam.jwd.final_project.domain.Review;
import com.epam.jwd.final_project.dto.UserSessionInfoDto;

public class EditReviewMarksCommand implements Command {
    @Override
    public ResponseContext execute(RequestContext requestContext) {

        ResponseContext responseContext = new ResponseContextImpl(ResponseContext.ResponseType.FORWARD,
                "/WEB-INF/jsp/reviews.jsp");

        Review review = null;

        if (requestContext.getParameter("first").equals("true")) {
            System.out.println("true");
            review = new Review(Long.valueOf(requestContext.getParameter("rewid")),
                    Integer.valueOf(requestContext.getParameter("pos")) + 1,
                    Integer.valueOf(requestContext.getParameter("neg")));
        } else {
            System.out.println("false");
            review = new Review(Long.valueOf(requestContext.getParameter("rewid")),
                    Integer.valueOf(requestContext.getParameter("pos")),
                            Integer.valueOf(requestContext.getParameter("neg")) + 1);
        }

        UserSessionInfoDto user = (UserSessionInfoDto) requestContext.getSession().getAttribute("user");

        ReviewDaoImpl.getInstance().updateReviewMarks(review, user.getId(), Boolean.valueOf(requestContext.getParameter("first")));

//        AppUser appUser = AppUserDaoImpl.getInstance().findUserByEmail(user.getEmail()).get();
//        requestContext.getSession().setAttribute("user", UserSessionInfoConverter.INSTANCE.toDto(appUser));

        Long i = Long.valueOf(requestContext.getParameter("id"));

        requestContext.setAttributes("reviews", ReviewDaoImpl.getInstance().findAllForParticularCinemaProduct(i));


        return responseContext;
    }
}
