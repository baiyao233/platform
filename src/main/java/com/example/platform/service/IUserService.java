package com.example.platform.service;

import com.example.platform.common.ServerResponse;
import com.example.platform.pojo.User;
import com.github.pagehelper.PageInfo;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: baiyao
 * Date: 2019-10-12
 * Time: 17:02
 */
public interface IUserService {

    ServerResponse<User> register(User user);

    ServerResponse<User> login(String userCode, String password);

    ServerResponse<User> updatePassword(String userCode, String idNumber, String phone, String newPassword,String oldPassword);

    ServerResponse<User> getUserCodeInfo(String userCode);

    ServerResponse<PageInfo> getAllUserInfo(int pageNum, int pageSize, String userName);

    ServerResponse getCountUser(String userName);

    ServerResponse<String> checkValid(String str, String type);

    ServerResponse<User> updateInfo(User user);

    ServerResponse deleteByPrimaryKey(int id);

}
