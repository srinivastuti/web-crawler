package org.dbs.crawl.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorResponse {

	private HttpStatus status;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timeStamp;

	private String message;

	private List<String> detiails;

	
	private ErrorResponse() {
		timeStamp=LocalDateTime.now();
	}

	public ErrorResponse(String message, List<String> details) {
		this();
		this.message = message;
		this.detiails = details;
	}

	public ErrorResponse(String message, HttpStatus status, List<String> details) {
		this();
		this.message = message;
		this.status = status;
		this.detiails = details;
	}



	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetiails() {
		return detiails;
	}

	public void setDetiails(List<String> detiails) {
		this.detiails = detiails;
	}

}
