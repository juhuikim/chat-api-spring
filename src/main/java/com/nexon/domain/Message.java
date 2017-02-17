package com.nexon.domain;

public class Message {
	private int senderid;
	private int receiverid;
	private int chatroomid;
	private int messageid;
	private String messagebody;	
	
	public Message() {}
	
	public Message(int senderid, int receiverid, int chatroomid, int messageid, String messagebody) {
		this.senderid = senderid;
		this.receiverid = receiverid;
		this.chatroomid = chatroomid;
		this.messageid = messageid;
		this.messagebody = messagebody;
	}
	
	public int getSenderid() {
		return senderid;
	}
	
	public void setSenderid(int senderid) {
		this.senderid = senderid;
	}
	
	public int getReceiverid() {
		return receiverid;
	}
	
	public void setReceiverid(int receiverid) {
		this.receiverid = receiverid;
	}
	
	public int getChatroomid() {
		return chatroomid;
	}
	
	public void setChatroomid(int chatroomid) {
		this.chatroomid = chatroomid;
	}
	
	public int getMessageid() {
		return messageid;
	}
	
	public void setMessageid(int messageid) {
		this.messageid = messageid;
	}
	
	public String getMessagebody() {
		return messagebody;
	}
	
	public void setMessagebody(String messagebody) {
		this.messagebody = messagebody;
	}
}
