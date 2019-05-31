package com.example.demo.Model;

import com.mysql.cj.Messages;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import com.mysql.jdbc.*;
import javax.sql.DataSource;
import javax.validation.constraints.Null;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;


public class DatabaseController {
	
	private static final Logger LOGGER = Logger.getLogger(DatabaseController.class.getName());
	
	
	
	private static DataSource pool =null;
	static String url = null;
	
	private static Connection createConnectionPool() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
		
		String CLOUD_SQL_CONNECTION_NAME = System.getenv(
				"CLOUD_SQL_INSTANCE_NAME");
		String DB_USER = System.getenv("DB_USER");
		String DB_PASS = System.getenv("DB_PASS");
		String DB_NAME = System.getenv("DB_NAME");
		
		LOGGER.info("Database Name:" + DB_NAME + "Database User:" + DB_USER + "Cloud Instance:" + CLOUD_SQL_CONNECTION_NAME);
		
//		HikariConfig config = new HikariConfig();
//		//jdbc:mysql://google/otpverify?cloudSqlInstance=micro-s-perpule:us-central1:otp-validation&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&user=test&password=anish
//		//config.setDriverClassName("com.mysql.jdbc.Driver");
//
//		config.setJdbcUrl(String.format("jdbc:mysql:///%s", DB_NAME));
//		config.setUsername(DB_USER); // e.g. "root", "postgres"
//		config.setPassword(DB_PASS);
//		//config.setJdbcUrl(String.format("jdbc:mysql://google/otpverify?cloudSqlInstance=micro-s-perpule:us-central1:otp-validation&socketFactory=com.google.cloud.sql.mysql.SocketFactory&useSSL=false&user=test&password=anish"));
//
//		// TODO Change this when deploying
////		config.setUsername(DB_USER); // e.g. "root", "postgres"
////		config.setPassword(DB_PASS); // e.g. "my-password"
//
//		config.addDataSourceProperty("socketFactory", "com.google.cloud.sql.mysql.SocketFactory");
//		config.addDataSourceProperty("cloudSqlInstance", CLOUD_SQL_CONNECTION_NAME);
//		config.addDataSourceProperty("useSSL", "false");
//
//
//			config.setMaximumPoolSize(5);
//			config.setMinimumIdle(5);
//			config.setConnectionTimeout(10000);
//			config.setIdleTimeout(600000);
//			config.setMaxLifetime(1800000);
//
//			pool = new HikariDataSource(config);
		
		// jdbc:google:mysql://perpule-1248:us-central1:perpule-sql/perpule_prod?user=backend&password=b@ck3nd
		url = "jdbc:google:mysql://micro-s-perpule:us-central1:otp-validation/otpverify?user=test&password=anish";
		Class.forName("com.mysql.jdbc.GoogleDriver").newInstance();
		Connection conn = DriverManager.getConnection(url);
		return conn;
		
		//TODO Local Deployment
//		config.setDriverClassName("com.mysql.jdbc.Driver");
//
//		config.setJdbcUrl(String.format("jdbc:mysql://35.225.50.95:3306/%s", DB_NAME));
	}
	
	
	private DatabaseController(){}
	
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if(url == null){
			createConnectionPool();
		}
		return  DriverManager.getConnection(url);
	}
	
}
