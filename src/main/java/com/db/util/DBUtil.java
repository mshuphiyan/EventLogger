package com.db.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static Connection connection=null;
	public static Connection getConnection(){
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			connection = DriverManager.getConnection(
					"jdbc:hsqldb:file:/Volumes/Data/EclipseWorkspace/event-logger/src/db/mydb;shutdown=true;ifexists=true", "SA", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
