package com.v2solutions.taf.exception;

import java.io.PrintWriter;

/**
 * Custom exception thrown for exception related to list
 * 
 * @author V2Solutions
 * @version 1.0
 * 
 */
public class ListException extends Exception {


	private static final long serialVersionUID = 202L;

	private String message;

	public ListException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public void printStackTrace(PrintWriter s) {
		super.printStackTrace(s);
	}

}
