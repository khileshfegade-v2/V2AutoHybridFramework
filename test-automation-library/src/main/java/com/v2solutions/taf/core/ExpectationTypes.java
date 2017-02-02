package com.v2solutions.taf.core;

import org.apache.commons.logging.Log;

import com.v2solutions.taf.util.LogUtil;

/**
 * List the valid Expected condition for wait driver supported by the test framework
 *
 * @author V2Solutions
 * @version 1.0
 *
 */
public enum ExpectationTypes {

	/* Use for the single element search */
	PresenseOfElementLocated("presenceOfElementLocated"),
	ElementToBeClickable("elementToBeClickable"),
	VisibilityOfElementLocated("visibilityOfElementLocated"),

	/* Use for the below for multi elements search */
	PresenceOfAllElementsLocatedBy("presenceOfAllElementsLocatedBy"),
	VisibilityOfAllElements("visibilityOfAllElementsLocatedBy")
	;

	static Log log = LogUtil.getLog(ExpectationTypes.class);

	private String expectation;

	private ExpectationTypes(String expectations) {
		this.expectation = expectations;
	}

	public String getExpectation() {
		return expectation;
	}

}
