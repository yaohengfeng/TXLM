
package com.yhf.txl.utils;

import java.sql.Connection;
import java.sql.DriverManager;


public class DbUtil {

	private static Connection connection;
	private static String URL = DbConfig.getProperties().getProperty("Url");
	private static String USER = DbConfig.getProperties().getProperty("UserName");
	private static String PASSWORD = DbConfig.getProperties().getProperty("UserPassword");
	private static String DRIVER_NAME = DbConfig.getProperties().getProperty("DriverName");

	private DbUtil() {

	}

	static {
		try {
			Class.forName(DRIVER_NAME);
		} catch (Exception e) {
			System.err.println("error : fail to initialize the driver of database !\n");
			throw new ExceptionInInitializerError(e);
		}
	}


	public static Connection getConnection() {
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(URL, USER, PASSWORD);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

}
