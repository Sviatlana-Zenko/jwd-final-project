package com.epam.jwd.final_project.controller.command.impl;

import com.epam.jwd.final_project.context.impl.RatingContext;
import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.ResponseContext.ResponseType;
import com.epam.jwd.final_project.domain.Quote;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.service.impl.QuoteServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteQuoteCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteQuoteCommand.class);

    @Override
    public ResponseContext execute(RequestContext req) {
        ResponseContext resp = new ResponseContextImpl(ResponseType.FORWARD);
        Long quoteId = Long.valueOf(req.getParameter("id"));

        try {
            QuoteServiceImpl.INSTANCE.delete(new Quote(quoteId));
            RatingContext.INSTANCE.reinit(Quote.class);
            ((ResponseContextImpl) resp).setPage("/WEB-INF/jsp/work-with-quotes.jsp");
        } catch (DatabaseInteractionException e) {
            LOGGER.error(e.getMessage());
            ((ResponseContextImpl) resp).setPage("/WEB-INF/jsp/database-error.jsp");
        }

        return resp;
    }

}
