package com.epam.jwd.final_project.controller.command.impl;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.ResponseContext.ResponseType;
import com.epam.jwd.final_project.dao.impl.AppUserDaoImpl;
import com.epam.jwd.final_project.dao.impl.CinemaProductDaoImpl;
import com.epam.jwd.final_project.dao.impl.ReviewDaoImpl;
import com.epam.jwd.final_project.domain.CinemaProduct;
import com.epam.jwd.final_project.domain.Movie;
import com.epam.jwd.final_project.domain.TvSeries;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.pool.ConnectionPool;

public class ToSignInFormCommand implements Command {

    @Override
    public ResponseContext execute(RequestContext req) {
        ResponseContext resp = new ResponseContextImpl(ResponseType.FORWARD,
                "/WEB-INF/jsp/sign-in-form.jsp");

        return resp;
    }

}
