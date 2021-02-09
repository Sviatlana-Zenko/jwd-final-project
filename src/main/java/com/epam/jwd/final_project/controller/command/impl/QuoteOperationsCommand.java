package com.epam.jwd.final_project.controller.command.impl;

import com.epam.jwd.final_project.context.impl.RatingContext;
import com.epam.jwd.final_project.controller.command.Command;
import com.epam.jwd.final_project.controller.command.RequestContext;
import com.epam.jwd.final_project.controller.command.ResponseContext;
import com.epam.jwd.final_project.controller.command.ResponseContext.ResponseType;
import com.epam.jwd.final_project.domain.Quote;
import com.epam.jwd.final_project.service.impl.QuoteServiceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuoteOperationsCommand implements Command {
    @Override
    public ResponseContext execute(RequestContext req) {
        ResponseContext resp = new ResponseContextImpl(ResponseType.FORWARD, "/WEB-INF/jsp/work-with-quotes.jsp");
        List<String> distinctTitles = QuoteServiceImpl.INSTANCE.getAllDistinctTitles();
        req.setAttributes("titles" , createMapOfTitles(distinctTitles));
        req.setAttributes("options", createListOfOptions(distinctTitles.size()));
        req.setAttributes("quotes", RatingContext.INSTANCE.retrieveList(Quote.class));

        return resp;
    }

    private List<Integer> createListOfOptions(int numberOfOptions) {
        List<Integer> options = new ArrayList<>();
        for (int i = 1; i <= numberOfOptions; i++) {
            options.add(Integer.valueOf(i));
        }

        return options;
    }

    private Map<Integer, String> createMapOfTitles(List<String> distinctTitles) {
        Map<Integer, String> titles = new HashMap<>();
        for (int i = 0; i < distinctTitles.size(); i++) {
            titles.put(Integer.valueOf(i + 1), distinctTitles.get(i));
        }

        return titles;
    }

}
