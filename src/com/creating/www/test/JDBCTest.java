package com.creating.www.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.creating.www.daos.Conn;
import com.mysql.cj.jdbc.JdbcConnection;

public class JDBCTest {
    static Logger logger=null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	logger=LogManager.getLogger(JDBCTest.class.getName());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws SQLException {
		Connection conn = null;
		try {
			conn = Conn.getConnection();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(conn);
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery("select * from pon_alarm");
		while(rs.next()) 
		{
			logger.debug("ID:{} Location:{} code:{} firstCreateTime:{}",rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
		}
		
	}

}
