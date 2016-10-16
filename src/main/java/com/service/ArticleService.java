package com.service;

import java.util.List;

import com.dao.ArticleDao;
import com.db.SplitPage;
import com.model.Article;

public class ArticleService {
	ArticleDao dao = new ArticleDao();
	//1传入id，返回指定文章对象
	public  Article getArticleById(int a_id){
		return dao.getArticleById(a_id);
	}
	//2、利用模糊查询，返回指定名称的文章集合
	public  List<Article> getAllArticleByTitle(String a_title){
		return dao.getAllArticleByTitle(a_title);
	}
	//3、分页查询
	public  List<Article> getAllArticle(SplitPage sp){
		return dao.getAllArticle(sp);	
	}
	//4、作者id查询，放回文章作者为指定id的文章集合
		public  List<Article> getAllArticleByU_id(int u_id,SplitPage sp){
			return dao.getAllArticleByU_id(u_id,sp);
		}
	//5、文章添加函数，将编辑的文章存入数据库，但未发布
		public void saveArticle(Article article){
			dao.saveArticle(article);
		}
	//6、文章删除函数
		public void deleteArticle(int a_id){
			dao.deleteArticle(a_id);
		}
	//7、文章发布函数，根据文章id更改status属性也可以用来封文章
		public void publishArticle(int a_id,String status){
			dao.publishArticle(a_id, status);
		}
	//8、文章编辑函数,传入参数问标题，类别和内容以及文章a_id
		public void changeArticle(int a_id,String a_title,String a_type,String a_content){
			dao.changeArticle(a_id, a_title, a_type, a_content);
		}
	//9、管理员通知函数
		public void inform(int a_id,String inform){
			dao.inform(a_id, inform);
		}
	//10、分页分类查询
		public  List<Article> getAllArticleByA_type(String a_type,SplitPage sp){
			return dao.getAllArticleByA_type(a_type, sp);
		}
	//11、获取总记录数
		 public int getTotalRows(){
			 return dao.getTotalRows();
		 }
	//12、获取分类记录数
		 public int getTotalRows(String a_type){
			 return dao.getTotalRows(a_type);
		 }
	//13、获取指定用户记录数
		  public int getTotalRows(int u_id){
			  return dao.getTotalRows(u_id);
		  }
	//14、作者id查询，放回文章作者为指定id的文章集合
		  public  List<Article> getAllArticleByU_id(int u_id){
			  return dao.getAllArticleByU_id(u_id);
		  }
}
