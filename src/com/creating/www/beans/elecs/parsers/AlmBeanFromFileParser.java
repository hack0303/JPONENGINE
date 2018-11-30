/**
 * 
 */
package com.creating.www.beans.elecs.parsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.creating.www.AlmBean;
import com.creating.www.impl.PONAlarm;
import com.creating.www.parsers.Parser;
import com.creating.www.utils.SDFUtil;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年11月29日
 *
 */
public class AlmBeanFromFileParser implements Parser<File,List<AlmBean>>{
     private static String ALM_FILE_PATTERN_001=",";
     private static String ALM_FILE_PATTERN_002="\"";
     static Logger logger=LogManager.getLogger(AlmBeanFromFileParser.class.getName());
	/* (non-Javadoc)
	 * @see com.creating.www.parsers.Parser#parse(java.lang.Object)
	 */
	@Override
	public List<AlmBean> parse(File file) {
		if(!file.isFile()) return null;
		BufferedReader br=null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<AlmBean> list=null;
		String line="";
		try {
			line=br.readLine();
			if(line==null) return null;
			list=new ArrayList<AlmBean>();
			while((line=br.readLine())!=null) 
			{
				//clogid(1),calarmcode(7),crecvoccurutctime(12),clocationinfo(13),
				String[] strs=line.split(ALM_FILE_PATTERN_001);
				Integer id=Integer.valueOf(strs[0].substring(1,strs[0].length()-1));
				Integer code=Integer.valueOf(strs[6].substring(1,strs[6].length()-1));
				Date date=SDFUtil.format(strs[11].substring(1,strs[11].length()-1));
				String location=strs[12].substring(1,strs[12].length()-1);
				PONAlarm almBean=new PONAlarm();
				almBean.setId(id);
				almBean.setCode(code);
				almBean.setFirstCreateTime(date);
				almBean.setLocation(location);
				list.add(almBean);
			}
		} catch (IOException e) {
			logger.error("IO读取失败",e);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.creating.www.parsers.Parser#parse()
	 */
	@Override
	public List<AlmBean> parse() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
