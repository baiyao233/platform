package com.example.platform.service;

import com.example.platform.common.ServerResponse;
import com.example.platform.pojo.User;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: baiyao
 * Date: 2019-10-12
 * Time: 17:02
 */
public interface IUserService {

    ServerResponse<User> register(User user);

    ServerResponse<User> login(String userCode,String password);

    ServerResponse<User> updatePassword(String userCode,String idNumber,String phone,String newPassword);

    ServerResponse<String> checkValid(String str,String type);

}
