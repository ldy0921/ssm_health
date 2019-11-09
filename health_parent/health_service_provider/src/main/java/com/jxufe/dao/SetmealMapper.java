package com.jxufe.dao;

import com.jxufe.pojo.Setmeal;

import java.util.List;

public interface SetmealMapper {

    void add(Setmeal setmeal);

    void setSetmealAndCheckGroup(int sid, int gid);

    List<Setmeal> findPage(String queryString);
}
