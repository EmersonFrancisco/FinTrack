package com.fintrack.exceptionHandler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class FieldProblem {

	private String name;
	private String message;

	public FieldProblem(String name, String message) {
		this.name = name;
		this.message = message;
	}

	public static List<FieldProblem> buildListFields(MethodArgumentNotValidException ex) {
		List<FieldProblem> fields = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String name = ((FieldError) error).getField();
			//usando o messageSource, ele pega a mensagem traduzida no arquivo messages.properties, caso queira traduzir outras, pegar as tags no Log Resolved do Console!!
			String message = error.getDefaultMessage();
			fields.add(new FieldProblem(name, message));
		}
		return fields;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
