package com.jxufe.dao;

import com.jxufe.pojo.OrderSetting;

import java.util.Date;

public interface OrdersettingMapper {

    void add(OrderSetting os);

    int findOrdersettingByDate(Date date);

    void update(OrderSetting os);

}
