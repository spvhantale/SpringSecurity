package com.swapnil.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {

	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<MyError> getCustomerException(CustomerException cust,WebRequest req){
		
		MyError myError=new MyError(cust.getMessage(), LocalDateTime.now(), req.getDescription(false));
		
		return new ResponseEntity<MyError>(myError, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyError> exception(Exception cust,WebRequest req){
		
		MyError myError=new MyError(cust.getMessage(), LocalDateTime.now(), req.getDescription(false));
		
		return new ResponseEntity<MyError>(myError, HttpStatus.BAD_REQUEST);
	}
}
