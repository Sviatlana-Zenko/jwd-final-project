package com.epam.jwd.final_project.controller.command.impl;

import com.epam.jwd.final_project.controller.command.ResponseContext;

public class ResponseContextImpl implements ResponseContext {

    private ResponseType responseType;
    private String page;

    public ResponseContextImpl(ResponseType responseType, String page) {
        this.responseType = responseType;
        this.page = page;
    }

    public ResponseContextImpl(ResponseType responseType) {
        this.responseType = responseType;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public String getPage() {
        return page;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public void setPage(String page) {
        this.page = page;
    }

}
