package com.example.platform.controller;

import com.example.platform.common.Const;
import com.example.platform.common.ServerResponse;
import com.example.platform.pojo.User;
import com.example.platform.service.IUserService;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: baiyao
 * Date: 2019-10-12
 * Time: 17:00
 */
@RestController
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private IUserService iUserService;

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    public ServerResponse<User> register(User user) {
        return iUserService.register(user);
    }


    /**
     * 登录
     * @param userCode
     * @param password
     * @param session
     * @return
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    public ServerResponse login(HttpSession session,String userCode, String password) {
        ServerResponse<User> serverResponse = iUserService.login(userCode, password);
        if (serverResponse.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, serverResponse.getData());
        }
        return serverResponse;
    }
//    @RequestMapping(value = "login.do", method = RequestMethod.POST)
//    public ServerResponse login(@RequestBody Map<String,String> allMap, HttpSession session) {
//        String userCode = allMap.get("userCode");
//        String password = allMap.get("password");
//        ServerResponse<User> serverResponse = iUserService.login(userCode,password);
//        if (serverResponse.isSuccess()) {
//            session.setAttribute(Const.CURRENT_USER, serverResponse.getData());
//        }
//        return serverResponse;
//    }

    /**
     * 修改密码
     *
     * @param userCode
     * @param idNumber
     * @param phone
     * @param password
     * @return
     */
    @RequestMapping(value = "update_password.do", method = RequestMethod.POST)
    public ServerResponse<User> updatePassword(String userCode, String idNumber, String phone, String password,String oldPassword) {
        return iUserService.updatePassword(userCode, idNumber, phone, password,oldPassword);
    }

    /**
     * 退出登录
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    public ServerResponse<String> logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    /**
     * 获取登录用户信息
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "get_user_info.do", method = RequestMethod.POST)
    public ServerResponse<User> getUserInfo(HttpSession session) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user != null) {
            return ServerResponse.createBySuccess(user);
        }
        return ServerResponse.createByErrorMessage("用户未登录,无法获取当前用户的信息");
    }

    /**
     * 根据userCode查询用户信息
     *
     * @param userCode
     * @return
     */
    @RequestMapping(value = "get_userCode_info.do", method = RequestMethod.POST)
    public ServerResponse<User> getUserCodeInfo(String userCode) {
        return iUserService.getUserCodeInfo(userCode);
    }

    /**
     * 获取全部用户信息
     *
     * @return
     */
    @RequestMapping(value = "get_all_user_info.do", method = RequestMethod.POST)
    public ServerResponse getAllUserInfo(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                         @RequestParam(value = "pageSize", defaultValue = "10") int pageSize, String userName) {
        return iUserService.getAllUserInfo(pageNum, pageSize, userName);
    }

    /**
     * 获取用户总计
     *
     * @return
     */
    @RequestMapping(value = "get_count_user.do", method = RequestMethod.POST)
    public ServerResponse getCountUser(String userName) {
        return iUserService.getCountUser(userName);
    }

    /**
     * 更新姓名，性别，角色
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "update_info.do", method = RequestMethod.POST)
    public ServerResponse<User> updateInfo(User user) {
        return iUserService.updateInfo(user);
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "delete_id.do", method = RequestMethod.POST)
    public ServerResponse deleteById(int id) {
        return iUserService.deleteByPrimaryKey(id);
    }

}
