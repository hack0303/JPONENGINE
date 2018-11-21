package com.creating.www;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.creating.www.core.AppContext;

public class APP {
	public static ApplicationContext atx=new ClassPathXmlApplicationContext("applicationContext.xml");
	public static AppContext appContext=atx!=null?(AppContext)atx.getBean("app-context"):null;
	static Logger logger=LogManager.getLogger(APP.class.getName());
	public static void main(String[] args) {
		logger.debug("hello world!");
	}

}
