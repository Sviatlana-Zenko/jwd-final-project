package com.epam.jwd.final_project.controller.command.impl.temp;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.impl.ResponseContextImpl;
import com.epam.jwd.final_project.criteria.AppUserCriteria;
import com.epam.jwd.final_project.criteria.Criteria;
import com.epam.jwd.final_project.dao.impl.AppUserDaoImpl;
import com.epam.jwd.final_project.domain.AppUser;

public class ChangeBlockStatusCommand implements Command {
    @Override
    public ResponseContext execute(RequestContext requestContext) {
        ResponseContext responseContext = new ResponseContextImpl(ResponseContext.ResponseType.FORWARD,
                "/WEB-INF/jsp/show-users.jsp");

        Long userId = Long.valueOf(requestContext.getParameter("id"));
        String status = requestContext.getParameter("status");

        Criteria<AppUser> criteria = null;
        if (status.equals("block")) {
            criteria = new AppUserCriteria.AppUserCriteriaBuilder() {{
                isBanned(true);
            }}.build();
        } else {
            criteria = new AppUserCriteria.AppUserCriteriaBuilder() {{
                isBanned(false);
            }}.build();
        }

        System.out.println(criteria);

//        AppUserDaoImpl.getInstance().updateByCriteria(criteria, new AppUser(userId));

        return responseContext;
    }
}
