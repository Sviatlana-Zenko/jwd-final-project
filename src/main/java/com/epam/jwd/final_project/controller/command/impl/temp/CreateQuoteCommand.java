package com.epam.jwd.final_project.controller.command.impl.temp;

import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.impl.ResponseContextImpl;
import com.epam.jwd.final_project.domain.Quote;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.exception.ValidationException;
import com.epam.jwd.final_project.service.impl.QuoteServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateQuoteCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreateQuoteCommand.class);

    @Override
    public ResponseContext execute(RequestContext requestContext) {

        ResponseContext responseContext = new ResponseContextImpl(ResponseContext.ResponseType.REDIRECT);

        String productTitle = requestContext.getParameter("prod-title");
        String quoteText = requestContext.getParameter("quote-txt");
        String posterUrl = requestContext.getParameter("poster-url");

        Quote newQuote = new Quote(productTitle, quoteText, posterUrl);
        try {
            QuoteServiceImpl.INSTANCE.create(newQuote);
            ((ResponseContextImpl) responseContext).setPage("/home?command=quote-operations");
        } catch (ValidationException e) {
            LOGGER.error(e.getMessage());
            requestContext.getSession().setAttribute("errors", e.getValidationErrors());
            ((ResponseContextImpl) responseContext).setPage("/home?command=quote-error");
        } catch (DatabaseInteractionException e) {
            LOGGER.error(e.getMessage());
            ((ResponseContextImpl) responseContext).setPage("/home?command=db-error");
        }

        return responseContext;
    }

}
