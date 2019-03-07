package com.eventlogger.server;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eventlogger.service.Service;

public class Server {
	private Logger log = LoggerFactory.getLogger(Server.class);
	private ThreadPoolExecutor executor;

	public Server() {
		log.info("Starting server.");
		executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
		log.info("Server started.");
	}

	public void executeTask(Service service) {
		log.info("Calling Service....");
		executor.execute(service);
		log.info("Service executed successfully.");
	}

	public void endServer() {
		log.info("Initializing server shutdown.");
		executor.shutdown();
		log.info("Server shutdown gracefully.");
	}
}
