package com.creating.www.spi;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.creating.www.APP;
import com.creating.www.AlarmModel;
import com.creating.www.core.Cache;
import com.creating.www.utils.CacheRelaUtil;

public class AlarmSearcherTask implements Runnable{

	private AlarmModel alarmModel;
	static Logger logger=LogManager.getLogger(AlarmSearcherTask.class.getName());
	
	public AlarmSearcherTask(AlarmModel alarmModel) {
		super();
		this.alarmModel = alarmModel;
	}

	@Override
	public void run() {
		try {
		logger.debug("告警 ID=[{}] 开始找根",alarmModel.getId());
	    AlarmModel source=CacheRelaUtil.findSource(Cache._ALARM_STORE,Cache._ELEC_STRUCTURE,Cache._CODE_RULE,this.alarmModel);
		if(source==null) { 
			logger.debug("告警 ID=[{}] 开始未找到根",alarmModel.getId());
			alarmModel.getLocation().storeElecUnit(Cache._ELEC_STRUCTURE);
			alarmModel.storeAlarmToCache(Cache._ALARM_STORE);
			APP.appContext._TIME_QUEUE().add(alarmModel);
		}else 
		{
			logger.debug("告警 ID=[{}] 开始找到根 ID=[{}]",alarmModel.getId(),alarmModel.getId());
			return;
		}
		logger.debug("告警 ID=[{}] 开始找到衍生",alarmModel.getId());
		Set<AlarmModel> descends=CacheRelaUtil.findDescend(Cache._ALARM_STORE,Cache._ELEC_STRUCTURE,Cache._CODE_RULE,this.alarmModel);
		if(descends==null||descends.size()==0) 
		{
			logger.debug("告警 ID=[{}] 开始未找到衍生",alarmModel.getId());
		}
		}catch(Exception e) 
		{
		logger.error("任务单元出错",e);	
		}
		}
	
}
