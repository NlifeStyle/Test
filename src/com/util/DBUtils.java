package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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

	public Connection getConnection() {
		if (isGetConnection()) {
			try {
				conn = DriverManager.getConnection(host, username, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;
	}

	private boolean isGetConnection() {
		if (conn == null) {
			return true;
		}
		try {
			return conn.isClosed();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public int executeUpdate(String o) {
		conn = getConnection();
		Statement stat = null;
		int result = -1;
		try {
			stat = conn.createStatement();
			result = stat.executeUpdate(o);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				stat.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public ResultSet executeQuery(String sql) {
		conn = getConnection();
		Statement stat = null;
		ResultSet result = null;
		try {
			stat = conn.createStatement();
			result = stat.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public List<List<String>> getListResult(String sql) {
		List<List<String>> result = new ArrayList<List<String>>();
		ResultSet rs = executeQuery(sql);
		if (rs != null) {
			try {
				while (rs.next()) {
					List<String> rowList = new ArrayList<String>();
					ResultSetMetaData rsmd = rs.getMetaData();
					for (int i = 1; i <= rsmd.getColumnCount(); i++) {
						rowList.add(rs.getString(i));
					}
					result.add(rowList);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public String runSingleResultDbQuery(String query) {
		String result = "";
		ResultSet rs = executeQuery(query);
		if (rs != null) {
			try {
				while (rs.next()) {
					result = rs.getString(1);
					break;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
