package com.evnetlogger.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.db.util.DBUtil;
import com.eventlogger.dto.EventDTO;

public class EventLoggerDaoImpl implements EventLoggerDao {
	private static Logger log=LoggerFactory.getLogger(EventLoggerDaoImpl.class);
	private Connection conn = null;
	private Statement st = null;
	int result = 0;

	public void insertEvent(EventDTO eventDto) {
		conn = DBUtil.getConnection();
		try {
			st = conn.createStatement();

			String sql = "CREATE TABLE IF NOT EXISTS alert (event_id VARCHAR(50), event_duration VARCHAR(50), type VARCHAR(20), host VARCHAR(20), alert VARCHAR(5));";
			st.executeUpdate(sql);
			System.out.println("Table created successfully");

			String insertSql = "INSERT INTO alert (event_id, event_duration, type, host, alert) values ('"
					+ eventDto.getId() + "', '" + eventDto.getEvent_duration() + "', '" + eventDto.getHost() + "', '"
					+ eventDto.getType() + "', '" + eventDto.getAlert() + "')";
			result = st.executeUpdate(insertSql);
			conn.commit();
			System.out.println(result + " rows inserted.");
			log.info("INFO: "+result+" rows inserted successfully.");
			
//			ResultSet result = st.executeQuery("SELECT * FROM alert");
//			while (result.next()) {
//				System.out.println(result.getString("event_id") + " | " + result.getString("event_duration") + " | "
//						+ result.getString("type"));
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
