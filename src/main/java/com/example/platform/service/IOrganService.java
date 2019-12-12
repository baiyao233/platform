package com.example.platform.service;

import com.example.platform.common.OnlyDateResponse;
import com.example.platform.common.ServerResponse;
import com.example.platform.pojo.Organ;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: baiyao
 * Date: 2019-12-06
 * Time: 16:18
 */
public interface IOrganService {
    ServerResponse getAllOrgan();

    List<Organ> getListOrgan();

    ServerResponse getOrganById(int id);

    ServerResponse insertOrgan(int id, String organName, int parentId);

    ServerResponse updateOrgan(int id, String organName, int parentId);

    ServerResponse delOrgan(int id);
}
