package com.example.platform.service;

import com.example.platform.common.ServerResponse;
import com.example.platform.pojo.BaseInfo;
import com.github.pagehelper.PageInfo;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: baiyao
 * Date: 2019-12-19
 * Time: 9:56
 */
public interface IBaseInfoService {
    ServerResponse insert(BaseInfo baseInfo);

    ServerResponse<BaseInfo> getByType(String baseType);

    ServerResponse updateByPrimaryKey(BaseInfo baseInfo);

    ServerResponse<PageInfo> getAllBaseInfo(int pageNum, int pageSize,BaseInfo baseInfo);

    ServerResponse delBaseInfo(int id);

    ServerResponse checkValid(String str, String type);
}
