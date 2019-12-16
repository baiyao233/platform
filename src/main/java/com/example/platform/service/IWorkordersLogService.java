package com.example.platform.service;

import com.example.platform.common.ServerResponse;
import com.example.platform.pojo.WorkordersLog;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: baiyao
 * Date: 2019-12-13
 * Time: 16:30
 */
public interface IWorkordersLogService {
    ServerResponse insert(WorkordersLog workordersLog);

    ServerResponse logByWorkordersId(int workordersId);
}
