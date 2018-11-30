package com.creating.www;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.create.www.core.Server;
import com.creating.www.core.AppContext;
import com.creating.www.impl.CallBackImpl;

public class APP {
	public static ApplicationContext atx=new ClassPathXmlApplicationContext("applicationContext.xml");
	public static AppContext appContext=atx!=null?(AppContext)atx.getBean("app-context"):null;
	static Logger logger=LogManager.getLogger(APP.class.getName());
	public static void main(String[] args) {
		try {
			Server.startup(new CallBackImpl());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
