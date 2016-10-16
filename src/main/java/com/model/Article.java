package com.model;

public class Article {
	private int a_id;//文章id
	private String a_title;//文章标题
	private String a_type;//文章类别
	private String a_createtime;//发布时间
	private String a_content;//文章内容
	
	
	public String getA_content() {
		return a_content;
	}
	public void setA_content(String a_content) {
		this.a_content = a_content;
	}
	private int u_id;//作者id
	private String username;//作者用户名
	private String status;//文章状态：发布，草稿，被禁
	private String inform;//文章通知信息
	
	public int getA_id() {
		return a_id;
	}
	public void setA_id(int a_id) {
		this.a_id = a_id;
	}
	public String getA_title() {
		return a_title;
	}
	public void setA_title(String a_title) {
		this.a_title = a_title;
	}
	public String getA_type() {
		return a_type;
	}
	public void setA_type(String a_type) {
		this.a_type = a_type;
	}
	public String getA_createtime() {
		return a_createtime;
	}
	public void setA_createtime(String a_createtime) {
		this.a_createtime = a_createtime;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getInform() {
		return inform;
	}
	public void setInform(String inform) {
		this.inform = inform;
	}
	@Override
	public String toString() {
		return "Article [a_id=" + a_id + ", a_title=" + a_title + ", a_type="
				+ a_type + ", a_createtime=" + a_createtime + ", a_content="
				+ a_content + ", u_id=" + u_id + ", username=" + username
				+ ", status=" + status + ", inform=" + inform + "]\n";
	}
	
}
