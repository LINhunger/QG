package com.model;

import java.sql.Timestamp;

public class Resource {
	private int id;
	private String uuidname;  //上传文件的名称，文件的uuid名
	private String realname; //上传文件的真实名称
	private String savepath;     //记住文件的位置
	private Timestamp uploadtime;     //文件的上传时间
	private String description;  //文件的描述
	private String username;//上传的用户名
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUuidname() {
		return uuidname;
	}
	public void setUuidname(String uuidname) {
		this.uuidname = uuidname;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getSavepath() {
		return savepath;
	}
	public void setSavepath(String savepath) {
		this.savepath = savepath;
	}
	public Timestamp getUploadtime() {
		return uploadtime;
	}
	public void setUploadtime(Timestamp uploadtime) {
		this.uploadtime = uploadtime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Resource [id=" + id + ", uuidname=" + uuidname + ", realname="
				+ realname + ", savepath=" + savepath + ", uploadtime="
				+ uploadtime + ", description=" + description + ", username="
				+ username + "]";
	}
}
