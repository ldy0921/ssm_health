package com.jxufe.service;

import com.jxufe.entity.PageResult;
import com.jxufe.entity.QueryPageBean;
import com.jxufe.pojo.CheckGroup;

public interface CheckGroupService {

    void add(CheckGroup checkGroup, Integer[] checkitemIds);

    PageResult findPage(QueryPageBean pageBean);

    CheckGroup findById(Integer id);

    void edit(CheckGroup checkGroup, Integer[] checkitemIds);

    void delById(Integer id);
}
