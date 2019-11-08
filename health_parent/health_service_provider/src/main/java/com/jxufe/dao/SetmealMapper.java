package com.jxufe.dao;

import com.jxufe.pojo.Setmeal;

public interface SetmealMapper {

    void add(Setmeal setmeal);

    void setSetmealAndCheckGroup(int sid, int gid);
}
