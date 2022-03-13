package com.mlb.api.dao.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class GameDate {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    private Integer totalItems;
    private Integer totalEvents;
    private Integer totalGames;
    private Integer totalGamesInProgress;

    private LinkedList<Game> games;

    // TODO - not sure the data type here
    private List<Object> events;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public LinkedList<Game> getGames() {
        return games;
    }

    public void setGames(LinkedList<Game> games) {
        this.games = games;
    }

    public List<Object> getEvents() {
        return events;
    }

    public void setEvents(List<Object> events) {
        this.events = events;
    }
}
