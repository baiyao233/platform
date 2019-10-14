package com.example.platform.controller;

import com.example.platform.common.Const;
import com.example.platform.common.ServerResponse;
import com.example.platform.pojo.User;
import com.example.platform.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: baiyao
 * Date: 2019-10-12
 * Time: 17:00
 */
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private IUserService iUserService;

    /**
     * 注册
     * @param user
     * @return
     */
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    public ServerResponse<User> register(User user) {
        return iUserService.register(user);
    }

    /**
     * 登录
     * @param session
     * @param userCode
     * @param password
     * @return
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    public ServerResponse login(HttpSession session, String userCode, String password) {
        ServerResponse<User> serverResponse = iUserService.login(userCode,password);
        if (serverResponse.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,serverResponse.getData());
        }
        return serverResponse;
    }

    public ServerResponse<User> updatePassword(User user){
        return null;
    }
}
