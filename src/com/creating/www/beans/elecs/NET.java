package com.creating.www.beans.elecs;


/**
 * @author Chack Yao
 * @firstcreatetime 2018年11月29日 下午5:34:04
 * 
 * 网元
 * */
public class NET extends ElecUnit{
	/**
	 * @param devInfo
	 * @param layerLocationNumber
	 * @param type
	 */
	public NET(String devInfo, Integer layerLocationNumber, ElecType type) {
		super(devInfo, layerLocationNumber, type);
		// TODO Auto-generated constructor stub
	}

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
		return null;
	}

	/* (non-Javadoc)
	 * @see com.creating.www.beans.elecs.LayerDescriptionInfo#childElecTypes()
	 */
	@Override
	public ElecType[] childElecTypes() {
		// TODO Auto-generated method stub
		return new ElecType[]{ElecType.BOARD,ElecType.PONINTERFACE,ElecType.ONU,ElecType.OTHER};
	}

}
