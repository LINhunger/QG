package com.service;

import java.util.List;

import com.dao.AdminDao;
import com.db.SplitPage;
import com.model.User;

public class AdminService  extends UserService {
	
		private AdminDao adminDao = new AdminDao(); 
		//测试函数
		public static void main(String[] args) {
			UserService use=new UserService();	
			User user=use.getUserByName("bbbbbb");
			System.out.println(use.getUserByName("林寒戈林寒戈"));

			
		}
		public User getUserByName(String username){
			return adminDao.getUserByName(username);
		}
		//获取全部用户信息函数
		public List<User> getAllUser()
		{
			return adminDao.getAllUser();
		}
		//分页获取全部用户信息
		public List<User> getAllUser(SplitPage sp)
		{
			return adminDao.getAllUser(sp);
		}
		//获取总记录数
		 public int getTotalRows(){
			 return adminDao.getTotalRows();
		 }

		//管理员设置函数
			public void creatAdmin(User user){
				user.setAdmin(true);
			}
		//管理员封号函数
			public void LimitUser(int id,int limit){
				adminDao.LimitUser(id,limit);
			}
		//管理员解除封号函数
			 public void ReleaseUser(int id,int limit){
				 adminDao.ReleaseUser(id, limit);
			 }
	}