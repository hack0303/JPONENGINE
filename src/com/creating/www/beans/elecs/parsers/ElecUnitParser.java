/**
 * 
 */
package com.creating.www.beans.elecs.parsers;

import com.creating.www.beans.elecs.Board;
import com.creating.www.beans.elecs.ElecType;
import com.creating.www.beans.elecs.ElecUnit;
import com.creating.www.beans.elecs.NET;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年11月29日
 *
 */
public class ElecUnitParser {

	/**
	 *网元单元
	 * */
	public static ElecUnit newNET(String devInfo, Integer layerLocationNumber) 
	{
		return new NET(devInfo, layerLocationNumber, ElecType.NET);
	}
	
	/**
	 *板卡单元
	 * */
	public static ElecUnit newBoard(String devInfo, Integer layerLocationNumber) 
	{
		return new Board(devInfo, layerLocationNumber, ElecType.BOARD);
	}
	/**
	 *PON口单元
	 * */
	public static ElecUnit newPONInterface(String devInfo, Integer layerLocationNumber) 
	{
		return new Board(devInfo, layerLocationNumber, ElecType.PONINTERFACE);
	}
	/**
	 *ONU单元
	 * */
	public static ElecUnit newONU(String devInfo, Integer layerLocationNumber) 
	{
		return new Board(devInfo, layerLocationNumber, ElecType.ONU);
	}
	/**
	 *Other单元
	 * */
	public static ElecUnit newOtherUnit(String devInfo, Integer layerLocationNumber) 
	{
		return new Board(devInfo, layerLocationNumber, ElecType.OTHER);
	}
}
