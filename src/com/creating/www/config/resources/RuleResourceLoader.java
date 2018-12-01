/**
 * 
 */
package com.creating.www.config.resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.creating.www.APP;
import com.creating.www.RoleType;
import com.creating.www.beans.IFactory;
import com.creating.www.beans.codes.AlarmCode;
import com.creating.www.beans.codes.AlarmCodePair;
import com.creating.www.beans.codes.support.AlarmCodeFactory;
import com.creating.www.beans.codes.support.CodeRulePairFactory;
import com.creating.www.beans.elecs.ElecUnit;
import com.creating.www.beans.elecs.LocationInfo;
import com.creating.www.beans.elecs.support.LocationInfoFactory;
import com.creating.www.core.Cache;
import com.creating.www.utils.LocationUtil;
import com.creating.www.utils.StoreUtil;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年11月29日
 *
 */
public class RuleResourceLoader implements Generator<Map<AlarmCode, Map<RoleType,Set<AlarmCode>>>> {
	static String regex001=",";
	Resource resource=new FileSystemResource("config/rules/rule.csv");
	@SuppressWarnings("unchecked")
	private IFactory<Object[],AlarmCode> _ALARMCODE_FACTORY=(IFactory<Object[],AlarmCode>) APP.atx.getBean(AlarmCodeFactory.class.getSimpleName());
	@SuppressWarnings("unchecked")
	private IFactory<Object[],AlarmCodePair> _CODERULE_PAIR_FACTORY=(IFactory<Object[], AlarmCodePair>) APP.atx.getBean(CodeRulePairFactory.class.getSimpleName());
	@SuppressWarnings("unchecked")
	private IFactory<ElecUnit[],LocationInfo> _LOCATION_FACTORY=(IFactory<ElecUnit[],LocationInfo>) APP.atx.getBean(LocationInfoFactory.class.getSimpleName());
	public Map<AlarmCode,Map<RoleType,Set<AlarmCode>>> _CODE_RULE=new HashMap<AlarmCode,Map<RoleType,Set<AlarmCode>>>();
	static Logger logger=LogManager.getLogger(RuleResourceLoader.class.getName());
	/* (non-Javadoc)
	 * @see com.creating.www.config.resources.Generator#generate()
	 */
	@Override
	public Map<AlarmCode, Map<RoleType,Set<AlarmCode>>> generate() {
		InputStream is=null;
		try {
			is=resource.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br=new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		String line="";
		try {
			line=br.readLine();
		} catch (IOException e) {
			logger.error("IO出错",e);
		}
		if(line==null) return null;
		try {
			while((line=br.readLine())!=null)
			{
				String[] line_spl=line.split(regex001);
				String pairId=line_spl[0];
				//logger.debug(Arrays.toString(line_spl));
				Object[] src={Integer.valueOf(line_spl[1]),_LOCATION_FACTORY.newTarget((ElecUnit[])LocationUtil.parse(line_spl[3]))};
				Object[] dsc={Integer.valueOf(line_spl[2]),_LOCATION_FACTORY.newTarget((ElecUnit[])LocationUtil.parse(line_spl[4]))};
				AlarmCode ac_src=_ALARMCODE_FACTORY.newTarget(src);
				AlarmCode ac_dsc=_ALARMCODE_FACTORY.newTarget(dsc);
				AlarmCodePair acPair=_CODERULE_PAIR_FACTORY.newTarget(new Object[]{Integer.valueOf(pairId),ac_src,ac_dsc});
			    String pairKey=ac_src.getCode()+"->"+ac_dsc.getCode();
				if(!Cache._CODE_RULE_PAIR.containsKey(pairKey)) 
				{
					Cache._CODE_RULE_PAIR.put(pairKey,acPair);
				}
			    StoreUtil.store(_CODE_RULE, ac_src, ac_dsc, RoleType.DESCEND);
			    StoreUtil.store(_CODE_RULE, ac_dsc, ac_src, RoleType.SOURCE);
			}
		} catch (IOException e) {
			logger.error("IO ERROR",e);
		}catch(Exception e) 
		{
			logger.error("OTHER ERROR",e);
		}
		if(is!=null)
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return _CODE_RULE;
	}
	/* (non-Javadoc)
	 * @see com.creating.www.config.resources.Generator#fresh()
	 */
	@Override
	public void fresh() {
		// TODO Auto-generated method stub
		
	}
  
}
