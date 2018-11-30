/**
 * 
 */
package com.creating.www.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年11月29日
 *
 */
public class SDFUtil {
 static SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
 static Logger logger=LogManager.getLogger(SDFUtil.class.getName());
 public static Date format(String source) 
 {
	 try {
		return sdf.parse(source);
	} catch (ParseException e) {
		logger.error("时间解析出错",e);
	}
	return null;
 }
}
