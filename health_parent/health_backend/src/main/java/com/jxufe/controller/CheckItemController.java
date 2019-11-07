package com.jxufe.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jxufe.constant.MessageConstant;
import com.jxufe.entity.PageResult;
import com.jxufe.entity.QueryPageBean;
import com.jxufe.entity.Result;
import com.jxufe.pojo.CheckItem;
import com.jxufe.service.CheckItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/checkitem")
public class CheckItemController {

    @Reference
    private CheckItemService checkItemService;

    //新增
    @RequestMapping("/add.do")
    public Result add(@RequestBody CheckItem checkItem) {

        try {
            checkItemService.add(checkItem);
        } catch (Exception e) {
            // 方法执行失败
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }

        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    // 分页查询
    @RequestMapping("/queryPage.do")
    public PageResult queryPage(@RequestBody QueryPageBean queryPageBean) {

        PageResult pageResult = checkItemService.queryPage(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString()
        );

        return pageResult;
    }

    @RequestMapping("/findById.do")
    public Result findById(String id) {
        CheckItem checkItem = null;
        try {
            checkItem = checkItemService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "查找失败", checkItem);
        }
        return new Result(true, "查找成功", checkItem);
    }


    // 修改检查项
    @RequestMapping("/edit.do")
    public Result edit(@RequestBody CheckItem checkItem) {

        try {
            checkItemService.edit(checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }

        return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

    // 查找所有检查项
    @RequestMapping("/findAll.do")
    public List<CheckItem> findAll() {

        return checkItemService.findAll();
    }

}
