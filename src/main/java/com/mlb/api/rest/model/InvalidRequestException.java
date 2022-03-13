package com.mlb.api.rest.model;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidRequestException extends Exception implements ExceptionMapper<InvalidRequestException> {

    private String input;

    public InvalidRequestException() {
        super();
    }

    public InvalidRequestException(String input) {
        super();
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    @Override
    public Response toResponse(InvalidRequestException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(new InvalidRequestResponse(exception.getInput()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
