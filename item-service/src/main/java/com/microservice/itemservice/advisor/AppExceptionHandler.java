package com.microservice.itemservice.advisor;


import com.microservice.itemservice.exception.NotFoundException;
import com.microservice.itemservice.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(NotFoundException e){
        return new ResponseEntity<StandardResponse>(new StandardResponse(404, "error", e.getMessage())
                , HttpStatus.NOT_FOUND);
    }
}
