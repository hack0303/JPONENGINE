package com.creating.www.core;

import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.creating.www.AlarmModel;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年12月3日 下午3:53:57
 */
public class AppContext {
	/**
	 * APP环境参数
	 */
	private Properties ENVIRONMENT;
	private Executor executor = Executors.newFixedThreadPool(10);
	private  MODE sys_mode = null;

	public Executor getThreadPool() {
		return executor;
	}

	public  void setMODE(MODE mode) {
		sys_mode = mode;
	}
	public  void setSys_mode(String mode) {
		if(mode.equals("dev"))
		sys_mode = MODE.DEV;
		else if(mode.equals("production")) 
		{
			sys_mode=MODE.PRODUCTION;
		}
	}
	public  MODE _SYSTEM_MODE() {
		return sys_mode;
	}

	private Queue<AlarmModel> timeDelayedQueue = new DelayQueue<AlarmModel>();

	public Queue<AlarmModel> _TIME_QUEUE() {
		return timeDelayedQueue;
	}

	public Object getProperty(String key) {
		if (ENVIRONMENT != null)
			return ENVIRONMENT.getProperty(key);
		return null;
	}

	public Object getProperty(String key, String defaultValue) {
		Object outter = getProperty(key);
		if (outter == null)
			return defaultValue;
		else
			return outter;
	}

	public void setENVIRONMENT(Properties ENVIRONMENT) {
		this.ENVIRONMENT = ENVIRONMENT;
	}
}
