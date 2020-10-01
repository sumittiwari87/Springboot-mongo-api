package com.mycloudknowledge.evaluation.exception;

import java.util.Date;

public class ExceptionResponse {
    private Date timeStamp;
    private int code;
    private String message;
    private String details;

    public ExceptionResponse(Date timeStamp, int code, String message, String details) {
        this.timeStamp = timeStamp;
        this.code = code;
        this.message = message;
        this.details = details;
    }

    @Override
    public String toString() {
        return "ExceptionResponse{" +
                "timeStamp=" + timeStamp +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", details='" + details + '\'' +
                '}';
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}