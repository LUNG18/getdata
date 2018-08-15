package com.lung.getdata.controller;

import com.lung.getdata.server.CsvServer;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CsvController {
    private Logger log = org.slf4j.LoggerFactory.getLogger(CsvController.class);
    @Resource
    private CsvServer csvServer;

    @RequestMapping("article/all")
    public String getAllArticles(Long date){
        try {
            log.info("date = "+date);
            csvServer.getArticleCsv(date);
        } catch (Exception e) {
            return e+"";
        }
        return "success";
    }

    @RequestMapping("comment/all")
    public String getAllComments(){
        try {
            csvServer.getCommentCsv();
        } catch (Exception e) {
            return e+"";
        }
        return "success";
    }

    @RequestMapping("commentfollower/all")
    public String getAllCommentFollowers(){
        try {
            csvServer.getCommentFollowCsv();
        } catch (Exception e) {
            return e+"";
        }
        return "success";
    }

    @RequestMapping("commentlog/all")
    public String getAllCommentlog(){
        try {
            csvServer.getCommentLogCsv();
        } catch (Exception e) {
            return e+"";
        }
        return "success";
    }

    @RequestMapping("praise/all")
    public String getAllPraise(){
        try {
            csvServer.getPraiseCsv();
        } catch (Exception e) {
            return e+"";
        }
        return "success";
    }

    @RequestMapping("messages/all")
    public String getAllMessages(){
        try {
            csvServer.getMessagesCsv();
        } catch (Exception e) {
            return e+"";
        }
        return "success";
    }

    @RequestMapping("useroplog/all")
    public String getAllUserOplog(){
        try {
            csvServer.getUserOplogCsv();
        } catch (Exception e) {
            return e+"";
        }
        return "success";
    }

    @RequestMapping("tables")
    public String getTables(){
        this.getAllComments();
        this.getAllCommentFollowers();
        this.getAllCommentlog();
        this.getAllPraise();
        this.getAllMessages();
        this.getAllUserOplog();
        return "success";
    }
}
