package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.db.DBUtil;
import com.model.User;

public class UserDao{
	static Connection conn;//声明Connection对象
	static PreparedStatement sql;//声明预处理语句
	static ResultSet rs;//声明结果集
	private  boolean flag=false;//判断标志
	
	//测试函数
	public static void main(String[] args) {
		UserDao dao=new UserDao();
		dao.changUser("cccccc", "cccccc");

		System.out.println(dao.getUserByName("cccccc"));
}
	//数据库查询函数，传入用户名，返回用户对象
	public  User getUserByName(String username)
	{
		User user= new User();
		conn = DBUtil.getConnection();
		try {
			sql=conn.prepareStatement("select * from user");
			rs=sql.executeQuery();
			while(rs.next()) {
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setSex(rs.getString("sex"));
				user.setPicture(rs.getString("picture"));
				user.setAdmin(Boolean.parseBoolean(rs.getString("admin")));
				user.setLimit(rs.getInt("limits"));
				if(username.equals(user.getUsername())) {
					flag = true; break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("getUserByName is running");
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	DBUtil.closeConnection(conn);
		}
		if(flag){
			flag=false;
			return user;
		}
		else 
			return null;
	}
	//数据库查询函数，传入id，返回用户对象
		public  User getUserById(int id)
		{
			User user= new User();
			conn = DBUtil.getConnection();
			try {
				sql=conn.prepareStatement("select * from user");
				rs=sql.executeQuery();
				while(rs.next()) {
					user.setId(rs.getInt("id"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setEmail(rs.getString("email"));
					user.setSex(rs.getString("sex"));
					user.setPicture(rs.getString("picture"));
					user.setAdmin(Boolean.parseBoolean(rs.getString("admin")));
					user.setLimit(rs.getInt("limits"));
					if(id==user.getId()) {
						flag = true; break;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					System.out.println("getUserById is running");
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//	DBUtil.closeConnection(conn);
			}
			if(flag){
				flag=false;
				return user;
			}
			else 
				return null;
		}
	//数据库插入函数，
	public void saveUser(User user)
	{
		conn = DBUtil.getConnection();
		try {
			sql=conn.prepareStatement("insert into user"+" "+"values(null,?,?,?,?,?,?,0)");
			sql.setString(1, user.getUsername());
			sql.setString(2, user.getPassword());
			sql.setString(3, user.getEmail());
			sql.setString(4, user.getSex());
			sql.setString(5,user.isAdmin()+"");
			sql.setString(6, null);
			sql.executeUpdate();
			sql.close();
			System.out.println("saveUser is running");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	//	DBUtil.closeConnection(conn);
		}
	}

	//数据库修改函数，根据传入的用户名获取用户对象，根据传入的密码进行password的修改
	public void changUser(String username,String password)
	{
		 conn = DBUtil.getConnection();
		try {
			sql=conn.prepareStatement("update user set password=? where username=?");
			sql.setString(1, password);
			sql.setString(2, username);
			
			sql.executeUpdate();
			sql.close();
			System.out.println("changUser is running");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	//	DBUtil.closeConnection(conn);
		}

	}
	//插入图片路径函数
	public void savePicture(String username,String picture)
	{
		 conn = DBUtil.getConnection();
			try {
				sql=conn.prepareStatement("update user set picture=? where username=?");
				sql.setString(1, picture);
				sql.setString(2, username);
				
				sql.executeUpdate();
				sql.close();
				System.out.println("savePicture is running");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
		//	DBUtil.closeConnection(conn);
			}
	}
}
