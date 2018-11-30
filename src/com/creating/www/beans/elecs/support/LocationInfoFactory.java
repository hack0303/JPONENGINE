/**
 * 
 */
package com.creating.www.beans.elecs.support;

import com.creating.www.beans.IFactory;
import com.creating.www.beans.elecs.ElecUnit;
import com.creating.www.beans.elecs.LocationInfo;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年11月30日
 *
 */
public class LocationInfoFactory implements IFactory<ElecUnit[],LocationInfo> {
	/* (non-Javadoc)
	 * @see com.creating.www.beans.IFactory#newTarget(java.lang.Object)
	 */
	@Override
	public LocationInfo newTarget(ElecUnit[] in) {
		// TODO Auto-generated method stub
		return new LocationInfo(in);
	}

	/* (non-Javadoc)
	 * @see com.creating.www.beans.IFactory#clear()
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
