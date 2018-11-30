/**
 * 
 */
package com.creating.www.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.creating.www.RoleType;
import com.creating.www.beans.codes.AlarmCode;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年11月30日
 *
 */
public class StoreUtil {

	public static void store(Map<AlarmCode,Map<RoleType,Set<AlarmCode>>> _CODE_RULE,AlarmCode a,AlarmCode b,RoleType type) 
	{
		Map<RoleType,Set<AlarmCode>> _find=_CODE_RULE.get(a);
		if(_find==null) 
		{
			_find=new HashMap<RoleType,Set<AlarmCode>>();
		    Set<AlarmCode> _dscs=new HashSet<AlarmCode>();
		    _dscs.add(b);
			_find.put(type,_dscs);
			_CODE_RULE.put(a,_find);
		}else 
		{
			Set<AlarmCode> _dscs=_find.get(type);
			if(_dscs==null) 
			{
				_dscs=new HashSet<AlarmCode>();
				_find.put(type,_dscs);
				_dscs.add(b);
			}else 
			{
			if(!_dscs.contains(b)) 
			{
				_dscs.add(b);
			}	
			}
			
		}
	}
	
}
