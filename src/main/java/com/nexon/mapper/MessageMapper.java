package com.nexon.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.nexon.domain.Message;

@Mapper
public interface MessageMapper {
	@Insert("Insert into message values (#{messageid}, #{senderid}, #{receiverid}, #{chatroomid}, #{messagebody})")
	@Options(useGeneratedKeys=true, keyProperty="messageid")
	public void insertMessage(Message message);
	
	@Select("Select * from message where chatroomid = #{chatroomid} and (senderid = #{senderid} or receiverid = #{receiverid} or receiverid = 0)")
	public List<Message> selectAllMessage(@Param("chatroomid") Integer chatroomid, @Param("senderid") Integer senderid, @Param("receiverid") Integer receiverid);
}
