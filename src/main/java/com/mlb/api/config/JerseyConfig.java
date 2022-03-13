package com.mlb.api.config;

import com.mlb.api.rest.ScheduleRestController;
import com.mlb.api.rest.model.InvalidRequestException;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {

        // Register Api Controllers
        registerRestControllers();

        // Register Exception Mappers
        registerExceptionMappers();
    }

    private void registerRestControllers() {
        // Add Controllers Here
        register(ScheduleRestController.class);
    }

    private void registerExceptionMappers() {
        // Add Exception Mappers Here
        register(InvalidRequestException.class);
    }
}
