package com.creating.www.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.creating.www.AlarmModel;
import com.creating.www.RoleType;
import com.creating.www.beans.codes.AlarmCode;
import com.creating.www.beans.codes.AlarmCodePair;
import com.creating.www.beans.elecs.LocationInfo;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年11月29日 下午6:04:12
 */
public class Cache {
	/**
	 * 实时告警缓存
	 */
	public static Map<AlarmCode, Map<LocationInfo, List<AlarmModel>>> _ALARM_STORE = new ConcurrentHashMap<>();
	/**
	 * 告警CODE规则
	 * 
	 */
	public static Map<AlarmCode, Map<RoleType, AlarmCode>> _CODE_RULE = new HashMap<AlarmCode, Map<RoleType, AlarmCode>>();
    /**
     * 告警规则对缓存
     * */
	public static Map<String,AlarmCodePair> _CODE_RULE_PAIR=new HashMap<String,AlarmCodePair>();
}
