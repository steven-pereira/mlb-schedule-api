package com.mlb.api.rest.model;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidRequestException extends Exception implements ExceptionMapper<InvalidRequestException> {

    public InvalidRequestException() {
        super();
    }

    public InvalidRequestException(String message) {
        super(message);
    }

    @Override
    public Response toResponse(InvalidRequestException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(new InvalidRequestResponse(exception.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
