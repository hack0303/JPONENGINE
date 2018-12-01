/**
 * 
 */
package com.creating.www.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.creating.www.AlarmModel;
import com.creating.www.RoleType;
import com.creating.www.beans.codes.AlarmCode;
import com.creating.www.beans.elecs.ElecType;
import com.creating.www.beans.elecs.ElecUnit;
import com.creating.www.beans.elecs.LocationInfo;
import com.creating.www.core.Cache;
import com.creating.www.impl.PONAlarm;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年12月1日
 *
 */
public class CacheRelaUtil {
public static AlarmModel findSource(Map<AlarmCode,Map<ElecType,Map<LocationInfo, List<AlarmModel>>>> mapping,AlarmModel alarm){
	AlarmCode ac=alarm.getAlarmCode();
	AlarmModel target=null;
	Set<AlarmCode> codes=getMappingCodes(Cache._CODE_RULE,ac,RoleType.SOURCE);
	if(codes==null||codes.size()==0) return null;
	Map<ElecType,Map<LocationInfo, List<AlarmModel>>> _mapping=mapping.get(ac);
	if(_mapping==null) return null;
	LocationInfo location=alarm.getLocation();
	ElecType[] ets=location.fatherElecTypes();
	Map<LocationInfo,List<AlarmModel>> __mapping=null;
	for(ElecType et:ets) {
	__mapping=_mapping.get(et);
	List<AlarmModel> list=__mapping.get(ac.getLocation());
	if(list==null) return null;
	@SuppressWarnings("unchecked")
	List<AlarmModel> _list=(List<AlarmModel>) ((CopyOnWriteArrayList<AlarmModel>)list).clone();
	for(AlarmModel e:_list) 
	{
		if(Math.abs(e.getFirstCreateTime().getTime()-alarm.getFirstCreateTime().getTime())<=20*1000) 
		{
			target=e;
		    break;
		}
	}}
	return target;
}
public static List<AlarmModel> findDescend(Map<AlarmCode,Map<ElecUnit,Map<LocationInfo, List<AlarmModel>>>> mapping,AlarmModel alarm){
	
	
	return null;
}
public static Set<AlarmCode> getMappingCodes(Map<AlarmCode, Map<RoleType,Set<AlarmCode>>> code_mapping,AlarmCode key,RoleType type)
{
	Map<RoleType,Set<AlarmCode>> _code_mapping=code_mapping.get(key);
	if(_code_mapping==null) return null;
	Set<AlarmCode> codes=_code_mapping.get(type);
	return codes;
}
}
