package com.yhf.domain;

public class User {
	private Integer classId;
	private Integer id;
	private String name;
	private String password;
	private String sex="男";
	private String iphone;
	private String xinzuo;
	private String birthday;
	private String liuyan;
	
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIphone() {
		return iphone;
	}
	public void setIphone(String iphone) {
		this.iphone = iphone;
	}
	public String getXinzuo() {
		return xinzuo;
	}
	public void setXinzuo(String xinzuo) {
		this.xinzuo = xinzuo;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getLiuyan() {
		return liuyan;
	}
	public void setLiuyan(String liuyan) {
		this.liuyan = liuyan;
	}
	@Override
	public String toString() {
		return "User [ id=" + id + ",classId=" + classId + ", name=" + name + ", password=" + password + ", sex=" + sex
				+ ", iphone=" + iphone + ", xinzuo=" + xinzuo + ", Birthday=" + birthday + ", liuyan=" + liuyan + "]";
	}
	
	
}
