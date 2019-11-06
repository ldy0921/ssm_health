package com.jxufe.dao;

import com.github.pagehelper.Page;
import com.jxufe.pojo.CheckItem;

public interface CheckItemMapper {


    void add(CheckItem checkItem);

    Page<CheckItem> selectByCondition(String queryString);

    CheckItem findById(Integer id);

    void edit(CheckItem checkItem);


}
