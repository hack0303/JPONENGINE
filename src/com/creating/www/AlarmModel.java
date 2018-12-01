package com.creating.www;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.creating.www.beans.codes.AlarmCode;
import com.creating.www.beans.elecs.LocationInfo;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年11月29日 下午2:08:45
 * */
public class AlarmModel implements Delayed{
static Logger logger=LogManager.getLogger(AlarmModel.class.getName());
private static  long DELAY_TIME=Long.valueOf((String) APP.appContext.getProperty(com.creating.www.config.Environment.APP_DELAY_TIME,"20000"));
private Integer id;
private AlmBean almBean;
@Override
public String toString() {
	return "AlarmModel [id=" + id + ", almBean=" + almBean + ", alarmCode=" + alarmCode + ", location=" + location
			+ ", firstCreateTime=" + firstCreateTime + ", receiveTime=" + receiveTime + ", source=" + source
			+ ", descend=" + descend + "]";
}
private AlarmCode alarmCode;
private LocationInfo location;
private Date firstCreateTime;
private Date receiveTime;
private AlarmModel source;
private List<AlarmModel> descend;

public AlarmModel(Integer id, AlmBean almBean, AlarmCode alarmCode, LocationInfo location, Date firstCreateTime,
		Date receiveTime) {
	super();
	this.id = id;
	this.almBean = almBean;
	this.alarmCode = alarmCode;
	this.location = location;
	this.firstCreateTime = firstCreateTime;
	this.receiveTime = receiveTime;
}
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
public LocationInfo getLocation() {
	return location;
}
public void setLocation(LocationInfo location) {
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
