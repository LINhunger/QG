package com.model;

public class Comment {
	private int c_id;//评论id
	private int cu_id;//评论用户id
	private String c_content;//评论内容
	private String c_createtime;//评论时间
	private int a_id;//文章id
	private int u_id;//文章作者id
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public int getCu_id() {
		return cu_id;
	}
	public void setCu_id(int cu_id) {
		this.cu_id = cu_id;
	}
	public String getC_content() {
		return c_content;
	}
	public void setC_content(String c_content) {
		this.c_content = c_content;
	}
	public String getC_createtime() {
		return c_createtime;
	}
	public void setC_createtime(String c_createtime) {
		this.c_createtime = c_createtime;
	}
	public int getA_id() {
		return a_id;
	}
	public void setA_id(int a_id) {
		this.a_id = a_id;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	@Override
	public String toString() {
		return "Comment [c_id=" + c_id + ", cu_id=" + cu_id + ", c_content="
				+ c_content + ", c_createtime=" + c_createtime + ", a_id="
				+ a_id + ", u_id=" + u_id + "]";
	}
	
}
