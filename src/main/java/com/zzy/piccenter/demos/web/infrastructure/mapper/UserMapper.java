package com.zzy.piccenter.demos.web.infrastructure.mapper;


import com.zzy.piccenter.demos.web.infrastructure.po.UserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author T041018
 */
@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserPO record);

    int insertSelective(UserPO record);

    UserPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserPO record);

    int updateByPrimaryKey(UserPO record);

    UserPO selectSelective(UserPO userPO);
}