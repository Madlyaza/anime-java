//package com.exception;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import javax.servlet.http.HttpServletRequest;
//
//@RestControllerAdvice
//public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(BadRequestException.class)
//    public ErrorMessage badRequestException(BadRequestException exception, HttpServletRequest request) {
//        return new ErrorMessage(String.format(exception.getMessage()));
//    }
//
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(DataNotFoundException.class)
//    public ErrorMessage dataNotFoundException(DataNotFoundException exception, HttpServletRequest request) {
//        return new ErrorMessage(String.format(exception.getMessage()));
//    }
//
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(NoContentException.class)
//    public ErrorMessage noContentException(NoContentException exception, HttpServletRequest request) {
//        return new ErrorMessage(String.format(exception.getMessage()));
//    }
//
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ExceptionHandler(RuntimeException.class)
//    public ErrorMessage generalException(RuntimeException exception) {
//        return new ErrorMessage("Something went wrong when executing the request.");
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return ResponseEntity.badRequest().body(new ErrorMessage("The input was incorrect"));
//    }
//
//    @Override
//    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return ResponseEntity.badRequest().body(new ErrorMessage("The input was incorrect"));
//    }
//
//
//}
