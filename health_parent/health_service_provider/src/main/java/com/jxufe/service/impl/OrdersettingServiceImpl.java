package com.jxufe.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jxufe.constant.RedisConstant;
import com.jxufe.dao.OrdersettingMapper;
import com.jxufe.pojo.OrderSetting;
import com.jxufe.service.OrdersettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Set;

@Service(interfaceClass = OrdersettingService.class)
@Transactional
public class OrdersettingServiceImpl implements OrdersettingService {

    @Autowired
    private OrdersettingMapper mapper;

    @Autowired
    private JedisPool jedisPool;


   /* @Override
    public void add(List<OrderSetting> list) {

        if (list != null && list.size() > 0) {

            for (OrderSetting os : list) {
                // 判断当前日期是否插入数据
                int count = mapper.findOrdersettingByDate(os.getOrderDate());
                if (count > 0) {
                    // 已经有数据,就更新当前日期的数据
                    mapper.update(os);
                } else {
                    // 没有数据,就直接插入
                    mapper.add(os);
                }
            }

        }

    }*/

    @Override
    public void add(List<OrderSetting> list) {

        // 先从redis中取出指定的键的值
        // 判断键值里面的date是否与当前对象的日期相等
        // 如果不相等,就查询数据库,并把ordersetting对象插入到redis中

        if (list != null && list.size() > 0) {
            for (OrderSetting os : list) {


                //判断redis中是否有相同的日期
                Set<String> set = jedisPool.getResource().smembers(RedisConstant.ORDERDATE);

                //如果不存在则进行保存,反之执行修改操作
                if ( !set.contains(os.getOrderDate())) {
                    mapper.add(os);
                    //将数据保存到redis数据库中
                    jedisPool.getResource().sadd(RedisConstant.ORDERDATE, os.getOrderDate().toString());
                } else {
                    mapper.update(os);
                }
            }
        }

    }
}
