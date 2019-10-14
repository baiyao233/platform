package com.example.platform.dao;

import com.example.platform.pojo.Workorders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WorkordersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Workorders record);

    int insertSelective(Workorders record);

    Workorders selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Workorders record);

    int updateByPrimaryKey(Workorders record);
}