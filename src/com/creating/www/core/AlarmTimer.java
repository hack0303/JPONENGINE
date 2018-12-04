/**
 * 
 */
package com.creating.www.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import com.creating.www.APP;
import com.creating.www.AlarmModel;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年12月4日
 *
 */
public class AlarmTimer extends Thread {
	static Logger logger=LogManager.getLogger(AlarmTimer.class.getName());
	static Marker marker=MarkerManager.getMarker("处理超时告警阶段");
	/**
	 * 
	 */
	public AlarmTimer() {
		super.setName("超时告警处理线程");
	}
/* (non-Javadoc)
 * @see java.lang.Thread#run()
 */
@Override
public void run() {
	while(true) {
		AlarmModel model=null;
	try {
		model=APP.appContext._TIME_QUEUE().take();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if(model!=null) 
	{
		logger.debug(marker,"延迟队列出来,告警: {}",model);
		if(model.see()) 
		{
			logger.debug(marker,"告警组或独立告警 ID:{}",model.getId());
			if(APP.appContext._RESULT_QUEUE().add(model)) 
			{
				logger.debug(marker,"告警组头: {},{} {}",model.getId(),"放入构组队列","成功");
			}else 
			{
				logger.warn(marker,"告警组头: {},{} {}",model.getId(),"放入构组队列","失败");
			}
		}else 
		{
			logger.debug(marker,"衍生告警 ID:{}",model.getId());
			if(model.removeAlarmInCache(Cache._ALARM_STORE)) 
			{
				logger.debug(marker,"删除在缓存中的衍生告警  ID:{},结果:{}",model.getId(),"成功");
			}else 
			{
				logger.warn(marker,"删除在缓存中的衍生告警  ID:{},结果:{}",model.getId(),"失败");	
			}
		}
	}
	}
}
}
