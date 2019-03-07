package com.eventlogger.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;

import org.junit.Test;

public class EventLoggerMainTest {
	private String TEST_FILENAME="event.json";
	private URL url = this.getClass().getResource("/" + TEST_FILENAME);

	
	@Test
	public void fileExists(){
		File file = new File(url.getFile());
		assertTrue("file exists", file.isAbsolute());
		System.out.println(file.getAbsolutePath());
	}
	
	
}
