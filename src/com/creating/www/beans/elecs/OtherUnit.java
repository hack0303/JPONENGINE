/**
 * 
 */
package com.creating.www.beans.elecs;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年11月29日
 *
 */
public class OtherUnit extends ElecUnit {

	/**
	 * @param devInfo
	 * @param layerLocationNumber
	 * @param type
	 */
	public OtherUnit(String devInfo, Integer layerLocationNumber, ElecType type) {
		super(devInfo, layerLocationNumber, type);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.creating.www.beans.elecs.ElecUnit#getDescription()
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.creating.www.beans.elecs.LayerDescriptionInfo#fatherElecTypes()
	 */
	@Override
	public ElecType[] fatherElecTypes() {
		// TODO Auto-generated method stub
		return new ElecType[]{ElecType.NET,ElecType.BOARD,ElecType.PONINTERFACE,ElecType.ONU};
	}

	/* (non-Javadoc)
	 * @see com.creating.www.beans.elecs.LayerDescriptionInfo#childElecTypes()
	 */
	@Override
	public ElecType[] childElecTypes() {
		// TODO Auto-generated method stub
		return null;
	}

}
