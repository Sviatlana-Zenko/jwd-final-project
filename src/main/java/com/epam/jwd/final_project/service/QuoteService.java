package com.epam.jwd.final_project.service;

import com.epam.jwd.final_project.criteria.QuoteCriteria;
import com.epam.jwd.final_project.domain.Quote;

import java.util.List;

public interface QuoteService extends EntityService<Quote, QuoteCriteria> {

    /**
     * Returns one randomly selected {@link Quote} from the {@link java.util.List} of
     * all Quote objects.
     * @return a random Quote
     */
    Quote getRandomQuote();

    /**
     * Create and returns a {@link java.util.List} of all unique movies/TV series titles.
     * @return List of unique titles
     */
    List<String> getAllDistinctTitles();

}
