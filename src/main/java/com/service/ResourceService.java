package com.service;

import java.util.List;

import com.dao.ResourceDao;
import com.db.SplitPage;
import com.model.Resource;

public class ResourceService {
	
	ResourceDao dao = new ResourceDao();
	//根据id获取资源对象
	public  Resource  getResourceById(int id){
		return dao.getResourceById(id);
	}
	//获取全部资源
	public  List<Resource> getAllResource(){
		return dao.getAllResource();
	}
	//获取指定用户的全部资源
	public  List<Resource> getAllResourceByName(String username){
		return dao.getAllResourceByName(username);
	}
	//添加资源
	public void saveResource(Resource resource){
		dao.saveResource(resource);
	}
	//删除资源
	public void deleteResource(int id){
		dao.deleteResource(id);
	}
	//实现了分页功能的获取资源集合
	public List<Resource> getAllResource(SplitPage sp){
		return dao.getAllResource(sp);
	}
	 //获取总记录数
	public int getTotalRows(){
		return dao.getTotalRows();
	}
	
}
