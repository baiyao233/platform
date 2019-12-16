package com.example.platform.service.impl;

import com.example.platform.common.Const;
import com.example.platform.common.ServerResponse;
import com.example.platform.dao.WorkordersLogMapper;
import com.example.platform.dao.WorkordersMapper;
import com.example.platform.pojo.User;
import com.example.platform.pojo.Workorders;
import com.example.platform.pojo.WorkordersLog;
import com.example.platform.service.IWorkordersService;
import com.example.platform.util.DateUtil;
import com.example.platform.util.OrderNo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: baiyao
 * Date: 2019-12-02
 * Time: 14:53
 */
@Service("iWorkodersService")
public class WorkodersServiceImpl implements IWorkordersService {
    @Autowired
    private HttpSession session;
    @Autowired
    private WorkordersMapper workordersMapper;
    @Autowired
    private WorkordersLogMapper workordersLogMapper;

    /**
     * 创建工单
     *
     * @param workorders
     * @return
     */
    @Override
    public ServerResponse<Workorders> insertWorkorders(Workorders workorders) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        workorders.setOrderNumber(OrderNo.createOrderNo("Web", "1"));//创建工单流水号
        workorders.setOrderStatus("0");//将工单状态改为0- 待审核
        workorders.setAppealTime(date);//设置诉求时间
        cal.add(Calendar.DAY_OF_MONTH, 1);
        workorders.setResultTime(cal.getTime());//设置答复时限
        workorders.setRefuseTime(cal.getTime());//设置拒绝时限
        int resultCount = workordersMapper.insert(workorders);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("创建工单失败");
        }
        return ServerResponse.createBySuccess("创建成功", workorders);
    }

    /**
     * 获取工单
     *
     * @param pageNum
     * @param pageSize
     * @param userCode
     * @param workorders
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public ServerResponse<PageInfo> selectWorkorders(int pageNum, int pageSize, String userCode, Workorders workorders, String startTime, String endTime) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo pageInfo = new PageInfo();
        workorders.setStartTime(startTime == null ? "" : startTime);
        workorders.setEndTime(endTime == null ? "" : endTime);
        List<Workorders> workordersList = workordersMapper.selectWorkorders(workorders);

        pageInfo.setList(workordersList);
        int workordersCount = workordersMapper.workordersCount(workorders);
        if (workordersList != null) {
            return ServerResponse.createByCountSuccess("查询成功", workordersCount, pageInfo);
        } else {
            return ServerResponse.createByErrorMessage("未查询到");
        }
    }

    @Override
    public ServerResponse getWorkordersById(int id) {
        Workorders workorders = workordersMapper.selectByPrimaryKey(id);
        if (workorders != null) {
            return ServerResponse.createBySuccess("查询成功", workorders);
        }
        return ServerResponse.createByErrorMessage("该条工单不存在");
    }

    /**
     * 退回修改
     *
     * @param id
     * @param modifyContent
     * @return
     */
    @Override
    public ServerResponse updateModify(int id, String modifyContent) {
        int resultCount = workordersMapper.updateModify(id, "5", modifyContent, new Date());
        if (resultCount > 0) {
            ServerResponse serverResponse = this.insertWorkordersLog(id, "后台审核", "退回修改", modifyContent);
            if (!serverResponse.isSuccess()) {
                return serverResponse;
            }
            return ServerResponse.createBySuccessMessage("退回修改成功");
        }
        return ServerResponse.createByErrorMessage("退回修改失败");
    }

    @Override
    public ServerResponse assign(Workorders workorders) {
        workorders.setOrderStatus("1");
        int resultCount = workordersMapper.assign(workorders);
        if (resultCount > 0){
            ServerResponse serverResponse = this.insertWorkordersLog(workorders.getId(),"后台审核","交办部门",workorders.getResultContent());
            if (!serverResponse.isSuccess()){
                return serverResponse;
            }
            return ServerResponse.createBySuccessMessage("交办部门成功");
        }
        return ServerResponse.createByErrorMessage("交办部门失败");
    }

    /**
     * 添加工单流程节点
     *
     * @param workordersId
     * @param node
     * @param dealWay
     * @param dealContent
     * @return
     */
    public ServerResponse insertWorkordersLog(int workordersId, String node, String dealWay, String dealContent) {
        WorkordersLog workordersLog = new WorkordersLog();
        Date date = new Date();
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        Workorders workorders = workordersMapper.selectByPrimaryKey(workordersId);
        workordersLog.setNode(node);
        workordersLog.setOrganName(workorders.getOrganName());
        workordersLog.setDealWay(dealWay);
        workordersLog.setDealContent(dealContent);
        workordersLog.setDealTime(date);
        workordersLog.setConsumeTime(DateUtil.getHandlingTimeStr(workorders.getAppealTime(), date));
        workordersLog.setWorkordersId(workorders.getId());
        workordersLog.setOrderNumber(workorders.getOrderNumber());
        workordersLog.setDealUser(user.getUserCode());
        int resultCount = workordersLogMapper.insert(workordersLog);
        if (resultCount > 0) {
            return ServerResponse.createBySuccessMessage("工单历史新增成功");
        }
        return ServerResponse.createByErrorMessage("工单历史新增失败");
    }
}
