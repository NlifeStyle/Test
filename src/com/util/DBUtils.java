package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {

	static {

		Properties properties = new Properties();
		InputStream inputStream = DBUtils.class.getClassLoader()
				.getResourceAsStream("jdbc.properties");
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		driver = properties.getProperty("driver");
		host = properties.getProperty("hostname");
		username = properties.getProperty("username");
		password = properties.getProperty("password");

	}
	private static String driver;
	private static String host;
	private static String username;
	private static String password;
	private static Connection conn;

	public DBUtils() {
		try {
			Class.forName(driver).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		conn = DriverManager.getConnection(host, username, password);
		return conn;
	}
}
