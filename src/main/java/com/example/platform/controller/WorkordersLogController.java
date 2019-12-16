package com.example.platform.controller;

import com.example.platform.common.ServerResponse;
import com.example.platform.pojo.WorkordersLog;
import com.example.platform.service.IWorkordersLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: baiyao
 * Date: 2019-12-13
 * Time: 16:33
 */
@RestController
@RequestMapping("/log/")
public class WorkordersLogController {
    @Autowired
    private IWorkordersLogService iWorkordersLogService;

    @RequestMapping(value = "insert.do", method = RequestMethod.POST)
    public ServerResponse insert(WorkordersLog workordersLog) {
        return iWorkordersLogService.insert(workordersLog);
    }

    /**
     * 根据工单号查询工单流程节点
     * @param workordersId
     * @return
     */
    @RequestMapping(value = "log_by_workorders_id.do", method = RequestMethod.POST)
    public ServerResponse logByWorkordersId(int workordersId) {
        return iWorkordersLogService.logByWorkordersId(workordersId);
    }
}
