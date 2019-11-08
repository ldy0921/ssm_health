package com.jxufe.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jxufe.dao.SetmealMapper;
import com.jxufe.pojo.Setmeal;
import com.jxufe.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;

    @Override
    public void add(Integer[] ids, Setmeal setmeal) {

        // 向t_setmeal表中插入数据
        setmealMapper.add(setmeal);

        // 向t_setmeal_checkgroup表中插入数据
        if (ids != null && ids.length > 0) {
            for (int gid : ids) {
                setmealMapper.setSetmealAndCheckGroup(setmeal.getId(), gid);
            }
        }

    }
}
