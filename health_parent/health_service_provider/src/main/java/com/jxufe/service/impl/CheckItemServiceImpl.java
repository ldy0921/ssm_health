package com.jxufe.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jxufe.pojo.CheckItem;
import com.jxufe.service.CheckItemService;

@Service
public class CheckItemServiceImpl implements CheckItemService {
    @Override
    public void add(CheckItem checkItem) {
        System.out.println("1111");
        System.out.println(checkItem);
    }
}
