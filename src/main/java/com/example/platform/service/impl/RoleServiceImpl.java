package com.example.platform.service.impl;

import com.example.platform.common.Const;
import com.example.platform.common.ServerResponse;
import com.example.platform.dao.RoleMapper;
import com.example.platform.pojo.Role;
import com.example.platform.service.IRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: baiyao
 * Date: 2019-12-05
 * Time: 11:36
 */
@Service("iRoleService")
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    @Override
    public ServerResponse<Role> addRole(Role role) {
        ServerResponse serverResponse = this.checkValid(role.getRoleCode(), Const.ROLE_CODE);
        if (!serverResponse.isSuccess()) {
            return serverResponse;
        }
        int resultCount = roleMapper.insert(role);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("新增失败");
        }
        return ServerResponse.createBySuccess("新增成功", role);

    }

    /**
     * 获取全部角色
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ServerResponse<PageInfo> getAllRole(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo pageInfo = new PageInfo();
        List<Role> roleList = roleMapper.getAllRole();
        pageInfo.setList(roleList);
        int resultCount = roleMapper.allRoleCount();
        return ServerResponse.createByCountSuccess("查询成功",resultCount,pageInfo);
    }

    @Override
    public ServerResponse<String> checkValid(String str, String type) {
        if (StringUtils.isNotBlank(type)) {
            //校验是否存在role_code
            if (Const.ROLE_CODE.equals(type)) {
                int resultCount = roleMapper.checkRoleCode(str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("角色代码已存在");
                }
            }
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @Override
    public ServerResponse delRole(int id) {
        int resultCount = roleMapper.deleteByPrimaryKey(id);
        if (resultCount >0){
            return ServerResponse.createBySuccessMessage("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    /**
     * 根据roleCode查询role
     * @param roleCode
     * @return
     */
    @Override
    public ServerResponse<Role> getRole(String roleCode) {
        Role role = roleMapper.getRole(roleCode);
        if (role != null){
            return ServerResponse.createBySuccess("查询成功",role);
        }
        return ServerResponse.createByErrorMessage("未查到该角色");
    }

    /**
     * 修改角色
     * @param role
     * @return
     */
    @Override
    public ServerResponse<Role> updateRole(Role role) {
        int resultCount = roleMapper.updateByPrimaryKeySelective(role);
        if (resultCount > 0){
            return ServerResponse.createBySuccessMessage("修改成功");
        }
        return ServerResponse.createByErrorMessage("修改失败");
    }


}
