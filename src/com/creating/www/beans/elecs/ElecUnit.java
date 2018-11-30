package com.creating.www.beans.elecs;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年11月29日 下午5:34:04
 * */
public abstract class ElecUnit implements LayerDescriptionInfo<String> {
    public String devInfo;//设备信息
    public Integer layerLocationNumber;//层级定位编号
    public ElecType type;//电路组成单元类型
    public ElecUnit(String devInfo,Integer layerLocationNumber,ElecType type) 
    {
    	this.devInfo=devInfo;
    	this.layerLocationNumber=layerLocationNumber;
    	this.type=type;
    }
	public abstract String getDescription();
	@Override
	public String toString() {
		return "ElecUnit [devInfo=" + devInfo + ", layerLocationNumber=" + layerLocationNumber + ", type=" + type.toName() + "]";
	}
	
}
