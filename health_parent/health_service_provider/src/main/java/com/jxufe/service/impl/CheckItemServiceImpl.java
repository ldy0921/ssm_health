package com.jxufe.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jxufe.dao.CheckItemMapper;
import com.jxufe.entity.PageResult;
import com.jxufe.entity.QueryPageBean;
import com.jxufe.pojo.CheckItem;
import com.jxufe.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemMapper checkItemDao;

    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    @Override
    public PageResult queryPage(Integer currentPage, Integer pageSize, String queryString) {
        //使用分页助手,进行分页查询
        PageHelper.startPage(currentPage, pageSize);
        Page<CheckItem> checkItems = checkItemDao.selectByCondition(queryString);

        return new PageResult(checkItems.getTotal(), checkItems.getResult());
    }

    @Override
    public CheckItem findById(String id) {
        return checkItemDao.findById(Integer.parseInt(id));
    }

    @Override
    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }

    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }

}
