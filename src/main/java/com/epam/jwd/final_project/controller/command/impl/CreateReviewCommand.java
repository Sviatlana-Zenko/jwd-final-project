package com.epam.jwd.final_project.controller.command.impl;

import com.epam.jwd.final_project.context.impl.RatingContext;
import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.ResponseContext.ResponseType;
import com.epam.jwd.final_project.converter.impl.UserSessionInfoConverter;
import com.epam.jwd.final_project.domain.AppUser;
import com.epam.jwd.final_project.domain.Review;
import com.epam.jwd.final_project.dto.UserSessionInfoDto;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.exception.ValidationException;
import com.epam.jwd.final_project.service.impl.AppUserServiceImpl;
import com.epam.jwd.final_project.service.impl.ReviewServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateReviewCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateReviewCommand.class);

    @Override
    public ResponseContext execute(RequestContext req) {
        ResponseContext resp = new ResponseContextImpl(ResponseType.REDIRECT);
        String id = req.getParameter("id");
        String summary = req.getParameter("summary");
        String text = req.getParameter("rev-text");
        UserSessionInfoDto userDto = (UserSessionInfoDto) req.getSession().getAttribute("user");
        AppUser user = UserSessionInfoConverter.INSTANCE.toEntity(userDto);
        Review newReview = new Review(Long.valueOf(id), user.getId(), getRate(req),
                summary, text, getSpoilerStatus(req));

        try {
            if(ReviewServiceImpl.INSTANCE.create(newReview)) {
                AppUserServiceImpl.getInstance().addReviewedProduct(user.getId(), Long.valueOf(id));
                RatingContext.INSTANCE.reinit(AppUser.class);
                AppUser updated = AppUserServiceImpl.getInstance().findById(user.getId()).get();
                req.getSession().setAttribute("user", UserSessionInfoConverter.INSTANCE.toDto(updated));
                ((ResponseContextImpl) resp).setPage("/home?command=show-reviews&id=" + id);
            }
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

    private Byte getRate(RequestContext req) {
        Byte rate = 0;
        for (int i = 10; i > 0 ; i--) {
            if (req.getParameter("rate-" + i) != null) {
                rate = (byte) i;
                break;
            }
        }

        return rate;
    }

    private boolean getSpoilerStatus(RequestContext req) {
        boolean hasSpoilers = false;
        if (req.getParameter("spoilers") != null &&
                req.getParameter("spoilers").equals("on")) {
            hasSpoilers = true;
        }

        return hasSpoilers;
    }

}
