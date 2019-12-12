package com.example.platform.dao;

import com.example.platform.pojo.Workorders;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WorkordersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Workorders record);

    Workorders selectByPrimaryKey(Integer id);

    List<Workorders> selectWorkorders (Workorders workorders);

    int workordersCount(Workorders workorders);
}