package com.epam.jwd.final_project.controller.command.impl;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.impl.ResponseContextImpl;
import com.epam.jwd.final_project.dto.UserSessionInfoDto;
import com.epam.jwd.final_project.service.impl.ReviewServiceImpl;

public class ShowUserReviewsCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        ResponseContext responseContext = new ResponseContextImpl(ResponseContext.ResponseType.FORWARD,
                "/WEB-INF/jsp/user-reviews.jsp");

        UserSessionInfoDto dto = (UserSessionInfoDto) requestContext.getSession().getAttribute("user");


//        requestContext.setAttributes("userReviews",
//                ReviewServiceImpl.INSTANCE.getAllForParticularUser(dto.getId()));

        return responseContext;
    }

}
