package com.mlb.api.rest;

import com.mlb.api.rest.model.InvalidRequestException;

import javax.ws.rs.core.Response;

public interface IScheduleRestController {

    /** Add Swagger Documentation Here */
    Response getSchedule(String language, Long sportId, String date, Long favoriteTeamId) throws InvalidRequestException;
}
