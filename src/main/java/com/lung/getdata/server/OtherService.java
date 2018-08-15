package com.lung.getdata.server;

import com.lung.getdata.mapper.OtherMapper;
import com.lung.getdata.pojo.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OtherService {
    private static final Logger log = LoggerFactory.getLogger(OtherService.class);

    private String fileName = this.getClass().getClassLoader().getResource("csv/1.txt").getPath();
    private static final int NUM = 9;
    @Resource
    private OtherMapper otherMapper;

    public void skuInitMysql() throws IOException, SQLException {
        writeCsv2Mysql(fileName);
    }

    public String skuInitRedis() throws IOException {
        Jedis jedis = null;
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            JedisPool jedisPool = new JedisPool(config, "10.3.9.237", 6379, 2000);
            jedis = jedisPool.getResource();
            if (jedis != null) {
                BufferedReader br = new BufferedReader(new FileReader(fileName));
                String line = "";
                log.info("===+++=== : save redis begin");
                int n = 0;
                while ((line = br.readLine()) != null) {
                    String[] s = line.split(",");
                    String aid = s[0];
                    String group = s[1];
                    List arrList = new ArrayList(Arrays.asList(s));
                    arrList.remove(0);
                    arrList.remove(0);
                    jedis.set("rec_" + aid, group + "-" + arrList.toString());
                    n++;
                    if(n % NUM == 0)
                        log.info("第"+n+"个key");
                }
                log.info("===+++=== : save redis end");
                return "sku init redis success";
            } else {
                return "can not connect to redis";
            }
        } finally {
            jedis.close();
        }
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
//            otherMapper.updateSkuByAid(key,arrList.toString());
            conSql = conSql + "('" + key + "','" + group + "','" + arrList.toString() + "'),";
            n++;
            if(n % NUM == 0){
                conSql = conSql.substring(0, conSql.lastIndexOf(","));
                otherMapper.skuInit(conSql);
                conSql = "";
                log.info("第"+(n / NUM)+"次插入(num="+NUM+")");
            }
        }
    }

    public List<Comment> findCommentByCid(String ids) {
        List<Integer> list = new ArrayList<>();
        for(String id : ids.split(",")){
            list.add(Integer.parseInt(id));
        }
        return otherMapper.selectCommentByCid(list);
    }
}
