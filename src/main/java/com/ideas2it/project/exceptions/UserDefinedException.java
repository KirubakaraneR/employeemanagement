package com.ideas2it.project.exceptions;

public class UserDefinedException extends Exception{

	public UserDefinedException(String message) {
		super(message);
	}

	public UserDefinedException(String message, Throwable e) {
		super(message, e);
	}

}
