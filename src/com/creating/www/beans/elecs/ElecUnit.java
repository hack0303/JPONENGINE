package com.creating.www.beans.elecs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年11月29日 下午5:34:04
 * */
public abstract class ElecUnit implements Comparable<ElecUnit>,LayerDescriptionInfo<String> {
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
	static Logger logger=LogManager.getLogger(ElecUnit.class.getName());
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(ElecUnit o) {
		if(this.type!=o.type) {
			logger.error("when ElecUnit Compare: {}","电路单元类型不同");
			return 0;
		}
		return this.devInfo.compareTo(o.devInfo);
	}
	
}
