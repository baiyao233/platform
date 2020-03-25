package com.example.platform.dao;

import com.example.platform.pojo.Organ;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrganMapper {

    List<Organ> getAllOrgan();

    List<Organ> selectByParentId(Integer id);

    int updateOrgan(@Param("id") Integer id, @Param("organName") String organName, @Param("parentId") Integer parentId);

    int insertOrgan(@Param("id") Integer id, @Param("organName") String organName, @Param("parentId") Integer parentId);

    Organ getOrganById(Integer id);

    int delOrgan(Integer id);
}