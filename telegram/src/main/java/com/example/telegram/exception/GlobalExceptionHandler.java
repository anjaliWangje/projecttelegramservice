package com.example.telegram.exception;


import com.example.telegram.dto.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(InvalidRequestException.class)
    @ResponseBody
    public ResponseEntity<ResponseDto> handleInvalidRequest(InvalidRequestException ex) {
        logger.warn("InvalidRequestException: {}", ex.getMessage());
        return new ResponseEntity<>(new ResponseDto("400", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TelegramApiException.class)
    @ResponseBody
    public ResponseEntity<ResponseDto> handleTelegramApi(TelegramApiException ex) {
        logger.error("TelegramApiException: {}", ex.getMessage());
        return new ResponseEntity<>(new ResponseDto("503", "Telegram API error: " + ex.getMessage()), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ResponseDto> handleValidation(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("; "));
        logger.warn("Validation failed: {}", errors);
        return new ResponseEntity<>(new ResponseDto("400", errors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ResponseDto> handleAll(Exception ex) {
        logger.error("Unhandled exception: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(new ResponseDto("500", "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}