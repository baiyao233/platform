package com.example.platform.dao;

import com.example.platform.pojo.BaseInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BaseInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseInfo record);

    int insertSelective(BaseInfo record);

    BaseInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseInfo record);

    int updateByPrimaryKey(BaseInfo record);

    BaseInfo getByType(String baseType);

    int checkBaseInfoTypeAndCode(@Param("baseType") String baseType, @Param("baseCode") String baseCode);

    List<BaseInfo> getAllBaseInfo(BaseInfo baseInfo);

    int getAllCount(BaseInfo baseInfo);
}