package com.metanet.main;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	
	void insert(UserVO vo);
}
