package com.example.platform.util;

import org.springframework.core.annotation.Order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: baiyao
 * Date: 2019-11-26
 * Time: 10:44
 */
public class OrderNo {
    /**
     * 工单号规则：受理方式代码+网络标识代码+键位+日期+流水号
     * @param rex
     * @param type
     * @return
     */
    public static synchronized String createOrderNo(String rex, String type) {
        Random random = new Random();
        int tail;//流水号
        tail = random.nextInt(9999);
        if (tail < 1000) {
            tail += 1000;
        }
        String date = "";//日期
        Date date1 = new Date();
        date = date1.getYear()+1900+"";
        date += date1.getMonth()+1+"";
        date += date1.getDate();
        return rex + "222000" + type + date + tail;
    }

    public static void main(String[] args) {
        OrderNo orderNo = new OrderNo();
        String a = orderNo.createOrderNo("DH", "1");
        System.out.println(a);

    }

}
