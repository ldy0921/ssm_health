package com.jxufe.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jxufe.dao.CheckItemDao;
import com.jxufe.entity.PageResult;
import com.jxufe.pojo.CheckItem;
import com.jxufe.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;

    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }
}
