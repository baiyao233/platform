package com.example.platform.service;

import com.example.platform.common.ServerResponse;
import com.example.platform.pojo.Workorders;
import com.github.pagehelper.PageInfo;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: baiyao
 * Date: 2019-12-02
 * Time: 14:51
 */
public interface IWorkordersService {
    ServerResponse<Workorders> insertWorkorders(Workorders workorders);

    ServerResponse<PageInfo> selectWorkorders(int pageNum, int pageSize, String userCode, Workorders workorders, String startTime, String endTime);

    ServerResponse getWorkordersById(int id);

    ServerResponse updateModify(int id, String modifyContent);

    ServerResponse assign(Workorders workorders);

    ServerResponse sign(Workorders workorders);

    ServerResponse refuse(Workorders workorders);

    ServerResponse result(Workorders workorders);

    ServerResponse toClose(Workorders workorders);

    ServerResponse close(Workorders workorders);

    ServerResponse audit(Workorders workorders);

    ServerResponse orderStatusData();

    ServerResponse organData();

    ServerResponse dayData();

    ServerResponse checkValid(String str, String type);

    ServerResponse printOrder(int id);
}
