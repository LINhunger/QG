package com.model;

public class Favorite {
	private int id;//收藏id
	private int u_id;//用户id
	private int a_id;//文章id
	private String a_title;//文章标题
	public String getA_title() {
		return a_title;
	}
	public void setA_title(String a_title) {
		this.a_title = a_title;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public int getA_id() {
		return a_id;
	}
	public void setA_id(int a_id) {
		this.a_id = a_id;
	}
	@Override
	public String toString() {
		return "Favorite [id=" + id + ", u_id=" + u_id + ", a_id=" + a_id
				+ ", a_title=" + a_title + "]";
	}
	
}
