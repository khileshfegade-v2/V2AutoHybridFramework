package com.v2solutions.taf.exception;

import java.io.PrintWriter;

/**
 * Custom exception thrown if error in generating report.
 * 
 * @author V2Solutions
 * @version 1.0
 * 
 */
public class ReportException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 206L;
	
	private String message;
		
	public ReportException(String message) {
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
