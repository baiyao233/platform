package com.example.platform.service.impl;

import com.example.platform.common.ServerResponse;
import com.example.platform.dao.WorkordersLogMapper;
import com.example.platform.pojo.WorkordersLog;
import com.example.platform.service.IWorkordersLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: baiyao
 * Date: 2019-12-13
 * Time: 16:30
 */
@Service("iWorkordersLogService")
public class WorkodersLogServiceImpl implements IWorkordersLogService {
    @Autowired
    private WorkordersLogMapper workordersLogMapper;


    @Override
    public ServerResponse insert(WorkordersLog workordersLog) {
        int resultCount = workordersLogMapper.insert(workordersLog);
        if (resultCount > 0) {
            return ServerResponse.createBySuccessMessage("添加成功");
        }
        return ServerResponse.createByErrorMessage("添加失败");
    }

    @Override
    public ServerResponse logByWorkordersId(int workordersId) {
        List<WorkordersLog> workordersLogList = workordersLogMapper.logByWorkordersId(workordersId);
        int logCount = workordersLogMapper.logCount(workordersId);
        if (workordersLogList == null){
            return ServerResponse.createByErrorMessage("未查到工单流程节点");
        }
        return ServerResponse.createByCountSuccess("查询成功",logCount,workordersLogList);
    }


}
