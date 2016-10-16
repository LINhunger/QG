package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.db.DBUtil;
import com.db.SplitPage;
import com.model.Favorite;

public class FavoriteDao {
	static Connection conn;//声明Connection对象
	static PreparedStatement sql;//声明预处理语句
	static ResultSet rs;//声明结果集
	private int totalRows;//记录总的记录数
	//测试函数
		public static void main(String[] args) {
			FavoriteDao dao = new FavoriteDao(); 
			dao.deleteAllFavorite(2);
			System.out.println(dao.getTotalRowsByU_id(2));
		}
		//1、根据u_id获取全部收藏信息，并分页
				public  List<Favorite> getAllFavoriteByU_id(int u_id,SplitPage sp)
				{
							List<Favorite> favorites = new ArrayList<Favorite>();

							conn = DBUtil.getConnection();
							try {
								sql=conn.prepareStatement
										("select * from favorite where  u_id=? " +
												" limit " 
								+ sp.getPageRows()* (sp.getCurrentPage()-1) 
						   				+"," + sp.getPageRows());
								sql.setInt(1, u_id);
								rs=sql.executeQuery();
								while(rs.next()) {
									Favorite  favorite = new Favorite();
									favorite.setId(rs.getInt("id"));
									favorite.setU_id(rs.getInt("u_id"));
									favorite.setA_id(rs.getInt("a_id"));
									favorite.setA_title(rs.getString("a_title"));
									favorites.add(favorite);
								}
							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								try {
									System.out.println("getAllFavoriteByU_id is running");
									rs.close();
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							//	DBUtil.closeConnection(conn);
							}
							return favorites;
				}
				//2、删除全部收藏消息
				public void deleteAllFavorite(int u_id)
				{
					conn = DBUtil.getConnection();
					try {
						sql=conn.prepareStatement("delete from favorite where u_id=?");
						sql.setInt(1, u_id);
						sql.executeUpdate();
						sql.close();
						System.out.println("deleteAllInFavorite is running");
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
				//	DBUtil.closeConnection(conn);
					}
				}
				//3、根据u_id(接收用户的id)获取收藏记录数，用于分页
			    public int getTotalRowsByU_id(int u_id){
			    	totalRows=0;
			    	conn = DBUtil.getConnection();
					try {
						sql=conn.prepareStatement("select * from favorite where u_id=?");
						sql.setInt(1, u_id);
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
			    //4、收藏消息插入方法
				public void saveFavorite(Favorite favorite)
				{
							conn = DBUtil.getConnection();
							try {
								sql=conn.prepareStatement("insert into favorite"+" "+"values(null,?,?,?)");
								sql.setInt(1, favorite.getU_id());
								sql.setInt(2, favorite.getA_id());
								sql.setString(3, favorite.getA_title());
								sql.executeUpdate();
								sql.close();
								System.out.println("saveFavorite is running");
							} catch (Exception e) {
								e.printStackTrace();
							} finally {
						//	DBUtil.closeConnection(conn);
							}
				}
				//5、根据收藏id删除个别收藏消息
				public void deleteFavorite(int id)
				{
					conn = DBUtil.getConnection();
					try {
						sql=conn.prepareStatement("delete from favorite where id=?");
						sql.setInt(1, id);
						sql.executeUpdate();
						sql.close();
						System.out.println("deleteFavorite is running");
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
				//	DBUtil.closeConnection(conn);
					}
				}
}
