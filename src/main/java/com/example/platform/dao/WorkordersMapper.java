package com.example.platform.dao;

import com.example.platform.pojo.Workorders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface WorkordersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Workorders record);

    Workorders selectByPrimaryKey(Integer id);

    List<Workorders> selectWorkorders(Workorders workorders);

    int workordersCount(Workorders workorders);

    int updateModify(@Param("id") Integer id, @Param("orderStatus") String orderStatus, @Param("modifyContent") String modifyContent, @Param("lastTime") Date lastTime);

    int assign(Workorders workorders);

    Workorders selectOrderNumber(String orderNumber);

    int sign(Workorders workorders);

    int refuse(Workorders workorders);

    int result(Workorders workorders);

    int toClose(Workorders workorders);

    int close(Workorders workorders);

    int audit(Workorders workorders);

    List<Workorders> allWorkorders();

    List<HashMap> getCountOfType();

    List<HashMap> getOrganData();

    List<HashMap> getDayData();
}