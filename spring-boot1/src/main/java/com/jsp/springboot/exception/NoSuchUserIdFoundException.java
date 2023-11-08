package com.jsp.springboot.exception;

public class NoSuchUserIdFoundException extends Exception {
	
	public NoSuchUserIdFoundException() {
		super("No user found with the mentioned id");
	}
}
