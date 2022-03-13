package com.mlb.api.rest.model;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class InvalidRequestResponse {

    private Long messageNumber;
    private String message;
    private String timestamp;
    private Long traceId;

    public InvalidRequestResponse(String inputValue) {
        this.messageNumber = null;
        this.message = String.format("Invalid Request with value: %s", inputValue);
        this.traceId = null;

        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        this.timestamp = dateTimeFormat.format(ZonedDateTime.now());
    }

    public Long getMessageNumber() {
        return messageNumber;
    }

    public void setMessageNumber(Long messageNumber) {
        this.messageNumber = messageNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Long getTraceId() {
        return traceId;
    }

    public void setTraceId(Long traceId) {
        this.traceId = traceId;
    }
}
