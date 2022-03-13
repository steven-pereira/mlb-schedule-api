package com.mlb.api.rest;

import com.mlb.api.rest.model.InvalidRequestException;
import com.mlb.api.service.IMlbScheduleService;
import com.mlb.api.dao.model.MlbScheduleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;

@Controller
@Path("/api/schedule")
public class ScheduleRestController implements IScheduleRestController {

    @Autowired
    private IMlbScheduleService mlbScheduleService;

    @GET
    @Path("/mlb")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSchedule(@QueryParam("language") String language,
                                @QueryParam("date") String date,
                                @QueryParam("favoriteTeamId") Long favoriteTeamId) throws InvalidRequestException {

        LocalDate dt = parseDate(date);

        MlbScheduleResponse mlbScheduleResponse = mlbScheduleService.getSchedule(favoriteTeamId, dt, language);

        return Response.ok(mlbScheduleResponse).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response getSchedule(@QueryParam("language") String language,
                                @QueryParam("sportId") Long sportId,
                                @QueryParam("date") String date,
                                @QueryParam("favoriteTeamId") Long favoriteTeamId) throws InvalidRequestException {
        if (sportId == null) {
            throw new InvalidRequestException("Missing required parameter sportId");
        }

        LocalDate dt = parseDate(date);

        MlbScheduleResponse mlbScheduleResponse = mlbScheduleService.getSchedule(favoriteTeamId, dt, sportId, language);

        return Response.ok(mlbScheduleResponse).build();
    }

    /**
     * Takes a string date input and returns a LocalDate representation.
     *
     * If the string date input is invalid, an InvalidRequestException is thrown.
     *
     * @param date
     * @return LocalDate representation of the input string
     * @throws InvalidRequestException
     */
    private LocalDate parseDate(String date) throws InvalidRequestException {
        if (date == null) {
            return null;
        }

        LocalDate dt;
        try {
            dt = LocalDate.parse(date);
        } catch (Exception e) {
            throw new InvalidRequestException("Invalid Request with value: " + date);
        }
        return dt;
    }
}
