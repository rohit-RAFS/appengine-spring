package com.example.demo.Model;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;


public class DatabaseController {
	

	private static String DB_NAME = "otpverify", DB_USER = "test", DB_PASS = "anish";
	private static final Logger LOGGER = Logger.getLogger(DatabaseController.class.getName());
	private static String url=null;
	
	private static Connection createConnectionPool() throws ClassNotFoundException,SQLException,IllegalAccessException,InstantiationException{

		String CLOUD_SQL_CONNECTION_NAME = System.getenv(
				"CLOUD_SQL_INSTANCE_NAME");
		String DB_USER = System.getenv("DB_USER");
		String DB_PASS = System.getenv("DB_PASS");
		String DB_NAME = System.getenv("DB_NAME");

		LOGGER.info("Database Name:" + DB_NAME + "Database User:" + DB_USER + "Cloud Instance:" + CLOUD_SQL_CONNECTION_NAME);

		url = "jdbc:google:mysql://micro-s-perpule:us-central1:otp-validation/otpverify?user=test&password=anish";
		Class.forName("com.mysql.jdbc.GoogleDriver").newInstance();
		Connection conn = DriverManager.getConnection(url);
		return conn;

	}
	
	
	private DatabaseController(){}
	
	
	public static Connection getConnection() throws SQLException {
		if(url == null){
			try{
				createConnectionPool();
			}
			catch (Exception e){}
		}
		return DriverManager.getConnection(url);
	}
	
}
