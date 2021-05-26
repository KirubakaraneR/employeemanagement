package com.ideas2it.project.exceptions;

public class ProjectException extends Exception{

	public ProjectException(String message) {
		super(message);
	}
	
	public ProjectException(String message, Throwable error) {
		super(message, error);
	}
}
