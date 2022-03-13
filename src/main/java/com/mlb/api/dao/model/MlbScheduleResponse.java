package com.mlb.api.dao.model;

import java.util.List;

public class MlbScheduleResponse {

    private String copyright;
    private Integer totalItems;
    private Integer totalEvents;
    private Integer totalGames;
    private Integer totalGamesInProgress;

    private List<GameDate> dates;

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public Integer getTotalEvents() {
        return totalEvents;
    }

    public void setTotalEvents(Integer totalEvents) {
        this.totalEvents = totalEvents;
    }

    public Integer getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(Integer totalGames) {
        this.totalGames = totalGames;
    }

    public Integer getTotalGamesInProgress() {
        return totalGamesInProgress;
    }

    public void setTotalGamesInProgress(Integer totalGamesInProgress) {
        this.totalGamesInProgress = totalGamesInProgress;
    }

    public List<GameDate> getDates() {
        return dates;
    }

    public void setDates(List<GameDate> dates) {
        this.dates = dates;
    }
}
