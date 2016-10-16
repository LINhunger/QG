package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.db.DBUtil;
import com.model.Comment;
import com.model.Recomment;
import com.util.GetTime;

public class RecommentDao {
	static Connection conn;//声明Connection对象
	static PreparedStatement sql;//声明预处理语句
	static ResultSet rs;//声明结果集
	private int totalRows;//记录总的记录数
	private  boolean flag=false;//判断标志
	
	//测试函数
	public static void main(String[] args) {
		RecommentDao dao = new RecommentDao();
		Recomment recomment= dao.getRecommentByR_Id(1);
		System.out.println(dao.getAllRecommentByC_id(10));
	}
	//回复查询函数，根据r_id返回指定回复
	public  Recomment getRecommentByR_Id(int r_id){
		 Recomment  recomment = new  Recomment();
		conn = DBUtil.getConnection();
		try {
			sql=conn.prepareStatement("select * from recomment");
			rs=sql.executeQuery();
			while(rs.next()) {
				recomment.setR_id(rs.getInt("r_id"));
				recomment.setR_createtime(rs.getString("r_createtime"));
				recomment.setR_content(rs.getString("r_content"));
				recomment.setPublishid(rs.getInt("publishid"));
				recomment.setReceiveid(rs.getInt("receiveid"));
				recomment.setC_id(rs.getInt("c_id"));
				recomment.setR_username(rs.getString("r_username"));
				if(r_id==recomment.getR_id()) {
					flag = true; break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println(" getRecommentByR_Id is running");
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	DBUtil.closeConnection(conn);
		}
		if(flag){
			flag=false;
			return recomment;
		}
		else 
			return null;
	}
	//2、利用指定c_id，返回回复集合
	public  List<Recomment> getAllRecommentByC_id(int c_id)
	{
		List<Recomment> recomments = new ArrayList<Recomment>();

		conn = DBUtil.getConnection();
		try {
			sql=conn.prepareStatement("select * from recomment  where c_id =?");
			sql.setInt(1, c_id);
			rs=sql.executeQuery();
			while(rs.next()) {
				Recomment  recomment = new Recomment();
				recomment.setR_id(rs.getInt("r_id"));
				recomment.setR_createtime(rs.getString("r_createtime"));
				recomment.setR_content(rs.getString("r_content"));
				recomment.setPublishid(rs.getInt("publishid"));
				recomment.setReceiveid(rs.getInt("receiveid"));
				recomment.setC_id(rs.getInt("c_id"));
				recomment.setR_username(rs.getString("r_username"));
				recomments.add(recomment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("getAllRecommentByC_id is running");
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	DBUtil.closeConnection(conn);
		}
		return recomments;
	}
	//3、回复添加函数
	public void saveRecomment(Recomment recomment)
	{
				conn = DBUtil.getConnection();
				try {
					sql=conn.prepareStatement("insert into recomment"+" "+"values(null,?,?,?,?,?,?)");
					sql.setString(1, GetTime.GetNowTime());
					sql.setString(2,recomment.getR_content());
					sql.setInt(3, recomment.getPublishid());
					sql.setInt(4, recomment.getReceiveid());
					sql.setInt(5, recomment.getC_id());
					sql.setString(6, recomment.getR_username());
					sql.executeUpdate();
					sql.close();
					System.out.println("saveRecomment is running");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
			//	DBUtil.closeConnection(conn);
				}
	}
	//4、回复删除函数
	public void deleteRecomment(int r_id)
	{
		conn = DBUtil.getConnection();
		try {
			sql=conn.prepareStatement("delete from recomment where r_id=?");
			sql.setInt(1, r_id);
			sql.executeUpdate();
			sql.close();
			System.out.println("deleteRecomment is running");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	//	DBUtil.closeConnection(conn);
		}
	}
}
