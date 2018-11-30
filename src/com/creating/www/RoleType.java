/**
 * 
 */
package com.creating.www;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年11月30日
 *
 */
public enum RoleType {
	
	UNKOWN("未知",0),SOURCE("根",1),DESCEND("衍生",2);
	private String name;
	private Integer index;
	/**
	 * 
	 */
	private RoleType(String name,Integer index) {
         this.name=name;
         this.index=index;
    }
	public String toName() {
		return name;
	}
	public Integer toIndex() 
	{
		return index;
	}
	

}
