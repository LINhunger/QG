package com.service;

import java.util.List;

import com.dao.RecommentDao;
import com.model.Recomment;

public class RecommentService {
	
	RecommentDao dao = new RecommentDao();
	
	//回复查询函数，根据r_id返回指定回复
	public  Recomment getRecommentByR_Id(int r_id){
		return dao.getRecommentByR_Id(r_id); 
	}
	
	//2、利用指定c_id，返回回复集合
	public  List<Recomment> getAllRecommentByC_id(int c_id){
		return dao.getAllRecommentByC_id(c_id);
	}
	//3、回复添加函数
	public void saveRecomment(Recomment recomment){
		dao.saveRecomment(recomment);
	}
	//4、回复删除函数
	public void deleteRecomment(int r_id){
		dao.deleteRecomment(r_id);
	}
}
