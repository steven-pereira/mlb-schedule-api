package com.mlb.api.service;

import com.mlb.api.dao.model.MlbScheduleResponse;

import java.time.LocalDate;

public interface IMlbScheduleService {

    MlbScheduleResponse getSchedule(Long favoriteTeamId, LocalDate date);

    MlbScheduleResponse getSchedule(Long favoriteTeamId, LocalDate date, String language);

    MlbScheduleResponse getSchedule(Long favoriteTeamId, LocalDate date, Long sportId, String language);
}
