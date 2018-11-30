/**
 * 
 */
package com.creating.www.test.rules;

import static org.junit.Assert.*;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.creating.www.APP;
import com.creating.www.config.resources.RuleResourceLoader;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年11月30日
 *
 */
public class CodeRuleFactory {
    
	static 
	{
		try {
			CodeRuleFactory.class.getClassLoader().loadClass(APP.class.getName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	static Logger logger=LogManager.getLogger(CodeRuleFactory.class.getName());
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
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		Map m=new RuleResourceLoader().generate();
		assertNotNull(m);
		logger.error(m);
		assertEquals(5, m.size());
	}

}
