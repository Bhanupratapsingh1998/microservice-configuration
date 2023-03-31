package com.hotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.hotel.helper.ApiResponse;

@ControllerAdvice
public class ExceptionHandlerController {

   @ExceptionHandler(ResourceNotFoundException.class)
   public ResponseEntity<Object> handleUserNotFoundException(ResourceNotFoundException ex) {
	   ApiResponse errorResponse = new ApiResponse();
	   errorResponse.setStatus_code(404);
	   errorResponse.setMessage(ex.getMessage());
      return new ResponseEntity<Object>(errorResponse, HttpStatus.NOT_FOUND);
   }

}