package com.service;

import java.util.List;

import com.dao.InformDao;
import com.db.SplitPage;
import com.model.Inform;

public class InformService {
		InformDao dao = new InformDao();
		
		//1、根据r_id获取全部提醒消息，并分页
		public  List<Inform> getAllInformByR_id(int r_id,SplitPage sp){
			return dao.getAllInformByR_id(r_id, sp);
		}
		//2、删除全部提示消息
		public void deleteAllInform(int r_id){
			dao.deleteAllInform(r_id);
		}
		//3、根据r_id(接收用户的id)获取消息记录数，用于分页
		 public int getTotalRowsByR_id(int r_id){
			 return dao.getTotalRowsByR_id(r_id);
		 }
		 //提醒消息添加函数
			public void saveInform(Inform inform){
				dao.saveInform(inform);
			}
}
