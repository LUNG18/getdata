package com.lung.getdata.server;

import com.lung.getdata.mapper.OtherMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OtherService {
    private static final Logger Log = LoggerFactory.getLogger(OtherService.class);

    private String fileName = this.getClass().getClassLoader().getResource("csv/1.txt").getPath();

    @Resource
    private OtherMapper otherMapper;

    public void skuInit() throws IOException, SQLException {
        writeCsv2Mysql(fileName);
    }

    private void writeCsv2Mysql(String fileName) throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = "";
        String conSql = "";
        int n = 0;
        while((line=br.readLine()) != null){
            String[] s = line.split(",");
            String key = s[0];
            String group = s[1];
            List<String> list = Arrays.asList(s);
            List arrList = new ArrayList(list);
            arrList.remove(0);
            arrList.remove(0);
            arrList.remove(s.length-3);
            conSql = conSql + "('" + key + "','" + group + "','" + arrList.toString() + "'),";
            n++;
            if(n%1141 == 0){
                conSql = conSql.substring(0, conSql.lastIndexOf(","));
                otherMapper.skuInit(conSql);
                conSql = "";
            }
        }
    }

}
