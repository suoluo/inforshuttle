package com.inforshuttle.dao;

import com.inforshuttle.entity.LyUser;

public interface LyUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LyUser record);

    int insertSelective(LyUser record);

    LyUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LyUser record);

    int updateByPrimaryKey(LyUser record);
}