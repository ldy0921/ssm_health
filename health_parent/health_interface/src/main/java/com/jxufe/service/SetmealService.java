package com.jxufe.service;

import com.jxufe.entity.PageResult;
import com.jxufe.entity.QueryPageBean;
import com.jxufe.pojo.Setmeal;

import java.util.List;

public interface SetmealService {

    void add(List<Integer> ids, Setmeal setmeal);

    PageResult findPage(QueryPageBean pageBean);
}
