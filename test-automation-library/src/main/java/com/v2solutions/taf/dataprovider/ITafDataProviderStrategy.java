package com.v2solutions.taf.dataprovider;

import java.util.Map;

/**
 * @author V2Solutions
 * @version 1.0
 * 
 */

public interface ITafDataProviderStrategy<T> {
	Map<String, T[][]> readTestData();
	Map<String, T[][]> readTestData(String sheet,String id);
}
