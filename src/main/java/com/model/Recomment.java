package com.model;

public class Recomment {
	private int r_id;//回复的id
	private String r_createtime;//回复时间
	private String r_content;//回复内容
	private int publishid;//回复人id
	private int receiveid;//被回复的人的id
	private int c_id;//回复的评论id
	private String r_username;//回复者用户名
	
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public int getR_id() {
		return r_id;
	}
	public void setR_id(int r_id) {
		this.r_id = r_id;
	}
	public String getR_createtime() {
		return r_createtime;
	}
	public void setR_createtime(String r_createtime) {
		this.r_createtime = r_createtime;
	}
	public String getR_content() {
		return r_content;
	}
	public void setR_content(String r_content) {
		this.r_content = r_content;
	}
	public int getPublishid() {
		return publishid;
	}
	public void setPublishid(int publishid) {
		this.publishid = publishid;
	}
	public int getReceiveid() {
		return receiveid;
	}
	public void setReceiveid(int receiveid) {
		this.receiveid = receiveid;
	}
	
	public String getR_username() {
		return r_username;
	}
	public void setR_username(String r_username) {
		this.r_username = r_username;
	}
	@Override
	public String toString() {
		return "Recomment [r_id=" + r_id + ", r_createtime=" + r_createtime
				+ ", r_content=" + r_content + ", publishid=" + publishid
				+ ", receiveid=" + receiveid + ", c_id=" + c_id
				+ ", r_username=" + r_username + "]";
	}
	
}
