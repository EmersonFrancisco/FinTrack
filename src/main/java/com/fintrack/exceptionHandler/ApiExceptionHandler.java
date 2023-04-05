package com.fintrack.exceptionHandler;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fintrack.exception.UserValidateException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<FieldProblem> fields = FieldProblem.buildListFields(ex);
		
		ProblemHandler problem = new ProblemHandler(status.value(), "One or more field is invalid, verify and try again");
		problem.setFields(fields);
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	@ExceptionHandler(UserValidateException.class)
	public ResponseEntity<Object> handleClientData(UserValidateException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemHandler problem = new ProblemHandler(status.value(), ex.getMessage());
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
		
	}

}
