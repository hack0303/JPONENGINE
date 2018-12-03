/**
 * 
 */
package com.creating.www.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年12月3日
 *
 */
public class CollectionTest {

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
		List<Integer> list=new ArrayList<Integer>();
		for(int i=0;i<10;i++) 
		{
			list.add(i);
		}
		System.out.println(list.size());
		List<Integer> dst=Arrays.asList(new Integer[list.size()]);
		System.out.println(dst.size());	
		Collections.copy(dst,list);
		assertEquals(10,dst.size());
		for(int i=0;i<10;i++) 
		{
			assertArrayEquals(list.toArray(),dst.toArray());
		}
         System.out.println(list);
         System.out.println(dst);
	}

}
