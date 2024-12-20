package com.example.vrs.exceptions;

public class BaseException extends RuntimeException {

	private final String message;

	public BaseException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
