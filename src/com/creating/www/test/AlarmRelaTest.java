/**
 * 
 */
package com.creating.www.test;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.creating.www.AlmBean;
import com.creating.www.ICallBack;
import com.creating.www.beans.support.AlmBeanHelper;
import com.creating.www.impl.PONAlarm;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年11月29日
 *
 */
public class AlarmRelaTest {
   static Logger LOG=LogManager.getLogger(AlarmRelaTest.class.getName());
	static List<AlmBean> SalmBeans=null;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testAlmFromCSVFile();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	//@Test
	public void testAlmFromCSVFile() {
		AlmBeanHelper abs=new AlmBeanHelper();
		String filepath="config/rules/t_alarmloghist_3_5.csv";
		List<AlmBean> almBeans=abs.newAlmBeans(filepath);
		SalmBeans=almBeans;
		assertNotNull(almBeans);
		almBeans.forEach(e->{
			LOG.debug("AlmBean id:{},code:{},location:{}.firstcreatetime;{}",((PONAlarm)e).getId(),((PONAlarm)e).getCode(),((PONAlarm)e).getLocation(),((PONAlarm)e).getFirstCreateTime());
		});	
	}
	@Test
	public void testAlarmFromAlm() {
		ICallBack callback=null;
		try {
			callback=new com.creating.www.CallBack();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(SalmBeans);
		try {
			callback.onReport(SalmBeans);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
