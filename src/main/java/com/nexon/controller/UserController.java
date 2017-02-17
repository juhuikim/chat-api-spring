package com.nexon.controller;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import com.nexon.domain.Chatroom;
import com.nexon.domain.User;
import com.nexon.excepetion.DuplicationException;
import com.nexon.excepetion.NotFoundException;
import com.nexon.excepetion.BadRequestException;
import com.nexon.mapper.UserMapper;

@RestController
@RequestMapping(value = "api/v1/users")
public class UserController {
	
	@Autowired
	UserMapper userMapper;
	
	@RequestMapping(method=RequestMethod.POST)
	public User postUSer(@RequestBody User user) {
		
		if(user.getNickname().length() > 20) {
			throw new BadRequestException();
		}
		
		Pattern pattern = Pattern.compile("\\W");
		Matcher matcher = pattern.matcher(user.getNickname());
		
		if(matcher.find()) {
			throw new BadRequestException();
		}
		
		int result = userMapper.selectUserByNickname(user.getNickname());		
		if(result != 0) {
			throw new DuplicationException();
		}
		
		userMapper.insertUser(user);		
		return user;
	}
	
	@RequestMapping(value="{userid}", method=RequestMethod.PUT)
	public User putUSer(@PathVariable Integer userid, @RequestBody User user) {
		
		int result = userMapper.selectUserByNickname(user.getNickname());
		if(result != 0) {
			throw new DuplicationException();
		}
		
		user.setUserid(userid);
		userMapper.updateUser(user);
		return user;
	}
	
	@RequestMapping(value="{userid}", method=RequestMethod.GET)
	public User getUser(@PathVariable Integer userid) {
		User user = userMapper.selectUserById(userid);
		
		if(user == null) {
			throw new NotFoundException();
		}
		
		return user;
	}
	
	@RequestMapping(value="{userid}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteUser(@PathVariable Integer userid) {
		userMapper.deleteUser(userid);
	}
	
	@RequestMapping(value="{userid}/chatrooms", method=RequestMethod.GET)
	public HashMap<String, List<Chatroom>> getUserChatroom(@PathVariable Integer userid) {
		List<Chatroom> joinChatrooms = userMapper.selectChatroomByUser(userid);
		HashMap<String, List<Chatroom>> retObj = new HashMap<String, List<Chatroom>>();
		retObj.put("chatrooms", joinChatrooms);
		return retObj;
	}
}