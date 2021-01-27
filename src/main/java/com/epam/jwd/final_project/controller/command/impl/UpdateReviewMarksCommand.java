package com.epam.jwd.final_project.controller.command.impl;

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

public class UpdateReviewMarksCommand implements Command {
    @Override
    public ResponseContext execute(RequestContext req) {

        ResponseContext resp = new ResponseContextImpl(ResponseContext.ResponseType.FORWARD,
                "/WEB-INF/jsp/reviews.jsp");

        Review review = null;

        if (req.getParameter("first").equals("true")) {
            System.out.println("true");
            review = new Review(Long.valueOf(req.getParameter("rewid")),
                    Integer.valueOf(req.getParameter("pos")) + 1,
                    Integer.valueOf(req.getParameter("neg")));
        } else {
            System.out.println("false");
            review = new Review(Long.valueOf(req.getParameter("rewid")),
                    Integer.valueOf(req.getParameter("pos")),
                            Integer.valueOf(req.getParameter("neg")) + 1);
        }

        UserSessionInfoDto user = (UserSessionInfoDto) req.getSession().getAttribute("user");

        ReviewDaoImpl.getInstance().updateReviewMarks(review, user.getId(), Boolean.valueOf(req.getParameter("first")));

//        AppUser appUser = AppUserDaoImpl.getInstance().findUserByEmail(user.getEmail()).get();
//        req.getSession().setAttribute("user", UserSessionInfoConverter.INSTANCE.toDto(appUser));

        Long i = Long.valueOf(req.getParameter("id"));

        req.setAttributes("reviews", ReviewDaoImpl.getInstance().findAllForParticularCinemaProduct(i));


        return resp;
    }
}
