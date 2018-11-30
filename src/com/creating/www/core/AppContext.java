package com.creating.www.core;

import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.creating.www.AlarmModel;

public class AppContext {
	/**
	 * APP环境参数
	 * */
private  Properties ENVIRONMENT;
private  Executor executor=Executors.newFixedThreadPool(10); 
private static MODE sys_mode=null;
public Executor getThreadPool() 
{
return executor;	
}
private Queue<AlarmModel> timeDelayedQueue=new DelayQueue<AlarmModel>();
public Object getProperty(String key) 
{
	if(ENVIRONMENT!=null) return ENVIRONMENT.getProperty(key);
	return null;
}
public Object getProperty(String key,String defaultValue) 
{
    Object outter=getProperty(key);
    if(outter==null) return defaultValue;
    else return outter;
}
public void setENVIRONMENT(Properties ENVIRONMENT)
{
this.ENVIRONMENT=ENVIRONMENT;
}
}
