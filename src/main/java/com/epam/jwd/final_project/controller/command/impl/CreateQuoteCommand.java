package com.epam.jwd.final_project.controller.command.impl;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.ResponseContext.ResponseType;
import com.epam.jwd.final_project.domain.Quote;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.exception.ValidationException;
import com.epam.jwd.final_project.service.impl.QuoteServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateQuoteCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateQuoteCommand.class);

    @Override
    public ResponseContext execute(RequestContext req) {

        ResponseContext resp = new ResponseContextImpl(ResponseType.REDIRECT);
        String productTitle = req.getParameter("prod-title");
        String quoteText = req.getParameter("quote-txt");
        String posterUrl = req.getParameter("poster-url");
        Quote newQuote = new Quote(productTitle, quoteText, posterUrl);

        try {
            QuoteServiceImpl.INSTANCE.create(newQuote);
            ((ResponseContextImpl) resp).setPage("/home?command=quote-operations");
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

}
