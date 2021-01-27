package com.epam.jwd.final_project.controller.command.impl.temp;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.impl.ResponseContextImpl;
import com.epam.jwd.final_project.converter.impl.UserSessionInfoConverter;
import com.epam.jwd.final_project.domain.AppUser;
import com.epam.jwd.final_project.domain.Review;
import com.epam.jwd.final_project.dto.UserSessionInfoDto;
import com.epam.jwd.final_project.service.impl.AppUserServiceImpl;

public class CreateReviewCommand implements Command {
    @Override
    public ResponseContext execute(RequestContext requestContext) {
        ResponseContext responseContext = new ResponseContextImpl(ResponseContext.ResponseType.REDIRECT);

        System.out.println("query + " + requestContext.getQueryString());

        Long id = Long.valueOf(requestContext.getParameter("id"));

        Byte rate = null;

        for (int j = 10; j > 0 ; j--) {
            if (requestContext.getParameter("rate-" + j) != null) {
                rate = (byte) j;
                break;
            }
        }

        String summary = requestContext.getParameter("summary");
        String text = requestContext.getParameter("review-text");

//        requestContext.getParameter(genres.get(i)).equals("on")

        boolean hasSpoilers = false;

        if (requestContext.getParameter("spoilers").equals("on")) {
            hasSpoilers = true;
        }

        UserSessionInfoDto userDto = (UserSessionInfoDto) requestContext.getSession().getAttribute("user");

        AppUser user = UserSessionInfoConverter.INSTANCE.toEntity(userDto);
        Review newReview = new Review(id, user.getId(), Byte.valueOf(rate), summary, text, hasSpoilers);

        AppUserServiceImpl.getInstance().createUserReview(user, newReview);

//        ReviewServiceImpl.INSTANCE.create(new Review(id, user.getId(), Byte.valueOf(rate), summary, text));

        ((ResponseContextImpl) responseContext).setPage("/home?command=show-reviews&id=" + id);

        return responseContext;
    }
}
