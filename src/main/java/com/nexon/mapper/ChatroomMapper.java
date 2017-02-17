package com.nexon.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.nexon.domain.Chatroom;

@Mapper
public interface ChatroomMapper {
	@Insert("Insert into chatroom values (#{chatroomid}, #{chatroomname}, #{userid})")
	@Options(useGeneratedKeys=true, keyProperty="chatroomid")
	public void insertChatroom(Chatroom chatroom);
		
	@Select("Select count(*) from chatroom where chatroomname = #{chatroomname}")
	public int selectChatroomByChatroomname(@Param("chatroomname") String chatroomname);
	
	@Select("Select * from chatroom")
	public List<Chatroom> selectAllChatroom();
	
	@Select("Select userid from chatroom where chatroomid = #{chatroomid}")
	public int selectChatroomowner(@Param("chatroomid") Integer chatroomid);
	
	@Update("Update chatroom set chatroomname = #{chatroomname} where chatroomid= #{chatroomid}")
	public void updateChatroom(Chatroom chatroom);
	
	@Delete("Delete from chatroom where chatroomid = #{chatroomid}")
	public void deleteChatroom(@Param("chatroomid") Integer chatroomid);
}
