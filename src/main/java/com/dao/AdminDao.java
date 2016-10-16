package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.db.DBUtil;
import com.db.SplitPage;
import com.model.User;

public class AdminDao extends UserDao{
	static Connection conn;//声明Connection对象
	static PreparedStatement sql;//声明预处理语句
	static ResultSet rs;//声明结果集
	private int totalRows;//记录总的记录数
	
	//测试函数
	public static void main(String[] args) {
		AdminDao dao=new AdminDao();
		dao.LimitUser(1, 1);
		System.out.println(dao.getAllUser());
}
	//数据库查询函数，返回用户对象的集合
	public  List<User> getAllUser()
	{
		List<User> users = new ArrayList<User>();

		conn = DBUtil.getConnection();
		try {
			sql=conn.prepareStatement("select * from user");
			rs=sql.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setSex(rs.getString("sex"));
				user.setPicture(rs.getString("picture"));
				user.setAdmin(Boolean.parseBoolean(rs.getString("admin")));
				user.setLimit(rs.getInt("limits"));
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("getAllUser is running");
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	DBUtil.closeConnection(conn);
		}
		return users;
	}
	//数据库删除函数，根据用户名删除用户信息
	public void deleteUser(String username)
	{
		conn = DBUtil.getConnection();
		try {
			sql=conn.prepareStatement("delete from user where username=?");
			sql.setString(1, username);
			sql.executeUpdate();
			sql.close();
			System.out.println("deleteUser is running");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	//	DBUtil.closeConnection(conn);
		}
	}
	//数据库查询函数，分页查询
		public  List<User> getAllUser(SplitPage sp)
		{
			List<User> users = new ArrayList<User>();

			conn = DBUtil.getConnection();
			try {
    	   		sql=conn.prepareStatement("select * from user limit " + sp.getPageRows()* (sp.getCurrentPage()-1) 
    	   				+"," + sp.getPageRows());
				rs=sql.executeQuery();
				while(rs.next()) {
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setUsername(rs.getString("username"));
					user.setPassword(rs.getString("password"));
					user.setEmail(rs.getString("email"));
					user.setSex(rs.getString("sex"));
					user.setPicture(rs.getString("picture"));
					user.setAdmin(Boolean.parseBoolean(rs.getString("admin")));
					user.setLimit(rs.getInt("limits"));
					users.add(user);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					System.out.println("getAllUser is running");
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//	DBUtil.closeConnection(conn);
			}
			return users;
		}
		 //获取总记录数
	    public int getTotalRows(){
	    	totalRows=0;
	    	conn = DBUtil.getConnection();
			try {
				sql=conn.prepareStatement("select * from user");
				rs=sql.executeQuery();
				while(rs.next()) {
					totalRows++;				
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return totalRows;
	    }
	    //封号函数，limit不为0则封号
	    public void LimitUser(int id,int limit)
		{
			 conn = DBUtil.getConnection();
			try {
				sql=conn.prepareStatement("update user set  limits=?  where id=?");
				sql.setInt(1, limit);
				sql.setInt(2, id);			
				sql.executeUpdate();
				sql.close();
				System.out.println("LimitUser is running");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
		//	DBUtil.closeConnection(conn);
			}

		}
	    //解除封号函数，limit为0则解除封号
	    public void ReleaseUser(int id,int limit)
		{
			 conn = DBUtil.getConnection();
			try {
				sql=conn.prepareStatement("update user set  limits=?  where id=?");
				sql.setInt(1, limit);
				sql.setInt(2, id);			
				sql.executeUpdate();
				sql.close();
				System.out.println("ReleaseUser is running");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
		//	DBUtil.closeConnection(conn);
			}

		}
}
