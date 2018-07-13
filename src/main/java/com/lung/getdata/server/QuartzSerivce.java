package com.lung.getdata.server;

import com.lung.getdata.utils.DateFormatTool;
import com.lung.getdata.utils.FtpClientUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class QuartzSerivce {
    private Logger log = org.slf4j.LoggerFactory.getLogger(QuartzSerivce.class);

    @Resource
    private CsvServer csvServer;

    @Scheduled(cron = "0 20 13 * * ?")
    public void createCsv(){
        try {
        } catch (Exception e) {
            log.info(e+"");
        }
    }

}
