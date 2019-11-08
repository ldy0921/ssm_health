package com.jxufe.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FileNameUtils {


    public static String getName(String fileName) {


        /*
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        int i = fileName.lastIndexOf(".");

        String name = fileName.substring(fileName.lastIndexOf("."));
        */


        StringBuilder uploadFileName = new StringBuilder();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        String dataStr = sdf.format(new Date());

        String[] splits = fileName.split("\\.");




        return uploadFileName.append(dataStr).append(".").append(splits[splits.length-1]).toString();
    }

    public static void main(String[] args) {

        String name = FileNameUtils.getName("1232.png");

        System.out.println(name);

    }

}
