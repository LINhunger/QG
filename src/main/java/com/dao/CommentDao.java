package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.db.DBUtil;
import com.db.SplitPage;
import com.model.Comment;
import com.util.GetTime;

public class CommentDao {
	static Connection conn;//声明Connection对象
	static PreparedStatement sql;//声明预处理语句
	static ResultSet rs;//声明结果集
	private int totalRows;//记录总的记录数
	private  boolean flag=false;//判断标志
	
	
	//测试函数
	public static void main(String[] args) {
		CommentDao dao = new CommentDao();
		System.out.println(dao.getTotalRowsByA_id(30));

	}
	//评论查询函数，根据c_id返回指定评论
	public  Comment getCommentByC_Id(int c_id){
		Comment  comment = new Comment();
		conn = DBUtil.getConnection();
		try {
			sql=conn.prepareStatement("select * from comment");
			rs=sql.executeQuery();
			while(rs.next()) {
				comment.setC_id(rs.getInt("c_id"));
				comment.setCu_id(rs.getInt("cu_id"));
				comment.setC_content(rs.getString("c_content"));
				comment.setC_createtime(rs.getString("c_createtime"));
				comment.setA_id(rs.getInt("a_id"));
				comment.setU_id(rs.getInt("u_id"));
				if(c_id==comment.getC_id()) {
					flag = true; break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println(" getCommentByC_Id is running");
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	DBUtil.closeConnection(conn);
		}
		if(flag){
			flag=false;
			return comment;
		}
		else 
			return null;
	}
		//2、数据库查询函数，利用指定a_id，返回评论集合
		public  List<Comment> getAllCommentByA_id(int a_id)
		{
			List<Comment> comments = new ArrayList<Comment>();

			conn = DBUtil.getConnection();
			try {
				sql=conn.prepareStatement("select * from comment  where a_id =?");
				sql.setInt(1, a_id);
				rs=sql.executeQuery();
				while(rs.next()) {
					Comment  comment = new Comment();
					comment.setC_id(rs.getInt("c_id"));
					comment.setCu_id(rs.getInt("cu_id"));
					comment.setC_content(rs.getString("c_content"));
					comment.setC_createtime(rs.getString("c_createtime"));
					comment.setA_id(rs.getInt("a_id"));
					comment.setU_id(rs.getInt("u_id"));
					comments.add(comment);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					System.out.println("getAllCommentByA_id is running");
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//	DBUtil.closeConnection(conn);
			}
			return comments;
		}
		//3、分页查询，返回评论集合
		public  List<Comment> getAllCommentByA_id(int a_id,SplitPage sp)
		{
					List<Comment> comments = new ArrayList<Comment>();

					conn = DBUtil.getConnection();
					try {
						sql=conn.prepareStatement
								("select * from comment where  a_id=?  limit " 
						+ sp.getPageRows()* (sp.getCurrentPage()-1) 
				   				+"," + sp.getPageRows());
						sql.setInt(1, a_id);
						rs=sql.executeQuery();
						while(rs.next()) {
							Comment  comment = new Comment();
							comment.setC_id(rs.getInt("c_id"));
							comment.setCu_id(rs.getInt("cu_id"));
							comment.setC_content(rs.getString("c_content"));
							comment.setC_createtime(rs.getString("c_createtime"));
							comment.setA_id(rs.getInt("a_id"));
							comment.setU_id(rs.getInt("u_id"));
							comments.add(comment);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							System.out.println("getAllCommentByA_id is running");
							rs.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					//	DBUtil.closeConnection(conn);
					}
					return comments;
		}
		//4、评论添加函数
		public void saveComment(Comment comment)
		{
					conn = DBUtil.getConnection();
					try {
						sql=conn.prepareStatement("insert into comment"+" "+"values(null,?,?,?,?,?)");
						sql.setInt(1, comment.getCu_id());
						sql.setString(2,comment.getC_content());
						sql.setString(3,GetTime.GetNowTime());
						sql.setInt(4, comment.getA_id());
						sql.setInt(5, comment.getU_id());
						sql.executeUpdate();
						sql.close();
						System.out.println("saveComment is running");
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
				//	DBUtil.closeConnection(conn);
					}
		}
		//5、数据库删除函数，根据评论id删除评论
		public void deleteComment(int c_id)
		{
			conn = DBUtil.getConnection();
			try {
				sql=conn.prepareStatement("delete from comment where c_id=?");
				sql.setInt(1, c_id);
				sql.executeUpdate();
				sql.close();
				System.out.println("deleteComment is running");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
		//	DBUtil.closeConnection(conn);
			}
		}
		 //6、获取总记录数
	    public int getTotalRowsByA_id(int a_id){
	    	totalRows=0;
	    	conn = DBUtil.getConnection();
			try {
				sql=conn.prepareStatement("select * from comment where a_id=?");
				sql.setInt(1, a_id);
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
}
