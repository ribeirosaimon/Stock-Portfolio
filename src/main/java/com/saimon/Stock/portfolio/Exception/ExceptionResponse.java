package com.saimon.Stock.portfolio.Exception;

import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Date timestamp = new Date();
    private String message;

    public ExceptionResponse(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }
}
