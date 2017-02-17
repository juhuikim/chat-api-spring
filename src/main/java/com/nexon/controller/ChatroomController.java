package com.nexon.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.nexon.domain.Chatroom;
import com.nexon.domain.Member;
import com.nexon.domain.Message;
import com.nexon.domain.User;
import com.nexon.excepetion.DuplicationException;
import com.nexon.excepetion.NoPermissionException;
import com.nexon.mapper.ChatroomMapper;
import com.nexon.mapper.MemberMapper;
import com.nexon.mapper.MessageMapper;

@RestController
@RequestMapping(value = "api/v1/chatrooms")
public class ChatroomController {

	@Autowired
	private ChatroomMapper chatroomMapper;
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private MessageMapper messageMapper;
	
	@RequestMapping(method=RequestMethod.POST)
	public Chatroom postChatroom(@RequestBody Chatroom chatroom) {
		
		if(chatroom.getChatroomname().length() > 100) {
			chatroom.setChatroomname(chatroom.getChatroomname().substring(0, 100));
		}
		
		int result = chatroomMapper.selectChatroomByChatroomname(chatroom.getChatroomname());
		if(result != 0) {
			throw new DuplicationException();
		}
		
		chatroomMapper.insertChatroom(chatroom);
		
		Member member = new Member();
		member.setChatroomid(chatroom.getChatroomid());
		member.setUserid(chatroom.getUserid());
		memberMapper.insertMember(member);
		
		return chatroom;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public HashMap<String, List<Chatroom>> getChatrooms() {
		List<Chatroom> chatrooms = chatroomMapper.selectAllChatroom();
		HashMap<String, List<Chatroom>> returnObj = new HashMap<String, List<Chatroom>>();
		returnObj.put("chatrooms", chatrooms);
		return returnObj;
	}
	
	@RequestMapping(value="{chatroomid}", method=RequestMethod.PUT)
	public Chatroom putChatroom(@PathVariable Integer chatroomid, @RequestBody Chatroom chatroom) {
		chatroom.setChatroomid(chatroomid);
		
		int chatroomOwner = chatroomMapper.selectChatroomowner(chatroomid);
		if(chatroomOwner != chatroom.getUserid()) {
			throw new NoPermissionException();
		}
		
		int result = chatroomMapper.selectChatroomByChatroomname(chatroom.getChatroomname());
		if(result != 0) {
			throw new DuplicationException();
		}
		
		chatroomMapper.updateChatroom(chatroom);
		return chatroom;
	}
	
	@RequestMapping(value="{chatroomid}/users", method=RequestMethod.POST)
	public Member postMember(@PathVariable Integer chatroomid, @RequestBody Member member) {
		member.setChatroomid(chatroomid);		
		memberMapper.insertMember(member);
		return member;
	}
	
	@RequestMapping(value="{chatroomid}/users/{userid}", method=RequestMethod.DELETE)
	public void deleteMember(@PathVariable Integer chatroomid, @PathVariable Integer userid) {
		Member member = new Member(userid, chatroomid);
		memberMapper.deleteMember(member);
		
		int result = memberMapper.selectMemberCount(member.getChatroomid());
		if(result == 0) {
			chatroomMapper.deleteChatroom(member.getChatroomid());
		}
	}
	
	@RequestMapping(value="{chatroomid}/users", method=RequestMethod.GET)
	public HashMap<String, List<User>> getMember(@PathVariable Integer chatroomid) {
		List<User> allUser = memberMapper.selectAllMember(chatroomid);
		HashMap<String, List<User>> retObj = new HashMap<String, List<User>>();
		retObj.put("users", allUser);
		return retObj;
	}
	
	@RequestMapping(value="{chatroomid}/messages", method=RequestMethod.POST)
	public Message postMessage(@PathVariable Integer chatroomid, @RequestBody Message message) {
		message.setChatroomid(chatroomid);
		messageMapper.insertMessage(message);
		return message;
	}
	
	@RequestMapping(value="{chatroomid}/messages", method=RequestMethod.GET)
	public HashMap<String, List<Message>> getMessages(@PathVariable Integer chatroomid, @RequestHeader int userid) {
		List<Message> messages = messageMapper.selectAllMessage(chatroomid, userid, userid);
		HashMap<String, List<Message>> returnObj = new HashMap<String, List<Message>>();
		returnObj.put("messages", messages);
		return returnObj;
	}
}
