package com.creating.www.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Chack Yao
 * @firstcreatetime 2018年12月3日 下午3:54:22
 * */
public class Conn {

	public static Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException 
	{
		Connection conn=null;
		String driver="com.mysql.cj.jdbc.Driver";
		String url="jdbc:mysql://192.168.0.13:3306/demo";
		String username="cmcclte";
		String password="r00t_Pwd";
		Class.forName(driver).newInstance();
		conn=DriverManager.getConnection(url,username,password);
		return conn;
	}
}
