package com.jxufe.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jxufe.constant.MessageConstant;
import com.jxufe.entity.PageResult;
import com.jxufe.entity.QueryPageBean;
import com.jxufe.entity.Result;
import com.jxufe.pojo.CheckGroup;
import com.jxufe.service.CheckGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 检查组管理
 */
@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {


    @Reference
    private CheckGroupService groupService;


    @RequestMapping("/add.do")
    public Result add(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds){

        try {
            groupService.add(checkGroup, checkitemIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }

        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }


    @RequestMapping("/findPage.do")
    public PageResult findPage(@RequestBody QueryPageBean pageBean){

       PageResult result = groupService.findPage(pageBean);

        return result;
    }

    // 根据id查询检查组,数据回显
    @RequestMapping("/findById.do")
    public CheckGroup findById(Integer id){

        CheckGroup checkGroup = groupService.findById(id);

        return checkGroup;
    }

    // 根据id查询检查组,数据回显
    @RequestMapping("/edit.do")
    public Result edit(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds){

        System.out.println(checkGroup);

        try {
            groupService.edit(checkGroup, checkitemIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }

        return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }


    @RequestMapping("/delById.do")
    public Result delById(Integer id){


        try {
            groupService.delById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKGROUP_FAIL);
        }

        return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
    }

}
