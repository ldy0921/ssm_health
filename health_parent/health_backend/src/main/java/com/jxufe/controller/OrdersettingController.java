package com.jxufe.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jxufe.constant.MessageConstant;
import com.jxufe.entity.Result;
import com.jxufe.pojo.OrderSetting;
import com.jxufe.service.OrdersettingService;
import com.jxufe.utils.POIUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ordersetting")
public class OrdersettingController {


    @Reference
    private OrdersettingService service;


    @RequestMapping("/upload.do")
    public Result upload(MultipartFile excelFile) {

        try {
            List<String[]> list = POIUtils.readExcel(excelFile);

            List<OrderSetting> array = new ArrayList<>();

            for (String[] str : list) {
                OrderSetting orderSetting = new OrderSetting();
                orderSetting.setOrderDate(new Date(str[0]));
                orderSetting.setNumber(Integer.parseInt(str[1]));

                array.add(orderSetting);

            }

            service.add(array);

        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }

        return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
    }

}
