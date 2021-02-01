package com.epam.jwd.final_project.context;

import com.epam.jwd.final_project.context.impl.RatingContext;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import java.util.function.Supplier;

public interface Application {

    static void start() throws DatabaseInteractionException {
        final Supplier<ApplicationContext> appSupplier = () -> RatingContext.INSTANCE;
        appSupplier.get().init();
    }

}
