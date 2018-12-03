package com.creating.www;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.creating.www.beans.support.AlmBeanHelper;
import com.creating.www.core.AppContext;
import com.creating.www.core.MODE;

public class APP {
	public static ApplicationContext atx=new ClassPathXmlApplicationContext("applicationContext.xml");
	public static AppContext appContext=atx!=null?(AppContext)atx.getBean("app-context"):null;
	static Logger logger=LogManager.getLogger(APP.class.getName());
	public static Properties ENVIRONMENT_VARS=(Properties) atx.getBean("ENVIRONMENT_VARS");
	public static void main(String[] args) {
		ICallBack callback=null;
		try {
			callback=new CallBack();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(appContext._SYSTEM_MODE()==MODE.DEV) 
		{
			logger.info("当前系统模式为 {}",MODE.DEV.toString());
			String filePath=ENVIRONMENT_VARS.getProperty("com.gs.nms.test.almbean.filepath","config/rules/x.csv");
			logger.debug("almBean对应数据库文件-> {}",filePath);
			AlmBeanHelper testUnit=(AlmBeanHelper) atx.getBean(AlmBeanHelper.class.getSimpleName());
			if(testUnit==null) 
			{
				logger.error("模拟数据构造出现问题");
				System.exit(1);
			} 
			List<AlmBean> list=testUnit.newAlmBeans(filePath);
			if(list==null||list.size()==0) 
			{
				logger.error("解析后的告警数据为0，退出系统");
				System.exit(1);
			} 
			logger.debug("告警数据数量 {}",list.size());
			try {
				callback.onReport(list);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		/**
		try {
			Server.startup(callback);
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
		*/
	}

}
