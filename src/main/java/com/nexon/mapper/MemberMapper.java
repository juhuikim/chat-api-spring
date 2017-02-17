package com.nexon.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.nexon.domain.Member;
import com.nexon.domain.User;

@Mapper
public interface MemberMapper {
	@Insert("Insert into member values (#{userid}, #{chatroomid})")
	public void insertMember(Member member);
	
	@Delete("Delete from member where userid = #{userid} and chatroomid = #{chatroomid}")
	public void deleteMember(Member member);
	
	@Select("Select count(*) from member where chatroomid = #{chatroomid}")
	public int selectMemberCount(@Param("chatroomid") Integer chatroomid);
	
	@Select("Select user.userid, user.nickname from user inner join member on member.userid = user.userid where member.chatroomid = #{chatroomid}")
	public List<User> selectAllMember(@Param("chatroomid") Integer chatroomid);
}
