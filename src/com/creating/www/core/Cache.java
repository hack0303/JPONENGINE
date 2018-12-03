package com.creating.www.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.creating.www.AlarmModel;
import com.creating.www.RoleType;
import com.creating.www.beans.codes.AlarmCode;
import com.creating.www.beans.codes.AlarmCodePair;
import com.creating.www.beans.elecs.ElecUnit;
import com.creating.www.beans.elecs.AlarmLocation;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年11月29日 下午6:04:12
 */
public class Cache {
	/**
	 * 实时告警缓存
	 */
	public static Map<AlarmLocation,List<AlarmModel>> _ALARM_STORE=new HashMap<AlarmLocation,List<AlarmModel>>();
	/**
	 * 电路结构
	 * */
	public static Map<ElecUnit,Set<ElecUnit>> _ELEC_STRUCTURE=new HashMap<ElecUnit,Set<ElecUnit>>();
	/**
	 * 告警CODE规则
	 * 
	 */
	public static Map<AlarmCode, Map<RoleType,Set<AlarmCode>>> _CODE_RULE = new HashMap<AlarmCode, Map<RoleType,Set<AlarmCode>>>();
    /**
     * 告警规则对缓存
     * */
	public static Map<String,AlarmCodePair> _CODE_RULE_PAIR=new HashMap<String,AlarmCodePair>();
}
