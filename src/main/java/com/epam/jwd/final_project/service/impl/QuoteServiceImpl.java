package com.epam.jwd.final_project.service.impl;

import com.epam.jwd.final_project.context.impl.RatingContext;
import com.epam.jwd.final_project.criteria.QuoteCriteria;
import com.epam.jwd.final_project.dao.impl.QuoteDaoImpl;
import com.epam.jwd.final_project.domain.Quote;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.exception.ValidationException;
import com.epam.jwd.final_project.pool.ConnectionPool;
import com.epam.jwd.final_project.service.QuoteService;
import com.epam.jwd.final_project.validation.ValidationChain;
import com.epam.jwd.final_project.validation.ValidationChainFactory;
import com.epam.jwd.final_project.validation.ValidationType;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class QuoteServiceImpl implements QuoteService {

    public static final QuoteServiceImpl INSTANCE = new QuoteServiceImpl();

    private QuoteServiceImpl() {
    }

    @Override
    public boolean create(Quote quote) throws ValidationException, DatabaseInteractionException {
        boolean wasCreated;
        ValidationChain<Quote> chain = ValidationChainFactory.INSTANCE.
                createValidationChain(quote);
        List<String> validationErrors = chain.getValidationReport(quote, ValidationType.CREATE_OBJECT);

        if (validationErrors.size() == 0) {
            wasCreated = QuoteDaoImpl.getInstance().create(quote, ConnectionPool.INSTANCE.getAvailableConnection());
            if (wasCreated) {
                RatingContext.INSTANCE.reinit(Quote.class);
            }
        } else {
            throw new ValidationException(Quote.class.getSimpleName(), validationErrors);
        }

        return wasCreated;
    }

    @Override
    public List<Quote> findAll() throws DatabaseInteractionException {
        return QuoteDaoImpl.getInstance().findAll(ConnectionPool.INSTANCE.getAvailableConnection());
    }

    @Override
    public Optional<Quote> findById(Long id) throws DatabaseInteractionException {
        return QuoteDaoImpl.getInstance().findById(id, ConnectionPool.INSTANCE.getAvailableConnection());
    }

    @Override
    public Quote updateByCriteria(Quote quote, QuoteCriteria quoteCriteria) throws ValidationException, DatabaseInteractionException {
        ValidationChain<Quote> chain = ValidationChainFactory.INSTANCE.
                createValidationChain(quote);
        List<String> validationErrors = chain.getValidationReport(quote, ValidationType.UPDATE_OBJECT);

        if (validationErrors.size() == 0) {
            quote = QuoteDaoImpl.getInstance().updateByCriteria(quote, quoteCriteria, ConnectionPool.INSTANCE.getAvailableConnection());
            RatingContext.INSTANCE.reinit(Quote.class);
        } else {
            throw new ValidationException(Quote.class.getSimpleName(), validationErrors);
        }

        return quote;
    }

    @Override
    public List<String> getAllDistinctTitles() {
        return RatingContext.INSTANCE.retrieveList(Quote.class).stream()
                .map(quote -> quote.getProductTitle())
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(Quote quote) throws DatabaseInteractionException {
        if (QuoteDaoImpl.getInstance().delete(quote, ConnectionPool.INSTANCE.getAvailableConnection())) {
            RatingContext.INSTANCE.reinit(Quote.class);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Quote getRandomQuote() {
        List<Quote> quotes = (List<Quote>) RatingContext.INSTANCE.retrieveList(Quote.class);
        int randomNumb = new Random().nextInt(quotes.size());

        return quotes.get(randomNumb);
    }

}
