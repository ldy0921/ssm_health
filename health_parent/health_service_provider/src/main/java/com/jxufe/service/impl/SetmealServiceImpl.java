package com.jxufe.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jxufe.dao.SetmealMapper;
import com.jxufe.entity.PageResult;
import com.jxufe.entity.QueryPageBean;
import com.jxufe.pojo.Setmeal;
import com.jxufe.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;

    @Override
    public void add(List<Integer> ids, Setmeal setmeal) {

        // 向t_setmeal表中插入数据
        setmealMapper.add(setmeal);

        // 向t_setmeal_checkgroup表中插入数据
        if (ids != null && ids.size() > 0) {
            for (int gid : ids) {
                setmealMapper.setSetmealAndCheckGroup(setmeal.getId(), gid);
            }
        }

    }

    @Override
    public PageResult findPage(QueryPageBean pageBean) {

        PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getPageSize());

        List<Setmeal> setmeals =  setmealMapper.findPage(pageBean.getQueryString());

        PageInfo pageInfo = new PageInfo(setmeals);

        return new PageResult(pageInfo.getTotal(), setmeals);
    }
}
