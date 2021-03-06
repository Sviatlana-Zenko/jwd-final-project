package com.epam.jwd.final_project.context.impl;

import com.epam.jwd.final_project.context.ApplicationContext;
import com.epam.jwd.final_project.domain.*;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import com.epam.jwd.final_project.service.impl.AppUserServiceImpl;
import com.epam.jwd.final_project.service.impl.CinemaProductServiceImpl;
import com.epam.jwd.final_project.service.impl.QuoteServiceImpl;
import java.util.ArrayList;
import java.util.Collection;

public class RatingContext implements ApplicationContext {

    public static final RatingContext INSTANCE = new RatingContext();

    private RatingContext(){
    }

    private Collection<Quote> quotes = new ArrayList<>();
    private Collection<CinemaProduct> recommended = new ArrayList<>();
    private Collection<AppUser> appUsers = new ArrayList<>();


    /**
     * Returns Collection of objects that have the specified class.
     *
     * @param tClass class of objects to return
     * @param <T> the type of elements in this class
     * @return {@link Collection} of specified objects
     */
    @Override
    public <T extends AppEntity> Collection<T> retrieveList(Class<T> tClass) {
        if (tClass.getSimpleName().equals(Quote.class.getSimpleName())) {
            return (Collection<T>) quotes;
        } else if (tClass.getSimpleName().equals(CinemaProduct.class.getSimpleName())) {
            return (Collection<T>) recommended;
        } else {
            return (Collection<T>) appUsers;
        }

    }

    /**
     * Initializes Collections of objects which define the cache
     * required for application work.
     *
     * @throws DatabaseInteractionException if an SQLException occurs at
     * the time of working with database
     */
    @Override
    public void init() throws DatabaseInteractionException {
        quotes = QuoteServiceImpl.INSTANCE.findAll();
        recommended = CinemaProductServiceImpl.INSTANCE.findRecommendations();
        appUsers = AppUserServiceImpl.getInstance().findAll();
    }

    /**
     * Reinitializes Collection of objects with specified
     * class after adding a new object of this class to the database
     * or updating an existing one.
     *
     * @throws DatabaseInteractionException if an SQLException occurs at the
     * time of working with database
     */
    @Override
    public <T extends AppEntity> void reinit(Class<T> tClass)
            throws DatabaseInteractionException {
        if (tClass.getSimpleName().equals(Quote.class.getSimpleName())) {
            quotes = QuoteServiceImpl.INSTANCE.findAll();
        } else if (tClass.getSimpleName().equals(CinemaProduct.class.getSimpleName())) {
            recommended = CinemaProductServiceImpl.INSTANCE.findRecommendations();
        } else {
            appUsers = AppUserServiceImpl.getInstance().findAll();
        }
    }

}
