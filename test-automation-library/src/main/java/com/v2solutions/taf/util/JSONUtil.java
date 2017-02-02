package com.v2solutions.taf.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * This class contains utility methods for reading Json data.
 * 
 * @author V2Solutions
 * @version 1.0
 *
 */

public class JSONUtil {

	private static Log log = LogUtil.getLog(JSONUtil.class);




	/**
	 * method to read the content from the feed url and move this method to a generic class / util class
	 * @throws IOException
	 * @throws ParseException
	 * @throws MalformedURLException
	 *
	 *
	 */
		public static JSONObject readJSONFeed(String jsonFeedUrl) throws IOException, ParseException {
			String jsonText = "";
			log.info("feedUrl ::"+jsonFeedUrl);
			InputStream is = null;
			try {
				is = new URL(jsonFeedUrl).openStream();

				BufferedReader rd = new BufferedReader(new InputStreamReader(is));
				StringBuilder sb = new StringBuilder();
				int cp;
				while ((cp = rd.read()) != -1) {
					sb.append((char) cp);
				}
				jsonText = sb.toString();
				log.info("jsonText ::\n"+jsonText);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				is.close();
			}


			JSONObject jsonObj = (JSONObject) new JSONParser().parse(jsonText);


			return jsonObj;
		}



}
