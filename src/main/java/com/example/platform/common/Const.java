package com.example.platform.common;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: baiyao
 * Date: 2019-09-29
 * Time: 15:51
 */
public class Const {
    public static final String CURRENT_USER = "currentUser";
    public static final String USER_CODE = "userCode";
    public static final String EMAIL = "email";
    public static final String ID_NUMBER = "idNumber";
    public static final String PHONE = "phone";
    public static final String PASSWORD = "password";
    public static final String ROLE_CODE = "roleCode";
    public static final String ORDER_NUMBER = "orderNumber";
    public static final String BASEINFO_TYPE = "baseInfo_type";

    public interface Role {
        /**
         * 普通用户
         */
        int ROLE_CUSTOMER = 0;
        /**
         * 管理员
         */
        int ROLE_ADMIN = 1;
        /**
         * 后台组长
         */
        int ROLE_GROUP_LEADER = 2;
        /**
         * 二级部门
         */
        int ROLE_SECOND = 3;
        /**
         * 三级部门
         */
        int ROLE_THIRD = 4;
    }

    public interface OrderStatus {
        /**
         * 放弃
         */
        int STATUS_GIVE_UP = 0;
        /**
         * 待审核
         */
        int STATUS_AUDIT = 1;
        /**
         * 待签收
         */
        int STATUS_SIGN = 2;
        /**
         * 处理中
         */
        int STATUS_DEAL = 3;
        /**
         * 拒绝
         */
        int STATUS_REFUSE = 4;
        /**
         * 已结案
         */
        int STATUS_END = 5;
        /**
         * 退回修改
         */
        int STATUS_MODIFY = 6;
    }


}
