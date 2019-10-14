package com.example.platform.dao;

import com.example.platform.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkUserCode(String userCode);

    int checkEmail(String email);

    int checkIdNumber(String idNumber);

    int checkPhone(String phone);

    User selectLogin(String userCode, String password);
}