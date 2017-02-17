package com.nexon.domain;

public class Chatroom {
	
	private int chatroomid;
	private String chatroomname;
	private int userid;
	
	public Chatroom() {}

	public Chatroom(String chatroomname, int  userid) {
		this.chatroomname = chatroomname;
		this.userid = userid;
	}
	
	public String getChatroomname() {
		return chatroomname;
	}
	
	public void setChatroomname(String chatroomname) {
		this.chatroomname = chatroomname;
	}
	
	public int getChatroomid() {
		return chatroomid;
	}
	
	public void setChatroomid(int chatroomid) {
		this.chatroomid = chatroomid;
	}
	
	public int getUserid() {
		return userid;		
	}
	
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	@Override
	public String toString() {
		return String.format("Chatroom[chatroomId=%d, chatroomName=%s, chatroomOwnerId=%d]",
				chatroomid, chatroomname, userid);
	}
}
