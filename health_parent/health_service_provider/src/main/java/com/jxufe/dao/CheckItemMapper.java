package com.jxufe.dao;

import com.github.pagehelper.Page;
import com.jxufe.pojo.CheckItem;

import java.util.List;

public interface CheckItemMapper {


    void add(CheckItem checkItem);

    Page<CheckItem> selectByCondition(String queryString);

    CheckItem findById(Integer id);

    void edit(CheckItem checkItem);

    List<CheckItem> findAll();


}
