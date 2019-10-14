package com.example.platform.service.impl;

import com.example.platform.common.Const;
import com.example.platform.common.ServerResponse;
import com.example.platform.dao.UserMapper;
import com.example.platform.pojo.User;
import com.example.platform.service.IUserService;
import com.example.platform.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: baiyao
 * Date: 2019-10-12
 * Time: 17:02
 */
@Service("iUserService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @Override
    public ServerResponse<User> register(User user) {
        //校验账号
        ServerResponse serverResponse = this.checkValid(user.getUserCode(), Const.USER_CODE);
        if (!serverResponse.isSuccess()) {
            return serverResponse;
        }
        //校验邮箱
        serverResponse = this.checkValid(user.getEmail(), Const.EMAIL);
        if (!serverResponse.isSuccess()) {
            return serverResponse;
        }
        //校验身份证
        serverResponse = this.checkValid(user.getIdNumber(), Const.ID_NUMBER);
        if (!serverResponse.isSuccess()) {
            return serverResponse;
        }
        //校验手机号码
        serverResponse = this.checkValid(user.getPhone(), Const.PHONE);
        if (!serverResponse.isSuccess()) {
            return serverResponse;
        }
        user.setRole(Const.Role.ROLE_CUSTOMER);
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        int resultCount = userMapper.insert(user);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    /**
     * 登录
     *
     * @param userCode
     * @param password
     * @return
     */
    @Override
    public ServerResponse<User> login(String userCode, String password) {
        int resultCount = userMapper.checkUserCode(userCode);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("账号错误");
        }
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectLogin(userCode, md5Password);
        if (user == null) {
            return ServerResponse.createByErrorMessage("密码错误");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登陆成功", user);

    }

    /**
     * 修改密码
     * @param user
     * @return
     */
    @Override
    public ServerResponse<User> updatePassword(User user) {
        return null;
    }


    /**
     * 校验
     *
     * @param str
     * @param type
     * @return
     */
    @Override
    public ServerResponse<String> checkValid(String str, String type) {
        if (StringUtils.isNotBlank(type)) {
            //开始校验
            //验证账号
            if (Const.USER_CODE.equals(type)) {
                int resultCount = userMapper.checkUserCode(str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("账号已被注册");
                }
            }
            //验证邮箱
            if (Const.EMAIL.equals(type)) {
                int resultCount = userMapper.checkEmail(str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("email已被注册");
                }
            }
            //验证身份证
            if (Const.ID_NUMBER.equals(type)) {
                int resultCount = userMapper.checkIdNumber(str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("该身份证已被注册");
                }
            }
            //校验手机号码
            if (Const.PHONE.equals(type)) {
                int resultCount = userMapper.checkPhone(str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("该手机号已被注册");
                }
            }
        } else {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }


}
