package com.example.platform.dao;

import com.example.platform.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    int updatePassword(@Param("userCode")String userCode,@Param("idNumber")String idNumber,@Param("phone")String phone,@Param("newPassword")String newPassword);

    User selectLogin(@Param("userCode") String userCode,@Param("password") String password);
}