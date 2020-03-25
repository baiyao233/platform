package com.example.platform.controller;

import cn.afterturn.easypoi.word.WordExportUtil;
import com.example.platform.common.Const;
import com.example.platform.common.ServerResponse;
import com.example.platform.pojo.User;
import com.example.platform.pojo.Workorders;
import com.example.platform.service.IBaseInfoService;
import com.example.platform.service.IWorkordersService;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: baiyao
 * Date: 2019-12-02
 * Time: 14:58
 */

@RestController
@RequestMapping("/workorders/")
public class WorkordersController {
    @Autowired
    private IWorkordersService iWorkordersService;
    @Autowired
    private IBaseInfoService iBaseInfoService;

    /**
     * 创建工单
     *
     * @param session
     * @param workorders
     * @return
     */
    @RequestMapping(value = "insert_workorders.do", method = RequestMethod.POST)
    public ServerResponse<Workorders> insertWorkorders(HttpSession session, Workorders workorders) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorMessage("用户未登录");
        }
        return iWorkordersService.insertWorkorders(workorders);
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
    @RequestMapping(value = "select_workorders.do", method = RequestMethod.POST)
    public ServerResponse selectWorkoders(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                          @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                          String userCode, Workorders workorders, String startTime, String endTime) {
        return iWorkordersService.selectWorkorders(pageNum, pageSize, userCode, workorders, startTime, endTime);
    }

    /**
     * 根据id查找工单
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "get_workorders_by_id.do", method = RequestMethod.POST)
    public ServerResponse getWorkordersById(int id) {
        return iWorkordersService.getWorkordersById(id);
    }

    /**
     * 退回修改
     *
     * @param id
     * @param modifyContent
     * @return
     */
    @RequestMapping(value = "update_modify.do", method = RequestMethod.POST)
    public ServerResponse updateModify(int id, String modifyContent) {
        return iWorkordersService.updateModify(id, modifyContent);
    }

    /**
     * 交办部门
     *
     * @param workorders
     * @return
     */
    @RequestMapping(value = "assign.do", method = RequestMethod.POST)
    public ServerResponse assign(Workorders workorders) {
        return iWorkordersService.assign(workorders);
    }

    /**
     * 部门签收
     *
     * @param workorders
     * @return
     */
    @RequestMapping(value = "sign.do", method = RequestMethod.POST)
    public ServerResponse sign(Workorders workorders) {
        return iWorkordersService.sign(workorders);
    }

    /**
     * 部门拒绝
     *
     * @param workorders
     * @return
     */
    @RequestMapping(value = "refuse.do", method = RequestMethod.POST)
    public ServerResponse refuse(Workorders workorders) {
        return iWorkordersService.refuse(workorders);
    }

    /**
     * 部门答复
     *
     * @param workorders
     * @return
     */
    @RequestMapping(value = "result.do", method = RequestMethod.POST)
    public ServerResponse result(Workorders workorders) {
        return iWorkordersService.result(workorders);
    }

    /**
     * 送结案
     *
     * @param workorders
     * @return
     */
    @RequestMapping(value = "to_close.do", method = RequestMethod.POST)
    public ServerResponse toClose(Workorders workorders) {
        return iWorkordersService.toClose(workorders);
    }

    /**
     * 结案
     *
     * @param workorders
     * @return
     */
    @RequestMapping(value = "close.do", method = RequestMethod.POST)
    public ServerResponse close(Workorders workorders) {
        return iWorkordersService.close(workorders);
    }

    /**
     * 送审核
     *
     * @param workorders
     * @return
     */
    @RequestMapping(value = "audit.do", method = RequestMethod.POST)
    public ServerResponse audit(Workorders workorders) {
        return iWorkordersService.audit(workorders);
    }

    /**
     * 工单状态分析
     *
     * @return
     */
    @RequestMapping(value = "order_status_data.do", method = RequestMethod.POST)
    public ServerResponse orderStatusData() {
        return iWorkordersService.orderStatusData();
    }

    /**
     * 各部门办件数统计
     *
     * @return
     */
    @RequestMapping(value = "organ_data.do", method = RequestMethod.POST)
    public ServerResponse organData() {
        return iWorkordersService.organData();
    }

    /**
     * 日工单总量统计
     *
     * @return
     */
    @RequestMapping(value = "day_data.do", method = RequestMethod.POST)
    public ServerResponse dayData() {
        return iWorkordersService.dayData();
    }

    /**
     * 打印工单
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "print_order.do", method = RequestMethod.POST)
    public ServerResponse printOrder(int id) {

        return iWorkordersService.printOrder(id);
    }
}
