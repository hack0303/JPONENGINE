package com.creating.www.beans;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.creating.www.APP;
import com.creating.www.beans.elecs.Location;

public class AlarmModel implements Delayed{
static Logger logger=LogManager.getLogger(AlarmModel.class.getName());
private static  long DELAY_TIME=Long.valueOf((String) APP.appContext.getProperty(com.creating.www.config.Environment.APP_DELAY_TIME,"20000"));
private Integer id;
private AlmBean almBean;
private AlarmCode alarmCode;
private Location location;
private Date firstCreateTime;
private Date receiveTime;
private AlarmModel source;
private List<AlarmModel> descend;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public AlmBean getAlmBean() {
	return almBean;
}
public void setAlmBean(AlmBean almBean) {
	this.almBean = almBean;
}
public AlarmCode getAlarmCode() {
	return alarmCode;
}
public void setAlarmCode(AlarmCode alarmCode) {
	this.alarmCode = alarmCode;
}
public Location getLocation() {
	return location;
}
public void setLocation(Location location) {
	this.location = location;
}
public Date getFirstCreateTime() {
	return firstCreateTime;
}
public void setFirstCreateTime(Date firstCreateTime) {
	this.firstCreateTime = firstCreateTime;
}
@Override
public int compareTo(Delayed o) {
	if(!(o instanceof AlarmModel)) 
	{
		logger.error("解释:{} 出错对象:{}","对象不是告警模型",o);
		return 0;
	}
	AlarmModel am=(AlarmModel)o;
	return this.receiveTime.compareTo(am.receiveTime);
}
@Override
public long getDelay(TimeUnit unit) {
	return unit.convert(receiveTime.getTime()+DELAY_TIME-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
}
}
