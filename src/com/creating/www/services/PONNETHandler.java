/**
 * 
 */
package com.creating.www.services;

import java.util.List;

import com.creating.www.AlarmModel;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年12月1日
 *
 */
public class PONNETHandler implements Handler<AlarmModel> {

	/* (non-Javadoc)
	 * @see com.creating.www.services.Handler#findSource()
	 */
	@Override
	public AlarmModel findSource(AlarmModel alarm) {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.creating.www.services.Handler#findDescends()
	 */
	@Override
	public List<AlarmModel> findDescends(AlarmModel alarm) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.creating.www.services.Handler#store(java.lang.Object)
	 */
	@Override
	public boolean store(AlarmModel alarm) {
		// TODO Auto-generated method stub
		return false;
	}

}
