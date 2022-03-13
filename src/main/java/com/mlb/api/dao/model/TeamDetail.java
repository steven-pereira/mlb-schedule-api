package com.mlb.api.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamDetail {

    private LeagueRecord leagueRecord;
    private Team team;
    private Integer score;

    public Boolean getIsWinner() {
        return isWinner;
    }

    public void setIsWinner(Boolean winner) {
        isWinner = winner;
    }

    private Boolean isWinner;
    private Boolean splitSquad;
    private Long seriesNumber;

    public LeagueRecord getLeagueRecord() {
        return leagueRecord;
    }

    public void setLeagueRecord(LeagueRecord leagueRecord) {
        this.leagueRecord = leagueRecord;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Boolean getSplitSquad() {
        return splitSquad;
    }

    public void setSplitSquad(Boolean splitSquad) {
        this.splitSquad = splitSquad;
    }

    public Long getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(Long seriesNumber) {
        this.seriesNumber = seriesNumber;
    }
}
