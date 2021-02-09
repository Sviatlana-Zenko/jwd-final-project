package com.epam.jwd.final_project.controller.command.impl;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.ResponseContext.ResponseType;
import com.epam.jwd.final_project.domain.Quote;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.service.impl.QuoteServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfirmQuoteDeletingCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfirmQuoteDeletingCommand.class);

    @Override
    public ResponseContext execute(RequestContext requestContext) {
        ResponseContext responseContext = new ResponseContextImpl(ResponseType.FORWARD);
        String id = requestContext.getParameter("id");

        try {
            Quote quote = QuoteServiceImpl.INSTANCE.findById(Long.valueOf(id)).get();
            requestContext.setAttributes("quote", quote);
            ((ResponseContextImpl) responseContext).setPage("/WEB-INF/jsp/confirm-quote-deleting.jsp");
        } catch (DatabaseInteractionException e) {
            LOGGER.error(e.getMessage());
            ((ResponseContextImpl) responseContext).setPage("/WEB-INF/jsp/database-error.jsp");
        }

        return responseContext;
    }

}
