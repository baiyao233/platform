package com.example.platform.controller;

import com.example.platform.common.OnlyDateResponse;
import com.example.platform.common.ServerResponse;
import com.example.platform.pojo.Organ;
import com.example.platform.service.IOrganService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: baiyao
 * Date: 2019-12-06
 * Time: 16:23
 */
@RestController
@RequestMapping("/organ/")
public class OrganController {
    @Autowired
    private IOrganService iOrganService;

    /**
     * 查询全部部门
     *
     * @return
     */
    @RequestMapping(value = "get_all_organ.do", method = RequestMethod.POST)
    public ServerResponse getAllOrgan() {
        return iOrganService.getAllOrgan();
    }

    /**
     * 获取部门list
     *
     * @return
     */
    @RequestMapping(value = "get_list_organ.do", method = RequestMethod.POST)
    public List<Organ> getListOrgan() {
        return iOrganService.getListOrgan();
    }

    /**
     * 新增部门
     *
     * @param id
     * @param organName
     * @param parentId
     * @return
     */
    @RequestMapping(value = "insert_organ.do", method = RequestMethod.POST)
    public ServerResponse insertOrgan(int id, String organName, int parentId) {
        return iOrganService.insertOrgan(id, organName, parentId);
    }

    /**
     * 修改部门
     *
     * @param id
     * @param organName
     * @param parentId
     * @return
     */
    @RequestMapping(value = "update_organ.do", method = RequestMethod.POST)
    public ServerResponse updateOrgan(int id, String organName, int parentId) {
        return iOrganService.updateOrgan(id, organName, parentId);
    }

    /**
     * 根据id查询部门
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "get_organ_by_id.do", method = RequestMethod.POST)
    public ServerResponse getOrganById(int id) {
        return iOrganService.getOrganById(id);
    }

    /**
     * 根据id删除部门
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "del_organ.do", method = RequestMethod.POST)
    public ServerResponse delOrgan(int id) {
        return iOrganService.delOrgan(id);
    }
}
