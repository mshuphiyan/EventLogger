package com.eventlogger.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Service implements Runnable {
	private static Logger log=LoggerFactory.getLogger(Service.class);
	private String file;

	public Service(String file) {
		this.file = file;
	}

	@Override
	public void run() {
		EventLoggerService loggerService = new EventLoggerService();
		log.info("INFO: Reading file.");
		loggerService.readFile(file);
	}
}
