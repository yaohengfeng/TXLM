
package com.yhf.txl.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class DbConfig {

	private static Properties properties;
	
	private static InputStream inputStream = DbConfig.class.getResourceAsStream("/databaseConfig.properties");

	private DbConfig() {
	}

	static {
		try {
			properties = new Properties();
			properties.load(inputStream);
			properties.getProperty("Url");
			properties.getProperty("UserName");
			properties.getProperty("UserPassword");
			properties.getProperty("DriverName");

		} catch (FileNotFoundException e) {
			System.err.println("error : not found the specified configuration file: databaseConfig.properties");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
	public static Properties getProperties() {
		return properties;
	}

}
