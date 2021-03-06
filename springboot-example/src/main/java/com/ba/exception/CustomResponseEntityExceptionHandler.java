package com.ba.exception;

import com.ba.dto.BusinessRuleException;
import com.ba.dto.ErrorResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessRuleException.class)
    public final ResponseEntity<ErrorResponseDTO> handleBusinessRuleException(BusinessRuleException e, WebRequest request) {
        ErrorResponseDTO response = prepareResponseModel(e.getMessage(), request);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SystemException.class)
    public final ResponseEntity<ErrorResponseDTO> handleSystemException(SystemException e, WebRequest request) {
        ErrorResponseDTO response = prepareResponseModel(e.getMessage(), request);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponseDTO> handlerException(Exception e, WebRequest request) {
        ErrorResponseDTO response = prepareResponseModel(e.getMessage(), request);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        ErrorResponseDTO response = new ErrorResponseDTO(
                new Date(),
                "Validation Failed",
                ex.getConstraintViolations().iterator().next().getMessageTemplate());
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    ErrorResponseDTO response =
        new ErrorResponseDTO(
            new Date(),
            "Validation Failed",
            ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
  }

    private ErrorResponseDTO prepareResponseModel(String message, WebRequest request) {
        return new ErrorResponseDTO(new Date(), message, request.getDescription(false));
    }
}
