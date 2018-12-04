package com.creating.www;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.creating.www.beans.codes.AlarmCode;
import com.creating.www.beans.elecs.AlarmLocation;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年11月29日 下午2:08:45
 * */
public class AlarmModel implements Delayed,FirstCreateTimeCompareable<AlarmModel,Integer>{
static Logger logger=LogManager.getLogger(AlarmModel.class.getName());
private static  long DELAY_TIME=Long.valueOf((String) APP.appContext.getProperty(com.creating.www.config.Environment.APP_DELAY_TIME,"20000"));
private Integer id;
private AlmBean almBean;
@Override
public String toString() {
	return "AlarmModel [id=" + id + ", almBean=" + almBean + ", alarmCode=" + alarmCode + ", location=" + location
			+ ", firstCreateTime=" + firstCreateTime + ", receiveTime=" + receiveTime + ", source=" + source
			+ ", descends=" + descends + "]";
}
private AlarmCode alarmCode;
private AlarmLocation location;
private Date firstCreateTime;
private Date receiveTime;
private AlarmModel source;//根
private List<AlarmModel> descends;//衍生

private volatile boolean dead=false;//是否要离开
public synchronized boolean toDead() 
{
return this.dead=true;
}
public AlarmModel(Integer id, AlmBean almBean, AlarmCode alarmCode, AlarmLocation location, Date firstCreateTime,
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
public AlarmLocation getLocation() {
	return location;
}
public void setLocation(AlarmLocation location) {
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

/* (non-Javadoc)
 * @see com.creating.www.FirstCreateTimeCompareable#compareFirstCreateTimeTo(java.lang.Object, java.lang.Object)
 */
@Override
public boolean compareFirstCreateTimeTo(AlarmModel target, Integer time) {
	
	return Math.abs(this.firstCreateTime.getTime()-target.firstCreateTime.getTime())<=time.intValue();
}
public boolean storeAlarmToCache(Map<AlarmLocation,List<AlarmModel>> alarmMapping) 
{
	if(alarmMapping==null) return false;
	List<AlarmModel> list=alarmMapping.get(this.getLocation());
	if(list==null) return false;
	return list.remove(this);
}
public synchronized boolean setSource(AlarmModel source)
{
	if(this.source==null&&!source.dead) { 
	this.source=source;
	return true;	
	}
	return false;
}
public volatile boolean isRetention=false;
public synchronized boolean addDescend(AlarmModel descend) 
{
	if(this.dead) 
	{ 
	 isRetention=true;
	 APP.appContext._RESULT_QUEUE().add(descend);
	 return false;
	}
	if(descends==null) 
	{
		this.descends=new ArrayList<AlarmModel>();
	}
	return this.descends.add(descend);
}
volatile boolean isHeader=false;
public boolean isHeader() 
{
return this.isHeader;
}
/**
 * 判断是否为组头
 */
public synchronized boolean see() {
	if(this.source==null) 
	{
		this.isHeader=true;
		return true;
	}
	return false;
}
public boolean removeAlarmInCache(Map<AlarmLocation,List<AlarmModel>> alarmMapping) 
{
	if(alarmMapping==null||alarmMapping.size()==0) return false;
	List<AlarmModel> list=alarmMapping.get(this.getLocation());
	return list.remove(this);
}
/**
 * @return
 */
public AlarmModel getSource() {
	// TODO Auto-generated method stub
	return this.source;
}
/**
 * @return
 * @throws Exception 
 */
public List<Integer> assembly() throws Exception {
	List<Integer> result=new ArrayList<Integer>();
	result.add(this.getId());
	return assembly(result);
}
public synchronized List<Integer> assembly(List<Integer> result) throws Exception {
	if(this.dead) throw new Exception("子节点被多引用");
	if(this.descends!=null&&this.descends.size()>0) 
	{
		for(AlarmModel am:this.descends) 
		{
			am.assembly(result);
		}
	}
	this.toDead();
	return result;
}
}
