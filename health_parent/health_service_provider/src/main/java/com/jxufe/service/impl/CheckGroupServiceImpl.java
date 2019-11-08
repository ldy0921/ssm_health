package com.jxufe.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jxufe.dao.CheckGroupMapper;
import com.jxufe.entity.PageResult;
import com.jxufe.entity.QueryPageBean;
import com.jxufe.pojo.CheckGroup;
import com.jxufe.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;


@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupMapper groupMapper;


    @Override
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        System.out.println("添加前id: "+checkGroup.getId());
        // 添加检查组,操作t_checkgroup表
        groupMapper.add(checkGroup);
        System.out.println("添加后id: "+checkGroup.getId());

        System.out.println("检查项id: "+Arrays.toString(checkitemIds));

        // 添加检查组与检查项之间的多对多的关系,操作t_checkgroup_checkitem表
        if (checkitemIds != null && checkitemIds.length > 0){
            for (Integer itemId : checkitemIds) {
                groupMapper.setCheckGroupAndCheckItem(checkGroup.getId(), itemId);
            }
        }

    }

    @Override
    public PageResult findPage(QueryPageBean pageBean) {

        // PageHelper.startPage(当前页码,每页展示的数据条数)
        PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getPageSize());

        // 将查询出的结果封装成为集合对象
        List<CheckGroup> list = groupMapper.findPage(pageBean.getQueryString());

        // 将集合载入到PageInfo中,反推出总记录数
        PageInfo pi = new PageInfo(list);

        // 将总页数与当前页数据集合载入到PageResult中
        return new PageResult(pi.getTotal(), list);
    }

    // 修改检查组时回显页面数据
    @Override
    public CheckGroup findById(Integer id) {

        CheckGroup checkGroup = groupMapper.findById(id);

        return checkGroup;
    }

    // 修改检查组
    @Override
    public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {
        groupMapper.updateById(checkGroup);


        // 先删除有关检查组的id的中间表数据
        groupMapper.delCheckGroupItemByGId(checkGroup.getId());

        // 再更新中间表数据
        if (checkitemIds != null && checkitemIds.length > 0) {
            for (Integer checkitemId : checkitemIds) {
                groupMapper.setCheckGroupAndCheckItem(checkGroup.getId(), checkitemId);
            }
        }
    }

    // 删除检查组的数据
    @Override
    public void delById(Integer id) {

        groupMapper.delCheckGroupItemByGId(id);

        groupMapper.delGroup(id);


    }

    // 查询所有
    @Override
    public List<CheckGroup> findAll() {

        return groupMapper.findAll();

    }
}
