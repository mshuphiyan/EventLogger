package com.eventlogger.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.eventlogger.server.Server;
import com.eventlogger.service.Service;

public class EventLoggerMain {
	private static Logger log = LoggerFactory.getLogger(EventLoggerMain.class);

	public static void main(String[] args) {
		if (args.length < 1) {
			log.info("ERROR: Missing file name.");
			System.out.println("Command line args needed.");
			log.error("Exiting program!!!");
			System.exit(1);
		}
		String file = args[0];

		Server server = new Server();
		for (int i = 0; i < 2; i++) {
			Service service = new Service(file);
			server.executeTask(service);
		}

		server.endServer();
	}

}
