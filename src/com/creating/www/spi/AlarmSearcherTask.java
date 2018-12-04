package com.creating.www.spi;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import com.creating.www.APP;
import com.creating.www.AlarmModel;
import com.creating.www.core.Cache;
import com.creating.www.utils.CacheRelaUtil;

public class AlarmSearcherTask implements Runnable{

	private AlarmModel alarmModel;
	static Logger logger=LogManager.getLogger(AlarmSearcherTask.class.getName()+"xxxxxxxxx");
	
	public AlarmSearcherTask(AlarmModel alarmModel) {
		super();
		this.alarmModel = alarmModel;
	}
    static Marker FIND_SOURCE_PHASE=MarkerManager.getMarker("寻找根阶段");
    static Marker STORE_ALARM_TO_CACHE_PHASE=MarkerManager.getMarker("告警放缓存阶段");
    static Marker STORE_ALARM_TO_QUEUE_PHASE=MarkerManager.getMarker("告警放队列阶段");
    static Marker FIND_DESCEND_PHASE=MarkerManager.getMarker("寻找衍生阶段");
	@Override
	public void run() {
		try {
		logger.debug(FIND_SOURCE_PHASE,"告警 ID=[{}]",alarmModel.getId());
	    AlarmModel source=CacheRelaUtil.findSource(Cache._ALARM_STORE,Cache._ELEC_STRUCTURE,Cache._CODE_RULE,this.alarmModel);
		if(source==null) { 
			logger.debug(FIND_SOURCE_PHASE,"告警 ID=[{}] 开始未找到根",alarmModel.getId());
			alarmModel.getLocation().storeElecUnit(Cache._ELEC_STRUCTURE);
			logger.debug(STORE_ALARM_TO_CACHE_PHASE,"告警 ID=[{}] 放入缓存",alarmModel.getId());
			alarmModel.storeAlarmToCache(Cache._ALARM_STORE);
			logger.debug(STORE_ALARM_TO_QUEUE_PHASE,"告警 ID=[{}] 放入队列",alarmModel.getId());
			if(APP.appContext._TIME_QUEUE().add(alarmModel)) 
			{
				logger.debug(STORE_ALARM_TO_QUEUE_PHASE,"告警 ID=[{}] 放入队列 {}",alarmModel.getId(),"成功");
			}else 
			{
				logger.debug(STORE_ALARM_TO_QUEUE_PHASE,"告警 ID=[{}] 放入队列 {}",alarmModel.getId(),"失败");
			}
		}else 
		{
			logger.debug(FIND_SOURCE_PHASE,"告警 ID=[{}] 开始找到根 ID=[{}]",alarmModel.getId(),alarmModel.getId());
			return;
		}
		logger.debug(FIND_DESCEND_PHASE,"告警 ID=[{}] {}",alarmModel.getId(),"开始");
		Set<AlarmModel> descends=CacheRelaUtil.findDescend(Cache._ALARM_STORE,Cache._ELEC_STRUCTURE,Cache._CODE_RULE,this.alarmModel);
		if(descends==null||descends.size()==0) 
		{
			logger.debug(FIND_DESCEND_PHASE,"告警 ID=[{}] {}",alarmModel.getId(),"没有找到");
		}else 
		{
			logger.debug(FIND_DESCEND_PHASE,"告警 ID=[{}] {},展示:{}",alarmModel.getId(),"找到了",descends);	
		}
		}catch(Exception e) 
		{
		logger.error("任务单元出错",e);	
		}
		}
	
}
