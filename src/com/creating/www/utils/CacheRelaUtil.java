/**
 * 
 */
package com.creating.www.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.creating.www.AlarmModel;
import com.creating.www.RoleType;
import com.creating.www.beans.codes.AlarmCode;
import com.creating.www.beans.elecs.ElecUnit;
import com.creating.www.beans.elecs.AlarmLocation;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年12月1日
 *
 */
public class CacheRelaUtil {
	static Logger logger=LogManager.getLogger(CacheRelaUtil.class.getName());
	public static AlarmModel findSource(Map<AlarmLocation, List<AlarmModel>> alarmMapping,
			Map<ElecUnit, Set<ElecUnit>> elecStructure, Map<AlarmCode, Map<RoleType, Set<AlarmCode>>> matchingRule,
			AlarmModel alarm) {
		AlarmCode ac = alarm.getAlarmCode();
		AlarmLocation al = alarm.getLocation();
		AlarmModel target = null;
		Set<AlarmLocation> al_collection = allPossibleAlarmLocations(elecStructure, al, true);
	    logger.debug(al_collection);
		for (AlarmLocation a : al_collection) {
			CopyOnWriteArrayList<AlarmModel> possibleAlarms = ((CopyOnWriteArrayList<AlarmModel>) alarmMapping.get(a));
			if (possibleAlarms == null)
				continue;
			@SuppressWarnings("unchecked")
			List<AlarmModel> _possibleAlarms = (List<AlarmModel>) possibleAlarms.clone();
			if (_possibleAlarms.size() == 0)
				continue;
			for (AlarmModel am : _possibleAlarms) {
				AlarmCode sourceAC = am.getAlarmCode();
				if (checkAlarmCodePair(matchingRule, sourceAC, ac)) {
				//	if (alarm.compareFirstCreateTimeTo(am, 20 * 1000)){
					if(alarm.setSource(am))
						return am;
					else continue;
				//}
				}
			}
		}
		return target;
	}

	public static boolean checkAlarmCodePair(Map<AlarmCode, Map<RoleType, Set<AlarmCode>>> codeRule, AlarmCode src,
			AlarmCode dsc) {
		if (codeRule == null)
			return false;
		Map<RoleType, Set<AlarmCode>> roleType2AlarmCodes = codeRule.get(src);
		if (roleType2AlarmCodes == null)
			return false;
		Set<AlarmCode> dscACs = roleType2AlarmCodes.get(RoleType.DESCEND);
		if (dscACs == null)
			return false;
		if (dscACs.contains(dsc))
			return true;
		return false;
	}

	/**
	 * 
	 * @param 1电路结构
	 *            2自身告警定位 3是否上溯
	 * @return 所有可能的定位 null 表示没有
	 */
	public static Set<AlarmLocation> allPossibleAlarmLocations(Map<ElecUnit, Set<ElecUnit>> elecStructure,
			AlarmLocation _self_location, boolean anscend) {
		Set<AlarmLocation> alarmLocations = null;
		ElecUnit rootNode = _self_location.getRootNode();
		if (anscend) {
			if (rootNode != null) {
				alarmLocations = _self_location.upAll();
			}
			if (alarmLocations == null) {
				alarmLocations = new HashSet<>();
			}
			alarmLocations.add(_self_location);
		} else {
			if (rootNode != null) {
				alarmLocations = _self_location.downAll(elecStructure);
			}
			if (alarmLocations == null) {
				alarmLocations = new HashSet<>();
			}
			alarmLocations.add(_self_location);
		}
		return alarmLocations;
	}
    public static boolean removeAlarmInCache(Map<AlarmLocation,List<AlarmModel>> alarmMapping,AlarmModel am) 
    {
    	if(alarmMapping==null||alarmMapping.size()==0) return false;
    	List<AlarmModel> list=alarmMapping.get(am.getLocation());
    	return list.remove(am);
    }
	public static Set<AlarmModel> findDescend(Map<AlarmLocation, List<AlarmModel>> alarmMapping,
			Map<ElecUnit, Set<ElecUnit>> elecStructure, Map<AlarmCode, Map<RoleType, Set<AlarmCode>>> matchingRule,
			AlarmModel alarm) {
		Set<AlarmModel> target=new HashSet<AlarmModel>();
		AlarmCode ac = alarm.getAlarmCode();
		AlarmLocation al = alarm.getLocation();
		Set<AlarmLocation> al_collection = allPossibleAlarmLocations(elecStructure, al, false);
		for (AlarmLocation a : al_collection) {
			CopyOnWriteArrayList<AlarmModel> possibleAlarms = ((CopyOnWriteArrayList<AlarmModel>) alarmMapping.get(a));
			if (possibleAlarms == null)
				continue;
			@SuppressWarnings("unchecked")
			List<AlarmModel> _possibleAlarms = (List<AlarmModel>) possibleAlarms.clone();
			if (_possibleAlarms.size() == 0)
				continue;
			for (AlarmModel am : _possibleAlarms) {
				AlarmCode sourceAC = am.getAlarmCode();
				if (checkAlarmCodePair(matchingRule, sourceAC, ac)) {
					//if (alarm.compareFirstCreateTimeTo(am, 20 * 1000)) {
						removeAlarmInCache(alarmMapping,am);
						if(alarm.addDescend(am))
						  target.add(am);
					//}
				}
			}
		}
		return target;
	}

	public static Set<AlarmCode> getMappingCodes(Map<AlarmCode, Map<RoleType, Set<AlarmCode>>> code_mapping,
			AlarmCode key, RoleType type) {
		Map<RoleType, Set<AlarmCode>> _code_mapping = code_mapping.get(key);
		if (_code_mapping == null)
			return null;
		Set<AlarmCode> codes = _code_mapping.get(type);
		return codes;
	}
}
