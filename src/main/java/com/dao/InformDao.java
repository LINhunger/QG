package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.db.DBUtil;
import com.db.SplitPage;
import com.model.Inform;
import com.util.GetTime;

public class InformDao {
	static Connection conn;//声明Connection对象
	static PreparedStatement sql;//声明预处理语句
	static ResultSet rs;//声明结果集
	private int totalRows;//记录总的记录数
	
	//测试函数
		public static void main(String[] args) {
			InformDao dao = new InformDao();
			dao.deleteAllInform(1);
			System.out.println(dao.getTotalRowsByR_id(23));

		}
	//1、根据r_id获取全部提醒消息，并分页
		public  List<Inform> getAllInformByR_id(int r_id,SplitPage sp)
		{
					List<Inform> informs = new ArrayList<Inform>();

					conn = DBUtil.getConnection();
					try {
						sql=conn.prepareStatement
								("select * from inform where  r_id=? " +
										"  order by createtime desc "+
										" limit " 
						+ sp.getPageRows()* (sp.getCurrentPage()-1) 
				   				+"," + sp.getPageRows());
						sql.setInt(1, r_id);
						rs=sql.executeQuery();
						while(rs.next()) {
							Inform  inform = new Inform();
							inform.setId(rs.getInt("id"));
							inform.setP_username(rs.getString("p_username"));
							inform.setCreatetime(rs.getString("createtime"));
							inform.setR_id(rs.getInt("r_id"));
							inform.setA_id(rs.getInt("a_id"));
							informs.add(inform);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							System.out.println("getAllInformByR_id is running");
							rs.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					//	DBUtil.closeConnection(conn);
					}
					return informs;
		}
	//2、删除全部提示消息
		public void deleteAllInform(int r_id)
		{
			conn = DBUtil.getConnection();
			try {
				sql=conn.prepareStatement("delete from inform where r_id=?");
				sql.setInt(1, r_id);
				sql.executeUpdate();
				sql.close();
				System.out.println("deleteAllInform is running");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
		//	DBUtil.closeConnection(conn);
			}
		}
	//3、根据r_id(接收用户的id)获取消息记录数，用于分页
	    public int getTotalRowsByR_id(int r_id){
	    	totalRows=0;
	    	conn = DBUtil.getConnection();
			try {
				sql=conn.prepareStatement("select * from inform where r_id=?");
				sql.setInt(1, r_id);
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
	    //4、提示消息插入方法
		public void saveInform(Inform inform)
		{
					conn = DBUtil.getConnection();
					try {
						sql=conn.prepareStatement("insert into inform"+" "+"values(null,?,?,?,?)");
						sql.setString(1, inform.getP_username());
						sql.setString(2,GetTime.GetNowTime());
						sql.setInt(3, inform.getR_id());
						sql.setInt(4, inform.getA_id());
						sql.executeUpdate();
						sql.close();
						System.out.println("saveInform is running");
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
				//	DBUtil.closeConnection(conn);
					}
		}
}
