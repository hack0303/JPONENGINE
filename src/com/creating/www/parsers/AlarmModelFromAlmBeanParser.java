/**
 * 
 */
package com.creating.www.parsers;

import java.util.Date;
import com.creating.www.AlarmModel;
import com.creating.www.AlmBean;
import com.creating.www.beans.codes.AlarmCode;
import com.creating.www.beans.elecs.LocationInfo;
import com.creating.www.impl.PONAlarm;
import com.creating.www.utils.LocationUtil;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年12月1日
 *
 */
public class AlarmModelFromAlmBeanParser implements Parser<AlmBean,AlarmModel> {

	/* (non-Javadoc)
	 * @see com.creating.www.parsers.Parser#parse(java.lang.Object)
	 */
	@Override
	public AlarmModel parse(AlmBean almBean) {
		PONAlarm p_alm=(PONAlarm)almBean;
		Integer id=p_alm.getId();
		Date firstCreateTime=p_alm.getFirstCreateTime();
		LocationInfo location=new LocationInfo(LocationUtil.parse(p_alm.getLocation()));
		AlarmCode alarmCode=new AlarmCode(p_alm.getCode(), location);
		Date receiveTime=new Date();
		AlarmModel am=new AlarmModel(id, almBean, alarmCode, location, firstCreateTime, receiveTime);
		return am;
	}

	/* (non-Javadoc)
	 * @see com.creating.www.parsers.Parser#parse()
	 */
	@Override
	public AlarmModel parse() {
		// TODO Auto-generated method stub
		return null;
	}

}
