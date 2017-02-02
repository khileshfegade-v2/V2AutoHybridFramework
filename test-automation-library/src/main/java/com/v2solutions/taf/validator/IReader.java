package com.v2solutions.taf.validator;

import com.v2solutions.taf.exception.ResourceLoadException;

/**
 * Reader contract for loading resource.
 * 
 * @author V2Solutions
 * @version 1.0
 *
 */
public interface IReader {

	void loadFromResource(String resource) throws ResourceLoadException;	

	void loadFromURL(String url) throws ResourceLoadException;
	
	void loadFromPath(String path) throws ResourceLoadException;
	
	void loadFromString(String json) throws ResourceLoadException;
	
	Object getNode();

}
