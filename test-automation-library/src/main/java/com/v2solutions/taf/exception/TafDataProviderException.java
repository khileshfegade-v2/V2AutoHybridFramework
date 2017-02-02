package com.v2solutions.taf.exception;

import java.io.PrintWriter;

/**
 * Custom exception thrown if error in reading Test Data.
 * 
 * @author V2Solutions
 * @version 1.0
 * 
 */
public class TafDataProviderException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 208L;
	private String message;
	
	public TafDataProviderException(String message) {
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
