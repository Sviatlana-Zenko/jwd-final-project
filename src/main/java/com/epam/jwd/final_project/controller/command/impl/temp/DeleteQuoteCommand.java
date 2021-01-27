package com.epam.jwd.final_project.controller.command.impl.temp;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.impl.ResponseContextImpl;
import com.epam.jwd.final_project.domain.Quote;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.service.impl.QuoteServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteQuoteCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteQuoteCommand.class);

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        ResponseContext responseContext = new ResponseContextImpl(ResponseContext.ResponseType.FORWARD,
                "/WEB-INF/jsp/work-with-quotes.jsp");

        Long quoteId = Long.valueOf(requestContext.getParameter("id"));

        try {
            QuoteServiceImpl.INSTANCE.delete(new Quote(quoteId));
        } catch (DatabaseInteractionException e) {
            LOGGER.error(e.getMessage());
            ((ResponseContextImpl) responseContext).setPage("/WEB-INF/jsp/database-error.jsp");
        }

        return responseContext;
    }
}
