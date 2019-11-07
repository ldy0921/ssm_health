package com.jxufe.service;

import com.jxufe.entity.PageResult;
import com.jxufe.entity.QueryPageBean;
import com.jxufe.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {

    void add(CheckItem checkItem);


    PageResult queryPage(Integer currentPage, Integer pageSize, String queryString);

    CheckItem findById(String id);

    void edit(CheckItem checkItem);

    List<CheckItem> findAll();
}
