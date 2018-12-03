/**
 * 
 */
package com.creating.www.beans.elecs.support;

import com.creating.www.beans.IFactory;
import com.creating.www.beans.elecs.ElecUnit;
import com.creating.www.beans.elecs.AlarmLocation;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年11月30日
 *
 */
public class LocationInfoFactory implements IFactory<ElecUnit[],AlarmLocation> {
	/* (non-Javadoc)
	 * @see com.creating.www.beans.IFactory#newTarget(java.lang.Object)
	 */
	@Override
	public AlarmLocation newTarget(ElecUnit[] in) {
		// TODO Auto-generated method stub
		return new AlarmLocation(in);
	}

	/* (non-Javadoc)
	 * @see com.creating.www.beans.IFactory#clear()
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
