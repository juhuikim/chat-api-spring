package com.nexon.domain;

public class Member {
	int userid;
	int chatroomid;
	
	public Member() {}
	
	public Member(int userid, int chatroomid) {
		this.userid = userid;
		this.chatroomid = chatroomid;
	}
	
	public int getUserid() {
		return userid;
	}
	
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public int getChatroomid() {
		return chatroomid;
	}
	
	public void setChatroomid(int chatroomid) {
		this.chatroomid = chatroomid;
	}
}
