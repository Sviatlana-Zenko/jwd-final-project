package com.epam.jwd.final_project.controller.command.impl;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.dto.UserSessionInfoDto;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.service.impl.ReviewServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShowUserReviewsCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShowUserReviewsCommand.class);

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        ResponseContext resp = new ResponseContextImpl(ResponseContext.ResponseType.FORWARD);

        UserSessionInfoDto dto = (UserSessionInfoDto) requestContext.getSession().getAttribute("user");

        try {
            requestContext.setAttributes("userReviews", ReviewServiceImpl.INSTANCE.findAllForConcreteUser(dto.getId()));
            ((ResponseContextImpl) resp).setPage("/WEB-INF/jsp/user-reviews.jsp");
        } catch (DatabaseInteractionException e) {
            LOGGER.error(e.getMessage());
            ((ResponseContextImpl) resp).setPage("/WEB-INF/jsp/database-error.jsp");
        }

        return resp;
    }

}
