package com.example.platform.util;

import com.example.platform.common.ServerResponse;
import com.example.platform.dao.BaseInfoMapper;
import com.example.platform.pojo.BaseInfo;
import com.example.platform.service.IBaseInfoService;
import com.example.platform.service.impl.BaseInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: baiyao
 * Date: 2019-11-26
 * Time: 10:44
 */
@Component
public class OrderNo {
    @Autowired
    private IBaseInfoService iBaseInfoService;


    public static synchronized String createOrderNoBySql(String rex, String type, int count) {
        Date date = new Date();
        String dateNo = "";//日期
        dateNo = date.getYear() + 1900 + "";
        dateNo += date.getMonth() + 1 + "";
        dateNo += date.getDate();
        count++;
        return rex + "222000" + type + dateNo + String.format("%04d", count);
    }

    /**
     * 工单号规则：受理方式代码+网络标识代码+键位+日期+流水号
     *
     * @param rex
     * @param type
     * @return
     */
    public synchronized String createOrderNo(String rex, String type) {
        String midStr = DateUtil.getDateStringyyyymmdd();
        ServerResponse serverResponse = iBaseInfoService.getByType("orderNoCreateVar");
        BaseInfo baseInfo = (BaseInfo) serverResponse.getData();
        int dbValue = Integer.parseInt(baseInfo.getBaseName());
        String tail = "";
        if (baseInfo.getBaseCode().equals(midStr)) {
            tail = String.format("%04d", dbValue + 1);
            baseInfo.setBaseName(tail);
        } else {
            tail = "0001";
            baseInfo.setBaseCode(midStr);
            baseInfo.setBaseName("1");
        }
        iBaseInfoService.updateByPrimaryKey(baseInfo);
        return rex + "222000" + type + midStr + tail;

    }

}
