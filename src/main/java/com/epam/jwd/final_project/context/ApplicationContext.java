package com.epam.jwd.final_project.context;

import com.epam.jwd.final_project.domain.AppEntity;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;
import java.util.Collection;

public interface ApplicationContext {

    <T extends AppEntity> Collection<T> retrieveList(Class<T> tClass);

    void init() throws DatabaseInteractionException;

    <T extends AppEntity> void reinit(Class<T> tClass) throws DatabaseInteractionException;

}
