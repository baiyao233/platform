package com.example.platform.controller;

import com.example.platform.common.ServerResponse;
import com.example.platform.pojo.Role;
import com.example.platform.service.IRoleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: baiyao
 * Date: 2019-12-05
 * Time: 11:50
 */
@RestController
@RequestMapping("/role/")
public class RoleController {
    @Autowired
    private IRoleService iRoleService;

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    @RequestMapping(value = "add_role.do", method = RequestMethod.POST)
    public ServerResponse<Role> addRole(Role role) {
        return iRoleService.addRole(role);
    }

    /**
     * 获取全部角色
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "get_all_role.do", method = RequestMethod.POST)
    public ServerResponse<PageInfo> getAllRole(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                               @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return iRoleService.getAllRole(pageNum, pageSize);
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "del_role.do", method = RequestMethod.POST)
    public ServerResponse delRole(int id) {
        return iRoleService.delRole(id);
    }

    /**
     * 根据roleCode查询role
     *
     * @param roleCode
     * @return
     */
    @RequestMapping(value = "get_role.do", method = RequestMethod.POST)
    public ServerResponse<Role> getRole(String roleCode) {
        return iRoleService.getRole(roleCode);
    }

    /**
     * 修改角色
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "update_role.do", method = RequestMethod.POST)
    public ServerResponse<Role> updateRole(int id,Role role) {
        return iRoleService.updateRole(role);
    }
}
