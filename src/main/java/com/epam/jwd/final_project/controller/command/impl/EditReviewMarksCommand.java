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
import com.epam.jwd.final_project.service.impl.AppUserServiceImpl;
import com.epam.jwd.final_project.service.impl.ReviewServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EditReviewMarksCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShowProductsCommand.class);

    @Override
    public ResponseContext execute(RequestContext req) {
        ResponseContext resp = new ResponseContextImpl(ResponseType.FORWARD);
        String id = req.getParameter("id");
        String reviewId = req.getParameter("rewid");
        String positiveMarks = req.getParameter("pos");
        String negativeMarks = req.getParameter("neg");
        String operation = req.getParameter("plus");
        boolean isPositive;
        Review review = null;

        if (operation.equals("t")) {
            review = new Review(Long.valueOf(reviewId),
                    Integer.valueOf(positiveMarks) + 1,
                    Integer.valueOf(negativeMarks));
            isPositive = true;
        } else {
            review = new Review(Long.valueOf(reviewId),
                    Integer.valueOf(positiveMarks),
                            Integer.valueOf(negativeMarks) + 1);
            isPositive = false;
        }

        UserSessionInfoDto userDto = (UserSessionInfoDto) req.getSession().getAttribute("user");
        AppUser user = UserSessionInfoConverter.INSTANCE.toEntity(userDto);

        try {
            ReviewServiceImpl.INSTANCE.updateReviewMarks(review);
            AppUserServiceImpl.getInstance().addRatedReview(user.getId(), review.getId(), isPositive);
            RatingContext.INSTANCE.reinit(AppUser.class);
            AppUser updated = AppUserServiceImpl.getInstance().findById(user.getId()).get();
            req.getSession().setAttribute("user", UserSessionInfoConverter.INSTANCE.toDto(updated));
            ((ResponseContextImpl) resp).setPage("/home?command=show-reviews&id=" + id);
        } catch (DatabaseInteractionException e) {
            LOGGER.error(e.getMessage());
            ((ResponseContextImpl) resp).setPage("/home?command=db-error");
        }

        return resp;
    }
}
