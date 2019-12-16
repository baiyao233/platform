package com.example.platform.dao;

import com.example.platform.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int checkUserCode(String userCode);

    int checkEmail(String email);

    int checkIdNumber(String idNumber);

    int checkPhone(String phone);

    int updatePassword(@Param("userCode") String userCode, @Param("idNumber") String idNumber, @Param("phone") String phone, @Param("newPassword") String newPassword);

    User selectLogin(@Param("userCode") String userCode, @Param("password") String password);

    User getUserCodeInfo(@Param("userCode") String userCode);

    List<User> getAllUserInfo(@Param("userName") String userName);

    int getCountUser(@Param("userName") String userName);

    int updateInfo(User user);
}