package com.mlb.api.dao;

import com.mlb.api.dao.model.MlbScheduleResponse;

import java.time.LocalDate;

public interface IMlbScheduleDao {

    MlbScheduleResponse getSchedule(LocalDate date);

    MlbScheduleResponse getSchedule(LocalDate date, Long sportId);

    MlbScheduleResponse getSchedule(LocalDate date, Long sportId, String language);
}
