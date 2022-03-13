package com.mlb.api.dao.model;

public class GameTeams {

    private TeamDetail away;
    private TeamDetail home;

    public TeamDetail getAway() {
        return away;
    }

    public void setAway(TeamDetail away) {
        this.away = away;
    }

    public TeamDetail getHome() {
        return home;
    }

    public void setHome(TeamDetail home) {
        this.home = home;
    }
}
