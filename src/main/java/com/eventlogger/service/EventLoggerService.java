package com.eventlogger.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eventlogger.dto.EventDTO;
import com.eventlogger.model.EventLoggerModel;
import com.evnetlogger.dao.EventLoggerDao;
import com.evnetlogger.dao.EventLoggerDaoImpl;
import com.google.gson.Gson;

public class EventLoggerService {
	private static Logger logger = LoggerFactory.getLogger(EventLoggerService.class);

	public synchronized void readFile(String file) {
		Gson gson = new Gson();
		EventLoggerModel loggerModel;
		EventLoggerDao eventDao = new EventLoggerDaoImpl();

		Map<String, Map<String, String>> m = new HashMap();
		logger.info("INFO: Loading file : " + file);
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			for (String line; (line = br.readLine()) != null;) {
				loggerModel = gson.fromJson(line, EventLoggerModel.class);
				Map<String, String> inner = new LinkedHashMap();
				inner.put("state", loggerModel.getState());
				inner.put("type", loggerModel.getType() == "" ? "" : loggerModel.getType());
				inner.put("host", loggerModel.getHost() == "" ? "" : loggerModel.getHost());
				inner.put("timestamp", loggerModel.getTimestamp());

				if (m.containsKey(loggerModel.getId())) {

					if (m.get(loggerModel.getId()).get("state").equals("STARTED")) {
						String t = m.get(loggerModel.getId()).get("timestamp");
						Long diff = Long.parseLong(t) - Long.parseLong(inner.get("timestamp"));
						if (diff > 4) {
							System.out.println(
									"elapsed time more than 4 ms " + loggerModel.getId() + ", saving into db.");
							logger.info("INFO: Event found having time elapsed more than 4 ms : " + loggerModel.getId());
							
							EventDTO eventDto = new EventDTO();
							eventDto.setId(loggerModel.getId());
							eventDto.setEvent_duration(diff);
							eventDto.setHost(loggerModel.getHost() == null ? "" : loggerModel.getHost());
							eventDto.setType(loggerModel.getType() == null ? "" : loggerModel.getType());
							eventDto.setAlert(diff > 4 ? "true" : "false");
							eventDao.insertEvent(eventDto);
							logger.info("INFO: Saving to database "+eventDto);
						}
					} else {
						m.put(loggerModel.getId(), inner);
					}
				} else {
					m.put(loggerModel.getId(), inner);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
