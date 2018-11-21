package com.creating.www.core;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.creating.www.beans.AlarmCode;
import com.creating.www.beans.AlarmModel;
import com.creating.www.beans.elecs.AlarmLocation;

public class Cache {
public static Map<AlarmCode,Map<AlarmLocation,List<AlarmModel>>> _ALARM_STORE=new ConcurrentHashMap<>();
}
