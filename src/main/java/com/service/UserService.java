package com.service;

import com.dao.UserDao;
import com.model.User;

public class UserService
{
	private UserDao userDao = new UserDao(); 
	//测试函数
	public static void main(String[] args) {
		UserService use=new UserService();	
		User user=use.getUserByName("林寒戈林寒戈");
		System.out.println(user);
		System.out.println(use.checkPassword("林寒戈林寒戈", "123123123"));
		
	}
	public  User getUserById(int id){
		return userDao.getUserById(id);
	}
	
	public User getUserByName(String username){
		return userDao.getUserByName(username);
	}

	//用户注册函数
	public void saveUser(User user)
	{
			userDao.saveUser(user);
			System.out.println("saveUser is running");
	}
	//用户名判断函数，若已存在，则返回TRUE
	public boolean checkUser(String username){
		User u =userDao.getUserByName(username);
		if(u!=null)
			return true;
		else 
			return false;
	}
	//密码判断函数，若已正确，则返回TRUE
	public boolean checkPassword(String username, String password){
		User u =userDao.getUserByName(username);
		if(u.getPassword().equals(password))
			return true;
		else 
			return false;
	}
	//邮箱格式规范函数，若已正确，则返回TRUE
	public boolean checkEmail(String email){
		String regex ="\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";
		if(email.matches(regex))
			return true;
		else 
			return false;
	}
	/*修改密码函数
	 * String username		根据用户名获取用户对象
	 * String Opassword	根据密码获取修改权限
	 * String Npassword	 新密码
	 */
	public void changePassword(String username, String Opassword,String Npassword){
		if(checkUser(username)){
			if(checkPassword(username,Opassword)){
				userDao.changUser(username,Npassword);
				System.out.println("changePassword is running!");
			}
			else
				System.out.println("Wrong password!");
		}
		else
			System.out.println("username is not exist!");
	}
	//判断注册表单格式以及用户名是否已被注册
	public boolean checkFormat(String username, String password,String email){
		if(username!=""&&password!=""
				&&!this.checkUser(username)&&this.checkEmail(email))
				return true;
		else 
			return false;
	}
	//管理员判断函数
	public boolean areAdmin(User user){
		if(user.isAdmin()){
			return true;
		}
		else{
			return false;
		}
	}
	public void savePicture(String username,String picture){
		userDao.savePicture(username, picture);
	}
}