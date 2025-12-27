package com.example.FarmLink.demo.exception;

import com.example.FarmLink.demo.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionController extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ResponseDto responseDto = new ResponseDto(status.toString(), ex.getBindingResult().toString());
        return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ResponseDto> exceptionHandler(Exception exception){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatusCode("500");
        responseDto.setStatusMsg(exception.getMessage());

        return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
