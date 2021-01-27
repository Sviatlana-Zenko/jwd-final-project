package com.epam.jwd.final_project.controller.command;

public interface Command {

    ResponseContext execute(RequestContext requestContext);

}
