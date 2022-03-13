package com.mlb.api.dao;

import com.mlb.api.config.EnvironmentKey;
import com.mlb.api.dao.model.MlbScheduleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;

@Service
public class MlbScheduleDaoImpl implements IMlbScheduleDao {

    private final String LANGUAGE_QUERY_PARAM = "language";
    private final String DATE_QUERY_PARAM = "date";
    private final String SPORT_ID_QUERY_PARAM = "sportId";

    @Autowired
    private Environment env;

    @Override
    public MlbScheduleResponse getSchedule(LocalDate date) {
       return getSchedule(date, Long.valueOf(env.getProperty(EnvironmentKey.DEFAULT_SPORT_ID)));
    }

    @Override
    public MlbScheduleResponse getSchedule(LocalDate date, Long sportId) {
        return getSchedule(date, sportId, env.getProperty(EnvironmentKey.DEFAULT_LANGUAGE));
    }

    @Override
    public MlbScheduleResponse getSchedule(LocalDate date, Long sportId, String language) {
        Client client = ClientBuilder.newClient();

        WebTarget webTarget = client.target(env.getProperty(EnvironmentKey.MLB_SCHEDULE_API_URL))
                .queryParam(LANGUAGE_QUERY_PARAM, language)
                .queryParam(DATE_QUERY_PARAM, date)
                .queryParam(SPORT_ID_QUERY_PARAM, sportId);

        Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        MlbScheduleResponse mlbScheduleResponse = response.readEntity(MlbScheduleResponse.class);

        return mlbScheduleResponse;
    }
}
