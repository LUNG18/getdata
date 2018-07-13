package com.lung.getdata.controller;

import com.lung.getdata.server.OtherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OtherController {
    private static final Logger log = LoggerFactory.getLogger(OtherController.class);

    @Resource
    private OtherService otherService;

    @RequestMapping("sku/init")
    public String skuInit(){
        try{
            otherService.skuInit();
        }catch (Exception e){
            log.info("sku init : "+ e);
            return e+"";
        }
        return "success";
    }
}
