package com.example.platform.service.impl;

import com.example.platform.common.Const;
import com.example.platform.common.ServerResponse;
import com.example.platform.dao.BaseInfoMapper;
import com.example.platform.pojo.BaseInfo;
import com.example.platform.service.IBaseInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: baiyao
 * Date: 2019-12-19
 * Time: 9:57
 */
@Service("iBaseInfoService")
public class BaseInfoServiceImpl implements IBaseInfoService {
    @Autowired
    private BaseInfoMapper baseInfoMapper;

    /**
     * 添加变量
     *
     * @param baseInfo
     * @return
     */
    @Override
    public ServerResponse insert(BaseInfo baseInfo) {
        ServerResponse serverResponse = this.checkValid(baseInfo.getBaseType(), Const.BASEINFO_TYPE);
        if (!serverResponse.isSuccess()) {
            return serverResponse;
        }
        int resultCount = baseInfoMapper.insert(baseInfo);
        if (resultCount > 0) {
            return ServerResponse.createBySuccessMessage("添加成功");
        }
        return ServerResponse.createByErrorMessage("添加失败");
    }

    /**
     * 根据type获取变量
     *
     * @param baseType
     * @return
     */
    @Override
    @Cacheable(value = "myCache", key = "#baseType")
    public ServerResponse<BaseInfo> getByType(String baseType) {
        BaseInfo baseInfo = baseInfoMapper.getByType(baseType);
        if (baseInfo == null) {
            return ServerResponse.createByErrorMessage("未查到该变量");
        }
        return ServerResponse.createBySuccess("查询成功", baseInfo);
    }

    @Override
    public ServerResponse updateByPrimaryKey(BaseInfo baseInfo) {
        int resultCount = baseInfoMapper.updateByPrimaryKey(baseInfo);
        if (resultCount > 0) {
            return ServerResponse.createBySuccessMessage("修改成功");
        }
        return ServerResponse.createByErrorMessage("修改失败");
    }

    /**
     * 获取变量
     *
     * @param pageNum
     * @param pageSize
     * @param baseInfo
     * @return
     */
    @Override
    public ServerResponse<PageInfo> getAllBaseInfo(int pageNum, int pageSize, BaseInfo baseInfo) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo pageInfo = new PageInfo();
        List<BaseInfo> baseInfoList = baseInfoMapper.getAllBaseInfo(baseInfo);
        if (baseInfoList == null) {
            return ServerResponse.createByErrorMessage("没有数据");
        }
        int count = baseInfoMapper.getAllCount(baseInfo);
        pageInfo.setList(baseInfoList);
        return ServerResponse.createByCountSuccess("查询成功", count, pageInfo);
    }

    /**
     * 根据id删除变量
     * @param id
     * @return
     */
    @Override
    public ServerResponse delBaseInfo(int id) {
        int resultCount = baseInfoMapper.deleteByPrimaryKey(id);
        if (resultCount > 0){
            return ServerResponse.createBySuccessMessage("删除变量成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    /**
     * 校验变量
     *
     * @param str
     * @param type
     * @return
     */
    @Override
    public ServerResponse checkValid(String str, String type) {
        if (StringUtils.isNotBlank(type)) {
            if (Const.BASEINFO_TYPE.equals(type)) {
                int resultCount = baseInfoMapper.checkBaseInfoType(str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("该变量已存在");
                }
            }
        } else {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }
}
