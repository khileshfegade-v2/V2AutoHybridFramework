package com.v2solutions.taf.dataprovider.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to be used with each test method to denote excel file, excel sheet
 * and data key of test data needed for that method
 * 
 * @author V2Solutions
 * @version 1.0
 * 
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ITafExcelDataProviderInputs {
	String excelFile();
	String excelsheet();
	String dataKey();
}
