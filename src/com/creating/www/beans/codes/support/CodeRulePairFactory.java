/**
 * 
 */
package com.creating.www.beans.codes.support;

import com.creating.www.APP;
import com.creating.www.beans.IFactory;
import com.creating.www.beans.codes.AlarmCode;
import com.creating.www.beans.codes.AlarmCodePair;
import com.creating.www.beans.elecs.LocationInfo;
import com.creating.www.beans.elecs.support.LocationInfoFactory;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年11月30日
 *
 */
public class CodeRulePairFactory implements IFactory<Object[],AlarmCodePair>{
 /* (non-Javadoc)
	 * @see com.creating.www.beans.IFactory#newTarget(java.lang.Object)
	 */
	@Override
	public AlarmCodePair newTarget(Object[] in) {
		return new AlarmCodePair((Integer)in[0],(AlarmCode)in[1],(AlarmCode)in[2]);
		
	}
	/* (non-Javadoc)
	 * @see com.creating.www.beans.IFactory#clear()
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
