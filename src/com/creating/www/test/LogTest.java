package com.creating.www.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LogTest {
    static Logger logger=null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		logger=LogManager.getLogger(LogTest.class.getClass());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		logger.debug("{} {} {}",'a','b','c');
	}

}
