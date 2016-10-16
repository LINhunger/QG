package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.db.DBUtil;
import com.db.SplitPage;
import com.model.Article;
import com.util.GetTime;

public class ArticleDao {
	static Connection conn;//声明Connection对象
	static PreparedStatement sql;//声明预处理语句
	static ResultSet rs;//声明结果集
	private int totalRows;//记录总的记录数
	private  boolean flag=false;//判断标志
	
	//测试函数
	public static void main(String[] args) {
		ArticleDao dao=new ArticleDao();
		System.out.println(dao.getAllArticleByTitle("学习"));
	}
	//1、数据库查询函数，传入id，返回指定文章对象
	public  Article getArticleById(int a_id)
	{
		Article article= new Article();
		conn = DBUtil.getConnection();
		try {
			sql=conn.prepareStatement("select * from article");
			rs=sql.executeQuery();
			while(rs.next()) {
				article.setA_id(rs.getInt("a_id"));
				article.setA_title(rs.getString("a_title"));
				article.setA_createtime(rs.getString("a_createtime"));
				article.setA_type(rs.getString("a_type"));
				article.setA_content(rs.getString("a_content"));
				article.setU_id(rs.getInt("u_id"));
				article.setUsername(rs.getString("username"));
				article.setStatus(rs.getString("status"));
				article.setInform(rs.getString("inform"));
				if(a_id==article.getA_id()) {
					flag = true; break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println(" getArticleById is running");
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	DBUtil.closeConnection(conn);
		}
		if(flag){
			flag=false;
			return article;
		}
		else 
			return null;
	}
	//2、数据库查询函数，利用模糊查询，返回指定名称的文章集合
	public  List<Article> getAllArticleByTitle(String a_title)
	{
		List<Article> articles = new ArrayList<Article>();
		//对传来的关键字进行过滤
		a_title = a_title.replaceAll(" ", "");

		conn = DBUtil.getConnection();
		try {
			sql=conn.prepareStatement("select * from article  where  (a_title like '%"+a_title+"%'"
					+"  or a_type like '%"+a_title+"%'"
					+"  or username like '%"+a_title+"%')"
					+"  and status='publish'"
					+"  order by a_createtime desc"
					);
			rs=sql.executeQuery();
			while(rs.next()) {
				Article article= new Article();
				article.setA_id(rs.getInt("a_id"));
				article.setA_title(rs.getString("a_title"));
				article.setA_createtime(rs.getString("a_createtime"));
				article.setA_type(rs.getString("a_type"));
				article.setA_content(rs.getString("a_content"));
				article.setU_id(rs.getInt("u_id"));
				article.setUsername(rs.getString("username"));
				article.setStatus(rs.getString("status"));
				article.setInform(rs.getString("inform"));
				articles.add(article);
			}
			//按时间倒序排序
		//	Collections.sort(articles, new ArticleComparator());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("getAllArticleByName is running");
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	DBUtil.closeConnection(conn);
		}
		return articles;
	}
	//3、分页查询
	public  List<Article> getAllArticle(SplitPage sp)
	{
		List<Article> articles = new ArrayList<Article>();

		conn = DBUtil.getConnection();
		try {
	   		sql=conn.prepareStatement("select * from article"
	   				+" where status='publish'"
	   				+" order by a_createtime desc "+" limit " 
	   				+ sp.getPageRows()* (sp.getCurrentPage()-1) 
	   				+"," + sp.getPageRows()
	   				);
			rs=sql.executeQuery();
			while(rs.next()) {
				Article article= new Article();
				article.setA_id(rs.getInt("a_id"));
				article.setA_title(rs.getString("a_title"));
				article.setA_createtime(rs.getString("a_createtime"));
				article.setA_type(rs.getString("a_type"));
				article.setA_content(rs.getString("a_content"));
				article.setU_id(rs.getInt("u_id"));
				article.setUsername(rs.getString("username"));
				article.setStatus(rs.getString("status"));
				article.setInform(rs.getString("inform"));
				articles.add(article);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("getAllArticle is running");
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	DBUtil.closeConnection(conn);
		}
		return articles;
	}
	//4、作者id查询，放回文章作者为指定id的文章集合
	public  List<Article> getAllArticleByU_id(int u_id,SplitPage sp){
		List<Article> articles = new ArrayList<Article>();

		conn = DBUtil.getConnection();
		try {
			sql=conn.prepareStatement("select * from article  where u_id="+u_id
					+" order by a_createtime desc"
					+"  limit " 
					+sp.getPageRows()* (sp.getCurrentPage()-1) 
	   				+"," + sp.getPageRows()
					);
			rs=sql.executeQuery();
			while(rs.next()) {
				Article article= new Article();
				article.setA_id(rs.getInt("a_id"));
				article.setA_title(rs.getString("a_title"));
				article.setA_createtime(rs.getString("a_createtime"));
				article.setA_type(rs.getString("a_type"));
				article.setA_content(rs.getString("a_content"));
				article.setU_id(rs.getInt("u_id"));
				article.setUsername(rs.getString("username"));
				article.setStatus(rs.getString("status"));
				article.setInform(rs.getString("inform"));
				articles.add(article);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("getAllArticleByU_id is running");
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	DBUtil.closeConnection(conn);
		}
		return articles;
	}
	//5、文章添加函数，将编辑的文章存入数据库，但未发布
	
	public void saveArticle(Article article)
	{
		conn = DBUtil.getConnection();
		try {
			sql=conn.prepareStatement("insert into article"+" "+"values(null,?,?,?,?,?,?,?,?)");
			sql.setString(1, article.getA_title());
			sql.setString(2,article.getA_type());
			sql.setString(3,GetTime.GetNowTime());
			sql.setString(4,article.getA_content());
			sql.setInt(5, article.getU_id());
			sql.setString(6,article.getUsername());
			sql.setString(7,article.getStatus());
			sql.setString(8,article.getInform());
			sql.executeUpdate();
			sql.close();
			System.out.println("saveArticle is running");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	//	DBUtil.closeConnection(conn);
		}
	}
	//6、数据库删除函数，根据文章id删除文章
	public void deleteArticle(int a_id)
	{
		conn = DBUtil.getConnection();
		try {
			sql=conn.prepareStatement("delete from article where a_id=?");
			sql.setInt(1, a_id);
			sql.executeUpdate();
			sql.close();
			System.out.println("deleteArticle is running");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	//	DBUtil.closeConnection(conn);
		}
	}
	//7、文章发布函数，根据文章id更改status属性也可以用来封文章
	//注意同时更新时间
	public void publishArticle(int a_id,String status){
		 conn = DBUtil.getConnection();
		try {
			sql=conn.prepareStatement("update article set status=? ,a_createtime=?  where a_id=?");
			sql.setString(1, status);
			sql.setString(2, GetTime.GetNowTime());
			sql.setInt(3, a_id);
			
			sql.executeUpdate();
			sql.close();
			System.out.println("publishArticle is running");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	//	DBUtil.closeConnection(conn);
		}
	}
	//8、文章编辑函数,传入参数问标题，类别和内容以及文章a_id
	public void changeArticle(int a_id,String a_title,String a_type,String a_content){
		 conn = DBUtil.getConnection();
		try {
			sql=conn.prepareStatement
					("update article set a_title=? ,a_type=? ,a_createtime=? , a_content=?  where a_id=?");
			sql.setString(1, a_title);
			sql.setString(2, a_type);
			sql.setString(3, GetTime.GetNowTime());
			sql.setString(4, a_content);
			sql.setInt(5, a_id);
			
			sql.executeUpdate();
			sql.close();
			System.out.println("changeArticle is running");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	//	DBUtil.closeConnection(conn);
		}
	}

	//9、管理员通知函数
	public void inform(int a_id,String inform){
		 conn = DBUtil.getConnection();
		try {
			sql=conn.prepareStatement("update article set inform=?  where a_id=?");
			sql.setString(1, inform);
			sql.setInt(2, a_id);
			
			sql.executeUpdate();
			sql.close();
			System.out.println("inform is running");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
	//	DBUtil.closeConnection(conn);
		}
	}
	//10、分页查询，返回文章集合
			public  List<Article> getAllArticleByA_type(String a_type,SplitPage sp)
			{
						List<Article> articles = new ArrayList<Article>();

						conn = DBUtil.getConnection();
						try {
							sql=conn.prepareStatement
									("select * from article where  a_type=? " +
											"and status='publish'" +
											"  order by a_createtime desc "+
											"limit " + sp.getPageRows()* (sp.getCurrentPage()-1) 
					   				+"," + sp.getPageRows());
							sql.setString(1, a_type);
							rs=sql.executeQuery();
							while(rs.next()) {
								Article article= new Article();
								article.setA_id(rs.getInt("a_id"));
								article.setA_title(rs.getString("a_title"));
								article.setA_createtime(rs.getString("a_createtime"));
								article.setA_type(rs.getString("a_type"));
								article.setA_content(rs.getString("a_content"));
								article.setU_id(rs.getInt("u_id"));
								article.setUsername(rs.getString("username"));
								article.setStatus(rs.getString("status"));
								article.setInform(rs.getString("inform"));
								articles.add(article);
							}
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							try {
								System.out.println("getAllArticleByA_type is running");
								rs.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						//	DBUtil.closeConnection(conn);
						}
						return articles;
			}
	 //11、获取总记录数
    public int getTotalRows(){
    	totalRows=0;
    	conn = DBUtil.getConnection();
		try {
			sql=conn.prepareStatement("select * from article where status='publish'");
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
    //12、获取分类记录数
    public int getTotalRows(String a_type){
    	totalRows=0;
    	conn = DBUtil.getConnection();
		try {
			sql=conn.prepareStatement("select * from article where a_type=?  and status='publish'");
			sql.setString(1, a_type);
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
    //13、获取指定用户记录数
    public int getTotalRows(int u_id){
    	totalRows=0;
    	conn = DBUtil.getConnection();
		try {
			sql=conn.prepareStatement("select * from article where u_id=?");
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
  //14、作者id查询，放回文章作者为指定id的文章集合
  	public  List<Article> getAllArticleByU_id(int u_id){
  		List<Article> articles = new ArrayList<Article>();

  		conn = DBUtil.getConnection();
  		try {
  			sql=conn.prepareStatement("select * from article  where u_id="+u_id
  					+" order by a_createtime desc"
  					);
  			rs=sql.executeQuery();
  			while(rs.next()) {
  				Article article= new Article();
  				article.setA_id(rs.getInt("a_id"));
  				article.setA_title(rs.getString("a_title"));
  				article.setA_createtime(rs.getString("a_createtime"));
  				article.setA_type(rs.getString("a_type"));
  				article.setA_content(rs.getString("a_content"));
  				article.setU_id(rs.getInt("u_id"));
  				article.setUsername(rs.getString("username"));
  				article.setStatus(rs.getString("status"));
  				article.setInform(rs.getString("inform"));
  				articles.add(article);
  			}
  		} catch (Exception e) {
  			e.printStackTrace();
  		} finally {
  			try {
  				System.out.println("getAllArticleByU_id is running");
  				rs.close();
  			} catch (SQLException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}
  		//	DBUtil.closeConnection(conn);
  		}
  		return articles;
  	}
}
