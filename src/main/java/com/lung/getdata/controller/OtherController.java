package com.lung.getdata.controller;

import com.alibaba.fastjson.JSONObject;
import com.lung.getdata.pojo.Comment;
import com.lung.getdata.server.OtherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class OtherController {
    private static final Logger log = LoggerFactory.getLogger(OtherController.class);

    @Resource
    private OtherService otherService;

    @RequestMapping("sku/init/mysql")
    public String skuInitMysql(){
        String ret = "111";
        try{
            otherService.skuInitMysql();
            ret = "sku init mysql success";
        }catch (Exception e){
            log.info("sku init mysql: "+ e);
            return e.getMessage();
        }
        return ret;
    }

    @RequestMapping("sku/init/redis")
    public String skuInitRedis(){
        String ret = "222";
        try{
            ret = otherService.skuInitRedis();
        }catch (Exception e){
            log.info("sku init redis: "+ e);
            return e.getMessage();
        }
        return ret;
    }


    @RequestMapping("test")
    public String test01(String ids, HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin","*");
        try {
            List<Comment> comment = otherService.findCommentByCid(ids);
            return JSONObject.toJSONString(comment);
        }catch (Exception e){
            return e+"";
        }
    }
}
