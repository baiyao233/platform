package com.example.platform.dao;

import com.example.platform.pojo.Role;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> getAllRole();

    int checkRoleCode(String roleCode);

    int allRoleCount();

    Role getRole(String roleCode);

    int updateRole(@Param("id") Integer id, @Param("role") Role role);
}