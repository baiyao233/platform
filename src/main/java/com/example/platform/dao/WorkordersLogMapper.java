package com.example.platform.dao;

import com.example.platform.pojo.WorkordersLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WorkordersLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WorkordersLog record);

    int insertSelective(WorkordersLog record);

    WorkordersLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WorkordersLog record);

    int updateByPrimaryKey(WorkordersLog record);

    List<WorkordersLog> logByWorkordersId(Integer workordersId);

    int logCount(Integer workordersId);
}