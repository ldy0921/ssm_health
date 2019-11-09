package com.jxufe.jobs;

import com.jxufe.constant.RedisConstant;
import com.sun.jersey.api.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Set;

public class ClearImgJob {


    @Autowired
    private JedisPool jedisPool;

    public void clearImg() {
        // 大集合减小集合,获得垃圾图片的信息
        Set<String> set = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);

        String path = "http://localhost:8080/fileServer/upload/";

        System.out.println("清理垃圾图片开始");


        if (set != null && set.size() > 0) {

            // 创建一个跨服务器上传的客服端
            Client client = new Client();

            for (String picName : set) {
                // 删除redis中大集合中的垃圾图片名称
                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES, picName);

                // 删除图片服务器上保存的垃圾图片
                client.resource(path+picName).delete();
            }
        }
    }

}
