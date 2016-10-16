package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	private static final String URL="jdbc:mysql://127.0.0.1:3306/QG?useUnicode=true&characterEncoding=utf8";
	private static final String USER="root";
	private static final String PASSWORD="123456";	
	private static Connection conn=null;
	static {
		try {
			//1.加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2.访问数据库，获取连接对象
			conn=DriverManager.getConnection(URL, USER, PASSWORD);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//获取数据库连接对象
	public static Connection getConnection(){
		return conn;
	}
	//断开与数据库连接
	public static void closeConnection(Connection conn)
	{
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}