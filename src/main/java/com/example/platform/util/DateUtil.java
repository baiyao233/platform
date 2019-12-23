package com.example.platform.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: baiyao
 * Date: 2019-12-13
 * Time: 15:33
 */
public class DateUtil {
    /**
     * 获取两个时间差 hh:mm:ss格式
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static String getHandlingTimeStr(Date startDate, Date endDate) {
        String retStr = "0s";
        long seconds = (endDate.getTime() - startDate.getTime()) / 1000;
        if (seconds > 0) {
            long left = 0l;
            long hour = seconds / 60 / 60;
            long minute = 0l;
            long second = 0l;
            if (hour > 0) {
                retStr = hour + "h";
                left = seconds % (60 * 60);
                if (left > 0) {
                    minute = left / 60;
                    retStr += ":" + minute + "m";
                    second = left % 60;
                    if (second > 0) {
                        retStr += ":" + second + "s";
                    }

                }
            } else {
                minute = seconds / 60;
                if (minute > 0) {
                    retStr = minute + "m";
                    second = seconds % 60;
                    if (second > 0) {
                        retStr += ":" + second + "s";
                    }
                } else {
                    retStr = seconds + "s";
                }
            }
        }
        return retStr;
    }

    /**
     *
     * @return
     */
    public static String getDateStringyyyymmdd(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String retStr = sdf.format(date);
        return retStr;
    }
}
