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
import com.nexon.domain.User;

@Mapper
public interface UserMapper {
	
	@Insert("Insert into user values (#{userid}, #{nickname})")
	@Options(useGeneratedKeys=true, keyProperty="userid")
	public void insertUser(User user);
	
	@Select("Select * from user where userid = #{userid}")
	public User selectUserById(@Param("userid") Integer userid);
	
	@Select("Select count(*) from user where nickname = #{nickname}")
	public int selectUserByNickname(@Param("nickname") String nickname);
	
	@Select("Select chatroom.chatroomid, chatroom.chatroomname from chatroom inner join member on member.chatroomid = chatroom.chatroomid where member.userid = #{userid}")
	public List<Chatroom> selectChatroomByUser(@Param("userid") Integer userid);
	
	@Update("Update user set nickname = #{nickname} where userid= #{userid}")
	public void updateUser(User user);
	
	@Delete("Delete from user where userid = #{userid}")
	public void deleteUser(@Param("userid") Integer userid);
}