package com.evnetlogger.dao;

import com.eventlogger.dto.EventDTO;

public interface EventLoggerDao {
	public void insertEvent(EventDTO eventDto);
}
