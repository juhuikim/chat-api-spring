package com.nexon.domain;

public class User {
	
	private int userid;
	private String nickname;
	
	protected User() {}
	
	public User(String nickname, int userId) {
		this.nickname = nickname;
		this.userid = userId;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public int getUserid() {
		return userid;
	}
	
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	@Override
	public String toString() {
		return String.format("User[id=%d, nickname=%s]", userid, nickname);
	}
}
