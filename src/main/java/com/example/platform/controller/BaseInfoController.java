package com.example.platform.controller;

import com.example.platform.common.ServerResponse;
import com.example.platform.pojo.BaseInfo;
import com.example.platform.service.IBaseInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: baiyao
 * Date: 2019-12-19
 * Time: 10:28
 */
@RestController
@RequestMapping("/baseInfo/")
public class BaseInfoController {
    @Autowired
    private IBaseInfoService iBaseInfoService;

    /**
     * 根据type获取变量
     *
     * @param baseType
     * @return
     */
    @RequestMapping(value = "get_by_type.do", method = RequestMethod.POST)
    public ServerResponse getByType(String baseType) {
        return iBaseInfoService.getByType(baseType);
    }

    /**
     * 插入变量
     *
     * @param baseInfo
     * @return
     */
    @RequestMapping(value = "insert.do", method = RequestMethod.POST)
    public ServerResponse insert(BaseInfo baseInfo) {
        return iBaseInfoService.insert(baseInfo);
    }

    /**
     * 获取变量
     *
     * @param pageNum
     * @param pageSize
     * @param baseInfo
     * @return
     */
    @RequestMapping(value = "get_all.do", method = RequestMethod.POST)
    public ServerResponse<PageInfo> getAllBaseInfo(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                                   @RequestParam(value = "pageSize", defaultValue = "10000") int pageSize,
                                                   BaseInfo baseInfo) {
        return iBaseInfoService.getAllBaseInfo(pageNum, pageSize, baseInfo);
    }


    /**
     * 根据id删除变量
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "del.do", method = RequestMethod.POST)
    public ServerResponse delBaseInfo(int id) {
        return iBaseInfoService.delBaseInfo(id);
    }

    /**
     * 修改变量
     * @param baseInfo
     * @return
     */
    @RequestMapping(value = "update.do", method = RequestMethod.POST)
    public ServerResponse update(BaseInfo baseInfo) {
        return iBaseInfoService.updateByPrimaryKey(baseInfo);
    }

}
