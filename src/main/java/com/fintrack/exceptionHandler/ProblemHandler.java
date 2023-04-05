package com.fintrack.exceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ProblemHandler {

	private Integer status;
	private OffsetDateTime dateProblem;
	private String title;
	private List<FieldProblem> fields;

	public ProblemHandler(Integer status, String title) {
		this.status = status;
		this.dateProblem = OffsetDateTime.now();
		this.title = title;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public OffsetDateTime getDateProblem() {
		return dateProblem;
	}

	public void setDateProblem(OffsetDateTime timestamp) {
		this.dateProblem = timestamp;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<FieldProblem> getFields() {
		return fields;
	}

	public void setFields(List<FieldProblem> fields) {
		this.fields = fields;
	}

}
