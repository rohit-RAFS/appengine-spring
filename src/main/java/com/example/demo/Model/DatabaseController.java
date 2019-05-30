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
	
	//private static final Logger LOGGER = Logger.getLogger(IndexServlet.class.getName());
	
	private static String DB_NAME = "otpverify", DB_USER = "test", DB_PASS = "anish";
	
	private static DataSource pool =null;
	
	private static void createConnectionPool() {
		
			HikariConfig config = new HikariConfig();
		//jdbc:mysql://google/otpverify?cloudSqlInstance=micro-s-perpule:us-central1:otp-validation&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&user=test&password=anish
		config.setDriverClassName("com.mysql.jdbc.Driver");
		
		config.setJdbcUrl(String.format("jdbc:mysql://35.225.50.95:3306/%s", DB_NAME));
		//config.setJdbcUrl(String.format("jdbc:mysql://google/otpverify?cloudSqlInstance=micro-s-perpule:us-central1:otp-validation&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&user=test&password=anish"));
		
		// TODO Change this when deploying
		config.setUsername(DB_USER); // e.g. "root", "postgres"
		config.setPassword(DB_PASS); // e.g. "my-password"
			
			config.addDataSourceProperty("socketFactory", "com.google.cloud.sql.mysql.SocketFactory");
			config.addDataSourceProperty("cloudSqlInstance", "micro-s-perpule:us-central1:otp-validation");
			config.addDataSourceProperty("useSSL", "false");
			
			
			config.setMaximumPoolSize(5);
			config.setMinimumIdle(5);
			config.setConnectionTimeout(10000);
			config.setIdleTimeout(600000);
			config.setMaxLifetime(1800000);
			
			pool = new HikariDataSource(config);
	}
	
	
	private DatabaseController(){}
	
	
	public static Connection getConnection() throws SQLException {
		if(pool == null){
			createConnectionPool();
		}
		return pool.getConnection();
	}
	
}
