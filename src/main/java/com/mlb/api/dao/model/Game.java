package com.mlb.api.dao.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Game {
    private Long gamePk;
    private String link;
    private String gameType;
    private String season;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime gameDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate officialDate;
    private LocalDateTime rescheduledFrom;
    private LocalDate rescheduledFromDate;
    private GameStatus status;
    private GameTeams teams;
    private Venue venue;
    private Content content;
    private Boolean isTie;
    private Long gameNumber;
    private Boolean publicFacing;
    private String doubleHeader;
    private String gamedayType;
    private String tiebreaker;
    private String calendarEventID;
    private String seasonDisplay;
    private String dayNight;
    private String description;
    private Integer scheduledInnings;
    private Boolean reverseHomeAwayStatus;
    private Integer inningBreakLength;
    private Integer gamesInSeries;
    private Integer seriesGameNumber;
    private String seriesDescription;
    private String recordSource;
    private String ifNecessary;
    private String ifNecessaryDescription;

    public Long getGamePk() {
        return gamePk;
    }

    public void setGamePk(Long gamePk) {
        this.gamePk = gamePk;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public LocalDateTime getGameDate() {
        return gameDate;
    }

    public void setGameDate(LocalDateTime gameDate) {
        this.gameDate = gameDate;
    }

    public LocalDate getOfficialDate() {
        return officialDate;
    }

    public void setOfficialDate(LocalDate officialDate) {
        this.officialDate = officialDate;
    }

    public LocalDateTime getRescheduledFrom() {
        return rescheduledFrom;
    }

    public void setRescheduledFrom(LocalDateTime rescheduledFrom) {
        this.rescheduledFrom = rescheduledFrom;
    }

    public LocalDate getRescheduledFromDate() {
        return rescheduledFromDate;
    }

    public void setRescheduledFromDate(LocalDate rescheduledFromDate) {
        this.rescheduledFromDate = rescheduledFromDate;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public GameTeams getTeams() {
        return teams;
    }

    public void setTeams(GameTeams teams) {
        this.teams = teams;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Boolean getIsTie() {
        return isTie;
    }

    public void setIsTie(Boolean tie) {
        isTie = tie;
    }

    public Long getGameNumber() {
        return gameNumber;
    }

    public void setGameNumber(Long gameNumber) {
        this.gameNumber = gameNumber;
    }

    public Boolean getPublicFacing() {
        return publicFacing;
    }

    public void setPublicFacing(Boolean publicFacing) {
        this.publicFacing = publicFacing;
    }

    public String getDoubleHeader() {
        return doubleHeader;
    }

    public void setDoubleHeader(String doubleHeader) {
        this.doubleHeader = doubleHeader;
    }

    public String getGamedayType() {
        return gamedayType;
    }

    public void setGamedayType(String gamedayType) {
        this.gamedayType = gamedayType;
    }

    public String getTiebreaker() {
        return tiebreaker;
    }

    public void setTiebreaker(String tiebreaker) {
        this.tiebreaker = tiebreaker;
    }

    public String getCalendarEventID() {
        return calendarEventID;
    }

    public void setCalendarEventID(String calendarEventID) {
        this.calendarEventID = calendarEventID;
    }

    public String getSeasonDisplay() {
        return seasonDisplay;
    }

    public void setSeasonDisplay(String seasonDisplay) {
        this.seasonDisplay = seasonDisplay;
    }

    public String getDayNight() {
        return dayNight;
    }

    public void setDayNight(String dayNight) {
        this.dayNight = dayNight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getScheduledInnings() {
        return scheduledInnings;
    }

    public void setScheduledInnings(Integer scheduledInnings) {
        this.scheduledInnings = scheduledInnings;
    }

    public Boolean getReverseHomeAwayStatus() {
        return reverseHomeAwayStatus;
    }

    public void setReverseHomeAwayStatus(Boolean reverseHomeAwayStatus) {
        this.reverseHomeAwayStatus = reverseHomeAwayStatus;
    }

    public Integer getInningBreakLength() {
        return inningBreakLength;
    }

    public void setInningBreakLength(Integer inningBreakLength) {
        this.inningBreakLength = inningBreakLength;
    }

    public Integer getGamesInSeries() {
        return gamesInSeries;
    }

    public void setGamesInSeries(Integer gamesInSeries) {
        this.gamesInSeries = gamesInSeries;
    }

    public Integer getSeriesGameNumber() {
        return seriesGameNumber;
    }

    public void setSeriesGameNumber(Integer seriesGameNumber) {
        this.seriesGameNumber = seriesGameNumber;
    }

    public String getSeriesDescription() {
        return seriesDescription;
    }

    public void setSeriesDescription(String seriesDescription) {
        this.seriesDescription = seriesDescription;
    }

    public String getRecordSource() {
        return recordSource;
    }

    public void setRecordSource(String recordSource) {
        this.recordSource = recordSource;
    }

    public String getIfNecessary() {
        return ifNecessary;
    }

    public void setIfNecessary(String ifNecessary) {
        this.ifNecessary = ifNecessary;
    }

    public String getIfNecessaryDescription() {
        return ifNecessaryDescription;
    }

    public void setIfNecessaryDescription(String ifNecessaryDescription) {
        this.ifNecessaryDescription = ifNecessaryDescription;
    }
}
