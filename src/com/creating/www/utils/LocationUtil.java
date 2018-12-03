/**
 * 
 */
package com.creating.www.utils;

import java.util.Arrays;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.creating.www.APP;
import com.creating.www.beans.IFactory;
import com.creating.www.beans.elecs.ElecType;
import com.creating.www.beans.elecs.ElecUnit;
import com.creating.www.beans.elecs.parsers.ElecUnitParser;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年11月30日
 *
 */
public class LocationUtil {
	public static final String REGEX001=":";
	static Logger logger=LogManager.getLogger(LocationUtil.class.getName());
public static ElecUnit[] parse(String str) 
{
	String[] strs=str.split(REGEX001);
	int length=strs.length;
	ElecUnit[] objs=null;
	ElecUnit net=null;
	ElecUnit board=null;
	ElecUnit pon=null;
	ElecUnit onu=null;
	ElecUnit other=null;
	String devInfo="";
	Integer layerLocationNumber=null;
	//logger.debug(Arrays.toString(strs));
	switch(length) 
	{

	case 1:{
		devInfo=strs[0];
		net=ElecUnitParser.newNET(devInfo, layerLocationNumber);
		objs= new ElecUnit[]{net};
		break;
	}
	case 2:{
		devInfo=strs[0];
		net=ElecUnitParser.newNET(devInfo, layerLocationNumber);
		devInfo=strs[1];
		board=ElecUnitParser.newBoard(devInfo, layerLocationNumber);
		objs= new ElecUnit[]{net,board};
		break;
	}
	case 3:{
		devInfo=strs[0];
		net=ElecUnitParser.newNET(devInfo, layerLocationNumber);
		devInfo=strs[1];
		board=ElecUnitParser.newBoard(devInfo, layerLocationNumber);
		devInfo=strs[2];
		pon=ElecUnitParser.newPONInterface(devInfo, layerLocationNumber);
	//	if(pon==null)
	//		System.out.println("------------------------------------");
		objs= new ElecUnit[]{net,board,pon};
		break;	
	}
	case 4:{
		devInfo=strs[0];
		net=ElecUnitParser.newNET(devInfo, layerLocationNumber);
		devInfo=strs[1];
		board=ElecUnitParser.newBoard(devInfo, layerLocationNumber);
		devInfo=strs[2];
		pon=ElecUnitParser.newPONInterface(devInfo, layerLocationNumber);
		devInfo=strs[3];
		onu=ElecUnitParser.newONU(devInfo, layerLocationNumber);
		objs= new ElecUnit[]{net,board,pon,onu};
		
		break;	
	}
	case 5:{
		devInfo=strs[0];
		net=ElecUnitParser.newNET(devInfo, layerLocationNumber);
		devInfo=strs[1];
		board=ElecUnitParser.newBoard(devInfo, layerLocationNumber);
		devInfo=strs[2];
		pon=ElecUnitParser.newPONInterface(devInfo, layerLocationNumber);
		devInfo=strs[3];
		onu=ElecUnitParser.newONU(devInfo, layerLocationNumber);
		devInfo=strs[4];
		other=ElecUnitParser.newOtherUnit(devInfo, layerLocationNumber);
		objs= new ElecUnit[]{net,board,pon,onu,other};
		break;	
	}
	case 6:{
		devInfo=strs[0];
		net=ElecUnitParser.newNET(devInfo, layerLocationNumber);
		devInfo=strs[1];
		board=ElecUnitParser.newBoard(devInfo, layerLocationNumber);
		devInfo=strs[2];
		pon=ElecUnitParser.newPONInterface(devInfo, layerLocationNumber);
		devInfo=strs[3];
		layerLocationNumber=Integer.valueOf(strs[4].substring(1,strs[4].length()-1));
		onu=ElecUnitParser.newONU(devInfo, layerLocationNumber);
		objs= new ElecUnit[]{net,board,pon,onu};
		break;
	}
	default:{
		logger.debug("location 分段出现意料之外的情况");
	}
	}
	return objs;

}
}
