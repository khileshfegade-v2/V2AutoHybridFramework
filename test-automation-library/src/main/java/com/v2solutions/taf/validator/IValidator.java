package com.v2solutions.taf.validator;

import com.v2solutions.taf.exception.ValidationException;

/**
 * Contract for validating data.
 * 
 * @author V2Solutions
 * @version 1.0
 *
 */
public interface IValidator {
	
	boolean validate(Object schema,Object data) throws ValidationException;

	void generateReport() throws ValidationException;
	
}
