package org.dbs.crawl.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {


	
	@ExceptionHandler(RecordNotFound.class)
	public ResponseEntity<Object> handlingResourceNotFound(RecordNotFound ex){
		
		List<String> details=new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error=new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND,details);
		
		return new ResponseEntity<>(error,error.getStatus());
		
	}
	
	 
}
