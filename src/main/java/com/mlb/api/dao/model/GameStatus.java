package com.mlb.api.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GameStatus {

    private String abstractGameState;
    private String codedGameState;
    private String detailedState;
    private String statusCode;
    private Boolean startTimeTBD;
    private String reason;
    private String abstractGameCode;

    public String getAbstractGameState() {
        return abstractGameState;
    }

    public void setAbstractGameState(String abstractGameState) {
        this.abstractGameState = abstractGameState;
    }

    public String getCodedGameState() {
        return codedGameState;
    }

    public void setCodedGameState(String codedGameState) {
        this.codedGameState = codedGameState;
    }

    public String getDetailedState() {
        return detailedState;
    }

    public void setDetailedState(String detailedState) {
        this.detailedState = detailedState;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Boolean getStartTimeTBD() {
        return startTimeTBD;
    }

    public void setStartTimeTBD(Boolean startTimeTBD) {
        this.startTimeTBD = startTimeTBD;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getAbstractGameCode() {
        return abstractGameCode;
    }

    public void setAbstractGameCode(String abstractGameCode) {
        this.abstractGameCode = abstractGameCode;
    }
}
