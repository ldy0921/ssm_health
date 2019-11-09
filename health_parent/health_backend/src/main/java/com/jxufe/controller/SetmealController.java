package com.jxufe.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.jxufe.constant.MessageConstant;
import com.jxufe.constant.RedisConstant;
import com.jxufe.entity.PageResult;
import com.jxufe.entity.QueryPageBean;
import com.jxufe.entity.Result;
import com.jxufe.pojo.Setmeal;
import com.jxufe.service.SetmealService;
import com.jxufe.utils.FileNameUtils;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Reference
    private SetmealService setmealService;

    @Autowired
    private JedisPool jedisPool;


    @RequestMapping("/upload.do")
    public Result upload(MultipartFile imgFile) {

        String path = "http://localhost:8080/fileServer/upload/";

        String oddName = imgFile.getOriginalFilename();

        String newName = FileNameUtils.getName(oddName);

        //创建网络文件传输的客服端
        Client client = new Client();

        WebResource resource = client.resource(path + newName);

        //上传 (本质)
        try {
            resource.put(imgFile.getBytes());
            //上传成功,把文件名保存到redis中的set集合中
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES, newName);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }

        return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, newName);
    }


    @RequestMapping("/add.do")
    public Result add(@RequestBody Object[] obj) {

        List<Integer> idsList = JSON.parseArray(obj[0].toString(), Integer.class);
        Setmeal setmeal = JSON.parseObject(obj[1].toString(), Setmeal.class);


        try {
            setmealService.add(idsList, setmeal);
            // 表单数据添加成功之后,把添加到数据库里的img名字保存到redis中
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES, setmeal.getImg());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_MEMBER_FAIL);
        }

        return new Result(true, MessageConstant.ADD_MEMBER_SUCCESS);

    }

    @RequestMapping("/findPage.do")
    public PageResult findPage(@RequestBody QueryPageBean pageBean) {

        return setmealService.findPage(pageBean);

    }

}
