package com.example.platform.service.impl;

import com.example.platform.common.OnlyDateResponse;
import com.example.platform.common.ServerResponse;
import com.example.platform.dao.OrganMapper;
import com.example.platform.pojo.Organ;
import com.example.platform.service.IOrganService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: baiyao
 * Date: 2019-12-06
 * Time: 16:18
 */
@Service("iOrganService")
public class OrganServiceImpl implements IOrganService {
    @Autowired
    private OrganMapper organMapper;

    /**
     * 获取全部部门,pid形式
     * @return
     */
    @Override
    public ServerResponse getAllOrgan() {
        List<Organ> organList = organMapper.getAllOrgan();
        if (organList != null) {
            return ServerResponse.createBySuccess("查询成功", organList);
        }
        return ServerResponse.createByErrorMessage("当前没有部门");
    }


    /**
     * 获取全部部门，children形式
     * @return
     */
    @Override
    public List<Organ> getListOrgan() {
        List<Organ> organList = organMapper.getAllOrgan();
        List<Organ> result = new ArrayList<>();
        for (Organ organ : organList) {
            if (organ.getPid() == 0) {
                //子节点的parentId=父节点的id
                List<Organ> organList1 = organMapper.selectByParentId(organ.getId());
                organ.setChildren(organList1);
                for (Organ second : organList1) {
                    List<Organ> secondList = organMapper.selectByParentId(second.getId());
                    second.setChildren(secondList);

                    for (Organ third : secondList) {
                        List<Organ> thirdList = organMapper.selectByParentId(third.getId());
                        third.setChildren(thirdList);

                    }
                }

                result.add(organ);
            }
        }
        return result;
    }

    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    @Override
    public ServerResponse getOrganById(int id) {
        Organ organ = organMapper.getOrganById(id);
        if (organ != null){
            return ServerResponse.createBySuccess("查询成功",organ);
        }
        return ServerResponse.createByErrorMessage("未查到部门");
    }


    /**
     * 新增部门
     * @param id
     * @param organName
     * @param parentId
     * @return
     */
    @Override
    public ServerResponse insertOrgan(int id, String organName, int parentId) {
        int resultCount = organMapper.insertOrgan(id, organName, parentId);
        if (resultCount > 0) {
            return ServerResponse.createBySuccessMessage("新增部门成功");
        }
        return ServerResponse.createByErrorMessage("新增部门失败");
    }

    /**
     * 修改部门
     * @param id
     * @param organName
     * @param parentId
     * @return
     */
    @Override
    public ServerResponse updateOrgan(int id, String organName, int parentId) {
        int resultCount = organMapper.updateOrgan(id,organName,parentId);
        if (resultCount > 0){
            return ServerResponse.createBySuccessMessage("修改部门成功");
        }
        return ServerResponse.createByErrorMessage("修改部门失败");
    }

    /**
     * 根据id删除部门
     * @param id
     * @return
     */
    @Override
    public ServerResponse delOrgan(int id) {
        int resultCount = organMapper.delOrgan(id);
        if (resultCount > 0){
            return ServerResponse.createBySuccessMessage("删除部门成功");
        }
        return ServerResponse.createByErrorMessage("删除部门失败");
    }

}
