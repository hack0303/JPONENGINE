/**
 * 
 */
package com.creating.www.beans.elecs;

/**
 *@author Chack Yao
 *@firstcreatetime 2018年11月29日 下午5:35:42
 */
public enum ElecType {
    NET("网元",1),BOARD("板卡",2),PONINTERFACE("PON口",3),ONU("光网络单元",4),OTHER("其他",5);
	private String name;
	private Integer index;
	private ElecType(String name, Integer index) {
		this.name = name;
		this.index = index;
	}
	public Integer toIndex() 
	{
		return index;
	}
	public String toName() 
	{
		return name;
	}
	
}
