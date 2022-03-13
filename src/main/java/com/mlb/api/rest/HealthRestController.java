package com.mlb.api.rest;

import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Controller
@Path("/health")
public class HealthRestController implements IHealthRestController {

    @GET
    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response healthCheck() {
        return Response.ok("App running healthy...").build();
    }
}
