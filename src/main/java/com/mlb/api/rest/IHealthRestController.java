package com.mlb.api.rest;

import javax.ws.rs.core.Response;

public interface IHealthRestController {

    Response healthCheck();
}
