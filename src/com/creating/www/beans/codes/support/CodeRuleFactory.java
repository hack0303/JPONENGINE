/**
 * 
 */
package com.creating.www.beans.codes.support;

import com.creating.www.APP;
import com.creating.www.beans.IFactory;
import com.creating.www.beans.codes.AlarmCode;
import com.creating.www.beans.elecs.LocationInfo;
import com.creating.www.beans.elecs.support.LocationInfoFactory;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年11月30日
 *
 */
public class CodeRuleFactory implements IFactory<Object[],AlarmCode>{
/* (non-Javadoc)
	 * @see com.creating.www.beans.IFactory#newTarget(java.lang.Object)
	 */
	@Override
	public AlarmCode newTarget(Object[] in) {
		return new AlarmCode((Integer)in[0],(LocationInfo)in[1]);
	}

	/* (non-Javadoc)
	 * @see com.creating.www.beans.IFactory#clear()
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
