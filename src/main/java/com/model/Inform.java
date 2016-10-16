package com.model;

public class Inform {
	private int id;//消息id
	private String p_username;//评论用户名
	private int r_id;//接收用户id
	private String createtime;//消息生时间
	private int a_id;//所在文章id
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getP_username() {
		return p_username;
	}
	public void setP_username(String p_username) {
		this.p_username = p_username;
	}

	
	public int getR_id() {
		return r_id;
	}
	public void setR_id(int r_id) {
		this.r_id = r_id;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	public int getA_id() {
		return a_id;
	}
	public void setA_id(int a_id) {
		this.a_id = a_id;
	}
	@Override
	public String toString() {
		return "Inform [id=" + id + ", p_username=" + p_username + ", r_id="
				+ r_id + ", createtime=" + createtime + ", a_id=" + a_id + "]";
	}
	
}
