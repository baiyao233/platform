package com.example.platform.service;

import com.example.platform.common.ServerResponse;
import com.example.platform.pojo.Role;
import com.github.pagehelper.PageInfo;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: baiyao
 * Date: 2019-12-05
 * Time: 11:36
 */
public interface IRoleService {
    ServerResponse<Role> addRole(Role role);

    ServerResponse<PageInfo> getAllRole(int pageNum, int pageSize);

    ServerResponse<String> checkValid(String str, String type);

    ServerResponse delRole(int id);

    ServerResponse<Role> getRole(String roleCode);

    ServerResponse<Role> updateRole(Role role);
}
