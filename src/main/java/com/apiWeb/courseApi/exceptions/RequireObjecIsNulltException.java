package com.apiWeb.courseApi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequireObjecIsNulltException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RequireObjecIsNulltException() {
		super("It is not allowed to persst a null object!");
	}
	public RequireObjecIsNulltException(String ex) {
		super(ex);
	}

	

}
