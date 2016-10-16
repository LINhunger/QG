package com.service;

import java.util.List;

import com.dao.CommentDao;
import com.db.SplitPage;
import com.model.Comment;

public class CommentService {
		CommentDao dao = new CommentDao();
		
		//评论查询函数，根据c_id返回指定评论
		public  Comment getCommentByC_Id(int c_id){
			return dao.getCommentByC_Id(c_id);
		}
		
		//2、文章评论查找函数，利用指定a_id，返回评论集合
		public  List<Comment> getAllCommentByA_id(int a_id){
			return dao.getAllCommentByA_id(a_id);
		}
		//3、分页查询，返回评论集合
		public  List<Comment> getAllCommentByA_id(int a_id,SplitPage sp){
			return dao.getAllCommentByA_id(a_id, sp);
		}
		//4、评论添加函数
		public void saveComment(Comment comment){
			dao.saveComment(comment);
		}
		//5、数据库删除函数，根据评论id删除评论
		public void deleteComment(int c_id){
			dao.deleteComment(c_id);
		}
		 //6、获取总记录数
	    public int getTotalRowsByA_id(int a_id){
	    	return dao.getTotalRowsByA_id(a_id);
	    }
}
