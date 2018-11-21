package com.creating.www.test;

import static org.junit.Assert.assertNotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContextTest {
     static ApplicationContext ac=null;
     static Logger logger=null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		System.setProperty("log4j.configurationFile","config/log4j2.xml");
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
		logger=LogManager.getLogger(AppContextTest.class.getName());
		logger.debug("aaaa");
		assertNotNull(ac);
	}

}
