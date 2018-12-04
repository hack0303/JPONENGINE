/**
 * 
 */
package com.creating.www.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
public class GroupBuilder extends Thread {
	static Logger logger=LogManager.getLogger();
	static Marker marker=MarkerManager.getMarker("[构组阶段]");
	/**
	 * 
	 */
	public GroupBuilder() {
		super.setName("告警组处理线程");
	}
	private int size=1000;
	private long _sleep_time=1000*5;
/* (non-Javadoc)
 * @see java.lang.Thread#run()
 */
@Override
public void run() {
	List<AlarmModel> list=new ArrayList<>(size);
	while(true) {
	APP.appContext._RESULT_QUEUE().drainTo(list);
	if(list.size()==0)
		try {
			Thread.sleep(_sleep_time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	else {
		for(AlarmModel head:list) {
		if(head.getSource()!=null&&!head.isRetention) 
		{
		logger.error(marker,"拥有父节点,又不是滞留告警 ID:{}",head.getId());	
		}else 
		{	
		try {
			logger.debug(marker,"告警组或独立告警 {}",head.assembly());
		} catch (Exception e) {
			logger.error(marker,"组装告警组时出错,出错信息:{}",e.getMessage(),e);
		}
		}
		}
		list.clear();
	}
	}
}
}
