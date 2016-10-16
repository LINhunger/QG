package com.service;

import java.util.List;

import com.dao.FavoriteDao;
import com.db.SplitPage;
import com.model.Favorite;

public class FavoriteService {
	
	FavoriteDao dao = new FavoriteDao();
	//1、根据u_id获取全部收藏信息，并分页
	public  List<Favorite> getAllFavoriteByU_id(int u_id,SplitPage sp){
		return dao.getAllFavoriteByU_id(u_id, sp);
	}
	//2、删除全部收藏消息
	public void deleteAllFavorite(int u_id){
		dao.deleteAllFavorite(u_id);
	}
	//3、根据u_id(接收用户的id)获取收藏记录数，用于分页
    public int getTotalRowsByU_id(int u_id){
    	return dao.getTotalRowsByU_id(u_id);
    }
    //4、收藏消息插入方法
	public void saveFavorite(Favorite favorite){
		dao.saveFavorite(favorite);
	}
	//5、根据收藏id删除个别收藏消息
	public void deleteFavorite(int id){
		dao.deleteFavorite(id);
	}
}
