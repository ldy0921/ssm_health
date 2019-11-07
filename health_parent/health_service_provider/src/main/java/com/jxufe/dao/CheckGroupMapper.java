package com.jxufe.dao;

import com.jxufe.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupMapper {

    void add(CheckGroup checkGroup);

    void setCheckGroupAndCheckItem(int groupId, int itemId);

    List<CheckGroup> findPage(String queryString);

    CheckGroup findById(Integer id);

    void updateById(CheckGroup checkGroup);

    void updateCheckGroupAndCheckItem(Integer id, Integer checkitemId);

    void delCheckGroupItemByGId(Integer id);

    void delGroup(Integer id);
}
