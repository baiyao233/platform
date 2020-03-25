package com.example.platform.service.impl;

import cn.afterturn.easypoi.word.WordExportUtil;
import com.example.platform.common.Const;
import com.example.platform.common.ServerResponse;
import com.example.platform.dao.BaseInfoMapper;
import com.example.platform.dao.OrganMapper;
import com.example.platform.dao.WorkordersLogMapper;
import com.example.platform.dao.WorkordersMapper;
import com.example.platform.pojo.*;
import com.example.platform.service.IWorkordersService;
import com.example.platform.util.DateUtil;
import com.example.platform.util.OrderNo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    private OrderNo on;
    @Autowired
    private OrganMapper organMapper;

    /**
     * 创建工单
     *
     * @param workorders
     * @return
     */
    @Override
    public ServerResponse<Workorders> insertWorkorders(Workorders workorders) {
        String orderNo = "";
        orderNo = on.createOrderNo("WZ", "1");//设置工单编号
        ServerResponse serverResponse = this.checkValid(orderNo, Const.ORDER_NUMBER);
        while (!serverResponse.isSuccess()) {
            orderNo = on.createOrderNo("WZ", "1");//设置工单编号
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        workorders.setStartTime(simpleDateFormat.format(date));
        workorders.setOrderNumber(orderNo);
        workorders.setOrderStatus("0");//将工单状态改为0- 待审核
        workorders.setAppealTime(date);//设置诉求时间
        int resultCount = workordersMapper.insert(workorders);
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("创建工单失败");
        }
        workorders = workordersMapper.selectOrderNumber(orderNo);
        this.insertWorkordersLog(workorders.getId(), "工单创建", "送审核", "");
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

    /**
     * 根据id查询工单
     *
     * @param id
     * @return
     */
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
        ServerResponse serverResponse = this.insertWorkordersLog(id, "后台审核", "退回修改", modifyContent);
        if (!serverResponse.isSuccess()) {
            return serverResponse;
        }
        int resultCount = workordersMapper.updateModify(id, "5", modifyContent, new Date());
        if (resultCount > 0) {
            return ServerResponse.createBySuccessMessage("退回修改成功");
        }
        return ServerResponse.createByErrorMessage("退回修改失败");
    }

    /**
     * 交办部门
     *
     * @param workorders
     * @return
     */
    @Override
    public ServerResponse assign(Workorders workorders) {
        ServerResponse serverResponse = this.insertWorkordersLog(workorders.getId(), "后台审核", "交办部门", workorders.getResultContent());
        if (!serverResponse.isSuccess()) {
            return serverResponse;
        }
        workorders.setOrderStatus("1");
        int resultCount = workordersMapper.assign(workorders);
        if (resultCount > 0) {
            return ServerResponse.createBySuccessMessage("交办部门成功");
        }
        return ServerResponse.createByErrorMessage("交办部门失败");
    }

    /**
     * 签收
     *
     * @param workorders
     * @return
     */
    @Override
    public ServerResponse sign(Workorders workorders) {
        ServerResponse serverResponse = this.insertWorkordersLog(workorders.getId(), "部门处理", "部门签收", "");
        if (!serverResponse.isSuccess()) {
            return serverResponse;
        }
        workorders.setOrderStatus("2");
        int resultCount = workordersMapper.sign(workorders);
        if (resultCount > 0) {
            return ServerResponse.createBySuccessMessage("签收成功");
        }
        return ServerResponse.createByErrorMessage("签收失败");
    }

    /**
     * 部门拒绝
     *
     * @param workorders
     * @return
     */
    @Override
    public ServerResponse refuse(Workorders workorders) {
        ServerResponse serverResponse = this.insertWorkordersLog(workorders.getId(), "部门处理", "部门拒绝", workorders.getRefuseContent());
        if (!serverResponse.isSuccess()) {
            return serverResponse;
        }
        workorders.setOrderStatus("3");
        int resultCount = workordersMapper.refuse(workorders);
        if (resultCount > 0) {
            return ServerResponse.createBySuccessMessage("部门拒绝成功");
        }
        return ServerResponse.createByErrorMessage("部门拒绝失败");
    }

    /**
     * 部门答复
     *
     * @param workorders
     * @return
     */
    @Override
    public ServerResponse result(Workorders workorders) {
        ServerResponse serverResponse = this.insertWorkordersLog(workorders.getId(), "部门处理", "部门答复", workorders.getResultContent());
        if (!serverResponse.isSuccess()) {
            return serverResponse;
        }
        workorders.setOrderStatus("6");//部门已答复
        int resultCount = workordersMapper.result(workorders);
        if (resultCount > 0) {
            return ServerResponse.createBySuccessMessage("部门答复成功");
        }
        return ServerResponse.createByErrorMessage("部门答复失败");
    }

    /**
     * 送结案
     *
     * @param workorders
     * @return
     */
    @Override
    public ServerResponse toClose(Workorders workorders) {
        ServerResponse serverResponse = this.insertWorkordersLog(workorders.getId(), "后台审核", "送结案", "");
        if (!serverResponse.isSuccess()) {
            return serverResponse;
        }
        workorders.setOrderStatus("7");
        int resultCount = workordersMapper.toClose(workorders);
        if (resultCount > 0) {
            return ServerResponse.createBySuccessMessage("送结案成功");
        }
        return ServerResponse.createByErrorMessage("送结案失败");
    }

    @Override
    public ServerResponse close(Workorders workorders) {
        ServerResponse serverResponse = this.insertWorkordersLog(workorders.getId(), "诉求人操作", "结案", "");
        if (!serverResponse.isSuccess()) {
            return serverResponse;
        }
        workorders.setOrderStatus("4");
        int resultCount = workordersMapper.close(workorders);
        if (resultCount > 0) {
            return ServerResponse.createBySuccessMessage("结案成功");
        }
        return ServerResponse.createByErrorMessage("结案失败");
    }

    /**
     * 送审核
     *
     * @param workorders
     * @return
     */
    @Override
    public ServerResponse audit(Workorders workorders) {
        ServerResponse serverResponse = this.insertWorkordersLog(workorders.getId(), "诉求人操作", "送审核", "");
        if (!serverResponse.isSuccess()) {
            return serverResponse;
        }
        workorders.setOrderStatus("0");
        int resultCount = workordersMapper.audit(workorders);
        if (resultCount > 0) {
            return ServerResponse.createBySuccessMessage("送审核成功");
        }
        return ServerResponse.createByErrorMessage("送审核失败");
    }

    /**
     * 工单状态数据分析
     *
     * @return
     */
    @Override
    public ServerResponse orderStatusData() {
        List<HashMap> list = workordersMapper.getCountOfType();
        if (list == null) {
            return ServerResponse.createByErrorMessage("获取数据失败");
        }
        return ServerResponse.createBySuccess(list);
    }

    /**
     * 各部门数据分析
     *
     * @return
     */
    @Override
    public ServerResponse organData() {
        List<HashMap> list = workordersMapper.getOrganData();
        if (list == null) {
            return ServerResponse.createByErrorMessage("获取数据失败");
        }
        return ServerResponse.createBySuccess(list);
    }

    /**
     * 日工单总量统计
     *
     * @return
     */
    @Override
    public ServerResponse dayData() {
        List<HashMap> list = workordersMapper.getDayData();
        if (list == null) {
            return ServerResponse.createByErrorMessage("获取数据失败");
        }
        return ServerResponse.createBySuccess(list);
    }

    /**
     * 查看工单是否重复
     *
     * @param str
     * @param type
     * @return
     */
    @Override
    public ServerResponse checkValid(String str, String type) {
        if (StringUtils.isNotBlank(type)) {
            if (Const.ORDER_NUMBER.equals(type)) {
                Workorders workorders = workordersMapper.selectOrderNumber(str);
                if (workorders != null) {
                    return ServerResponse.createByErrorMessage("工单号已存在");
                }
                return ServerResponse.createBySuccessMessage("创建工单号成功");
            }
        } else {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

    /**
     * 工单打印
     * @param id
     * @return
     */
    @Override
    public ServerResponse printOrder(int id) {
        Workorders workorders = workordersMapper.selectByPrimaryKey(id);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("order_number", workorders.getOrderNumber() == null ? "" : workorders.getOrderNumber());
        map.put("phone", workorders.getPhone() == null ? "" : workorders.getPhone());
        map.put("user_name", workorders.getUserName() == null ? "" : workorders.getUserName());
        map.put("appeal_type", workorders.getAppealType() == null ? "" : workorders.getAppealType());
        map.put("email", workorders.getEmail() == null ? "" : workorders.getEmail());
        map.put("id_number", workorders.getIdNumber() == null ? "" : workorders.getIdNumber());
        map.put("organ_name", workorders.getOrganName() == null ? "" : workorders.getOrganName());
        map.put("address", workorders.getAddress() == null ? "" : workorders.getAddress());
        map.put("appeal_content", workorders.getAppealContent() == null ? "" : workorders.getAppealContent());
        try {
            XWPFDocument doc = WordExportUtil.exportWord07(
                    "D:\\IDEAproject\\platform\\src\\main\\java\\com\\example\\platform\\docx\\test.docx", map);
            FileOutputStream fos = new FileOutputStream("C:\\工单\\" + workorders.getOrderNumber() + ".docx");
            doc.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("打印失败");
        }
        return ServerResponse.createBySuccessMessage("打印成功，到C盘工单文件夹下查看");
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
        workordersLog.setOrganName(workorders.getOrganName() == null ? "" : workorders.getOrganName());
        workordersLog.setDealWay(dealWay);
        workordersLog.setDealContent(dealContent);
        workordersLog.setDealTime(date);
        workordersLog.setConsumeTime(DateUtil.getHandlingTimeStr(workorders.getLastTime(), date));
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
