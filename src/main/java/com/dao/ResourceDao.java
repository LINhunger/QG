package com.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.db.DBUtil;
import com.db.SplitPage;
import com.model.Resource;
import com.model.User;

public class ResourceDao{
	static Connection conn;//声明Connection对象
	static PreparedStatement sql;//声明预处理语句
	static ResultSet rs;//声明结果集
	private  boolean flag=false;//判断标志
	private int totalRows;//记录总的记录数
	
	//测试函数
	public static void main(String[] args) {
		ResourceDao dao = new ResourceDao();
		System.out.println(dao.getAllResourceByName("bbbbbb"));
}
	//数据库查询函数，传入id，返回资源对象
	public  Resource  getResourceById(int id)
	{
		Resource resource= new Resource();
		conn = DBUtil.getConnection();
		try {
			sql=conn.prepareStatement("select * from resource");
			rs=sql.executeQuery();
			while(rs.next()) {
				resource.setId(Integer.parseInt(rs.getString("id")));
				resource.setUuidname(rs.getString("uuidname"));
				resource.setRealname(rs.getString("realname"));
				resource.setSavepath(rs.getString("savepath"));
				resource.setUploadtime(rs.getTimestamp("uploadtime"));
				resource.setDescription(rs.getString("description"));
				resource.setUsername(rs.getString("username"));
				if(id==resource.getId()) {
					flag = true; break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("getResourceById is running");
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	DBUtil.closeConnection(conn);
		}
		if(flag){
			flag=false;
			return resource;
		}
		else 
			return null;
	}
	//数据库查询函数，返回资源对象的集合
	public  List<Resource> getAllResource()
	{
		List<Resource> resources  = new ArrayList<Resource>();

		conn = DBUtil.getConnection();
		try {
			sql=conn.prepareStatement("select * from resource ");
			rs=sql.executeQuery();
			while(rs.next()) {
				Resource resource= new Resource();
				resource.setId(Integer.parseInt(rs.getString("id")));
				resource.setUuidname(rs.getString("uuidname"));
				resource.setRealname(rs.getString("realname"));
				resource.setSavepath(rs.getString("savepath"));
				resource.setUploadtime(rs.getTimestamp("uploadtime"));
				resource.setDescription(rs.getString("description"));
				resource.setUsername(rs.getString("username"));
				resources.add(resource);
			//	System.out.println(user.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("getAllResource is running");
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	DBUtil.closeConnection(conn);
		}
		return resources;
	}
	
	//数据库查询函数，返回指定用户名资源的集合
	public  List<Resource> getAllResourceByName(String username)
	{
		List<Resource> resources  = new ArrayList<Resource>();

		conn = DBUtil.getConnection();
		try {
			sql=conn.prepareStatement("select * from resource where username =?");
			sql.setString(1, username);
			rs=sql.executeQuery();
			while(rs.next()) {
				Resource resource= new Resource();
				resource.setId(Integer.parseInt(rs.getString("id")));
				resource.setUuidname(rs.getString("uuidname"));
				resource.setRealname(rs.getString("realname"));
				resource.setSavepath(rs.getString("savepath"));
				resource.setUploadtime(rs.getTimestamp("uploadtime"));
				resource.setDescription(rs.getString("description"));
				resource.setUsername(rs.getString("username"));
				resources.add(resource);
			//	System.out.println(user.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("getAllResource is running");
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	DBUtil.closeConnection(conn);
		}
		return resources;
	}
	//数据库插入函数，
	public void saveResource(Resource resource)
	{
		conn = DBUtil.getConnection();
		try {
			sql=conn.prepareStatement("insert into resource"+" "+"values(null,?,?,?,null,?,?)");
			sql.setString(1,resource.getUuidname());
			sql.setString(2, resource.getRealname());
			sql.setString(3, resource.getSavepath());
			sql.setString(4, resource.getDescription());
			sql.setString(5, resource.getUsername());
			sql.executeUpdate();
			sql.close();
			System.out.println("saveResource is running");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	//	DBUtil.closeConnection(conn);
		}
	}
	//数据库删除函数，根据文件id删除资源
	public void deleteResource(int id)
	{
		conn = DBUtil.getConnection();
		try {
			sql=conn.prepareStatement("delete from resource where id=?");
			sql.setString(1, id+"");
			sql.executeUpdate();
			sql.close();
			System.out.println("delete Resource is running");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	//	DBUtil.closeConnection(conn);
		}
	}
	//分页查询
    public List<Resource> getAllResource(SplitPage sp) {
           List<Resource> resources = new ArrayList<Resource>();
           //获取数据库连接对象
           conn = DBUtil.getConnection();

           //在sqlserver数据中分页查询的sql语句,分页的参数来自SplitPage对象
          
           try {
        	   		sql=conn.prepareStatement("select * from resource  limit " + sp.getPageRows()* (sp.getCurrentPage()-1) 
        	   				+"," + sp.getPageRows()
        	   				);
        	   		rs=sql.executeQuery();
                  while (rs.next()) {
      				Resource resource= new Resource();
    				resource.setId(Integer.parseInt(rs.getString("id")));
    				resource.setUuidname(rs.getString("uuidname"));
    				resource.setRealname(rs.getString("realname"));
    				resource.setSavepath(rs.getString("savepath"));
    				resource.setUploadtime(rs.getTimestamp("uploadtime"));
    				resource.setDescription(rs.getString("description"));
    				resource.setUsername(rs.getString("username"));
    				resources.add(resource);
                  }
           } catch (SQLException e) {
                  e.printStackTrace();
           } 
           return resources;
    }
    //获取总记录数
    public int getTotalRows(){
    	totalRows=0;
    	conn = DBUtil.getConnection();
		try {
			sql=conn.prepareStatement("select * from resource");
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