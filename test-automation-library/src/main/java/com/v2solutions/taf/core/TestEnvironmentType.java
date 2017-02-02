package com.v2solutions.taf.core;

import org.apache.commons.logging.Log;

import com.v2solutions.taf.util.LogUtil;

/**
 * TestEnvironmentType stores information on test execution environment.
 * 
 * @author V2Solutions
 * @version 1.0
 * 
 */
public enum TestEnvironmentType {
	LOCAL("local"),
	REMOTE("remote"),
	CLOUD("cloud");

	static Log log = LogUtil.getLog(TestEnvironmentType.class);

	private String testEnv;

	private TestEnvironmentType(String testEnv) {
		this.testEnv = testEnv;
	}

	/**
	 * Returns test environment
	 * @return
	 */
	public String getTestEnv() {
		return testEnv;
	}
}
