/**
 * 
 */
package com.creating.www.beans.support;

import java.io.File;
import java.util.List;

import com.creating.www.AlmBean;
import com.creating.www.beans.elecs.parsers.AlmBeanFromFileParser;
import com.creating.www.parsers.Parser;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年11月29日
 *
 */
public class AlmBeanHelper {

	public List<AlmBean> newAlmBeans(String filepath){
		return newAlmBeans(new File(filepath));
	}
	public List<AlmBean> newAlmBeans(File file){
		List<AlmBean> almBeans=null;
		if(!file.isFile()) return null;
		Parser<File,List<AlmBean>> parser=new AlmBeanFromFileParser();
		almBeans=parser.parse(file);
		return almBeans;
	}
}
