package com.v2solutions.taf.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.logging.Log;

import com.v2solutions.taf.core.DBPropertyFile;

public class DBUtil {
	
	public static Connection connection = null;
	public static Statement statement = null;
	private static Log log = LogUtil.getLog(DBUtil.class);
	
	public void OpenDBConnection() throws IOException {
		DBPropertyFile db = new DBPropertyFile();
		log.info("Connecting to the database : "+db.getDBType());
		try {
			
			if(db.getDBType().equalsIgnoreCase("My SQL")||db.getDBType().equalsIgnoreCase("mysql")){
				Class.forName("com.mysql.cj.jdbc.Driver");			
			}else if(db.getDBType().equalsIgnoreCase("Oracle")){
				Class.forName("oracle.jdbc.driver.OracleDriver");
			}else if(db.getDBType().equals("MS SQL Server")||db.getDBType().equalsIgnoreCase("mysqlserver")){
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			}
		} catch (ClassNotFoundException e) {
			log.info("Error in configuring the database connection : "+e.getMessage());
		}
		
		try {
			
			connection = DriverManager.getConnection(db.getConnectionString(), db.getUsername(), db.getPassword());
			
		} catch (SQLException e) {
			log.info("Error in connecting the database : "+e.getMessage());
		}
	}
	
	public ResultSet ExecuteDBQuery(String query) throws SQLException {
		
		ResultSet rs = null;
		log.info(" Executing query : "+query);
		try {
			
			statement = connection.createStatement();
			 rs = statement.executeQuery(query);
		} catch (SQLException e) {
			log.info("Error in executing database queries : "+e.getMessage());
		}
		return rs;
	}
	
	public void closeStatement() throws SQLException{
		statement.close();
	}
	
	public void CloseDBConnection() {
		
		log.info("------- Closing database --------");
		try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.close();
				}
		} catch (Exception e) {
			log.info("Error in closing database connection :"+e.getMessage());
		}
	}
}
