package com.neha.empmgmt.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionFactory {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/empmgmt";
	private static Connection connection;
	// Database credentials
	static final String USER = "root";
	static final String PASS = "password";

	public static Connection getConnection() {
		if (connection == null) {
			// STEP 2: Register JDBC driver
			try {
				Class.forName(JDBC_DRIVER);

				// STEP 3: Open a connection
				System.out.println("Connecting to database...");
				connection = DriverManager.getConnection(DB_URL, USER, PASS);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return connection;
	}
}
