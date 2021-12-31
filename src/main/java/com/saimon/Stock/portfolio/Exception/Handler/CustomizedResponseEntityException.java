package com.saimon.Stock.portfolio.Exception.Handler;

import com.saimon.Stock.portfolio.Exception.ExceptionResponse;
import com.saimon.Stock.portfolio.Exception.NoMoneyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NoMoneyException.class)
    public final ResponseEntity<ExceptionResponse> noMoneyException(Exception ex, WebRequest req) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                "You don't have money");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);

    }
}
