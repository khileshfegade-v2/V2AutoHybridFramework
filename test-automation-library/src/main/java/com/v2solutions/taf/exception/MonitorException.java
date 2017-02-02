package com.v2solutions.taf.exception;

import java.io.PrintWriter;

/**
 * Custom exception thrown by {@link SeleniumMonitor}.
 * 
 * @author V2Solutions
 * @version 1.0
 *
 */
public class MonitorException extends Exception{

	private static final long serialVersionUID = 203L;
	
	private String message;
	
	public MonitorException(String message) {
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
