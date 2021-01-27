package com.epam.jwd.final_project.listener;

import com.epam.jwd.final_project.context.Application;
import com.epam.jwd.final_project.exception.DatabaseInteractionException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Application.start();
        } catch (DatabaseInteractionException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
