package com.lung.getdata.server;

import com.lung.getdata.mapper.CsvMapper;
import com.lung.getdata.pojo.*;
import com.lung.getdata.utils.DateFormatTool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class CsvServer {

    @Resource
    private CsvMapper csvMapper;

    private String csvName = "tb_articles_";

    @Value("${csv_path}")
    private String csvPath;

    public void getArticleCsv(Long date) throws Exception {
        // 表格头
        Object[] head = { "id", "artid", "title", "link", "type", "created_at", "last_modified"};
        List<Object> headList = Arrays.asList(head);
        //数据
        List<List<Object>> dataList = new ArrayList<>();
        List<Article> articles = csvMapper.selectAllArticles(date);
        for(Article article : articles){
            List<Object> rowList = new ArrayList<>();
            rowList.add(article.getId());
            rowList.add(article.getArtId());
            rowList.add(article.getTitle());
            String link = article.getLink().replaceAll("/jcr:content", ".html");
            rowList.add(link);
            if(!article.getLink().contains("/banners") && (link.contains("/recipes") || link.contains("hot-recipe"))) {
                rowList.add("recipe");
            }else if(link.contains("/brands-products")) {
                rowList.add("product");
            }else {
                rowList.add("other");
            }
            rowList.add(DateFormatTool.long2string(article.getCreatedAt(), "yyyy-MM-dd HH:mm"));
            if(article.getLastModified() != null){
                rowList.add(DateFormatTool.long2string(article.getLastModified(), "yyyy-MM-dd HH:mm"));
            }else{
                rowList.add("无");
            }
            dataList.add(rowList);
        }

        String daily = new SimpleDateFormat("yyyyMMdd").format(new Date());
        File csvFile = new File(csvPath + csvName + daily + ".csv");
        File parent = csvFile.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        csvFile.createNewFile();

        // GB2312使正确读取分隔符","
        BufferedWriter csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GB2312"), 1024);

        //文件下载，使用如下代码
//            response.setContentType("application/csv;charset=gb18030");
//            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
//            ServletOutputStream out = response.getOutputStream();
//            csvWtriter = new BufferedWriter(new OutputStreamWriter(out, "GB2312"), 1024);

        // 写入文件头部
        writeRow(headList, csvWtriter);
        // 写入文件内容
        for (List<Object> row : dataList) {
            writeRow(row, csvWtriter);
        }
        csvWtriter.flush();
        csvWtriter.close();
    }

    public void getCommentCsv() throws Exception {
        // 表格头
        Object[] head = { "id","artid","openid","parentid","content","comment_numers","praise_nummers",
                        "isdisplay","isread","created_at","last_modified","op_status","is_from_admin",
                        "corder","find_corder","hot_corder","spt_for","labels","title", "aid", "images",
                        "type","publish_at","tags","is_followed","groupid","subject_id","book_time"};
        List<Object> headList = Arrays.asList(head);
        //数据
        List<List<Object>> dataList = new ArrayList<>();
        List<Comment> comments = csvMapper.selectAllComment();
        for(Comment comment : comments){
            List<Object> rowList = new ArrayList<>();
            rowList.add(comment.getId());
            rowList.add(comment.getArtid());
            rowList.add(comment.getOpenid());
            rowList.add(comment.getParentid());
            rowList.add(comment.getContent());
            rowList.add(comment.getCommentNumers());
            rowList.add(comment.getPraiseNumers());
            rowList.add(comment.getIsdisplay());
            rowList.add(comment.getIsread());
            rowList.add(comment.getCreatedAt());
            rowList.add(comment.getLastModified());
            rowList.add(comment.getOpStatus());
            rowList.add(comment.getIsFromAdmin());
            rowList.add(comment.getCorder());
            rowList.add(comment.getFindCorder());
            rowList.add(comment.getHotCorder());
            rowList.add(comment.getSptFor());
            rowList.add(comment.getLabels());
            rowList.add(comment.getTitle());
            rowList.add(comment.getAid());
            rowList.add(comment.getImages());
            rowList.add(comment.getType());
            rowList.add(comment.getPublishAt());
            rowList.add(comment.getTags());
            rowList.add(comment.getIsFollowed());
            rowList.add(comment.getGroupid());
            rowList.add(comment.getSubjectId());
            rowList.add(comment.getBookTime());
            dataList.add(rowList);
        }

        File csvFile = new File(csvPath + "tb_comment.csv");
        File parent = csvFile.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        csvFile.createNewFile();

        // GB2312使正确读取分隔符","
        BufferedWriter csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GB2312"), 1024);

        //文件下载，使用如下代码
//            response.setContentType("application/csv;charset=gb18030");
//            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
//            ServletOutputStream out = response.getOutputStream();
//            csvWtriter = new BufferedWriter(new OutputStreamWriter(out, "GB2312"), 1024);

        // 写入文件头部
        writeRow(headList, csvWtriter);
        // 写入文件内容
        for (List<Object> row : dataList) {
            writeRow(row, csvWtriter);
        }
        csvWtriter.flush();
        csvWtriter.close();
    }

    public void getCommentFollowCsv() throws Exception {
        // 表格头
        Object[] head = {"aid","comment_id","comment_type","type","tag","created_at","deleted","deleted_at","last_followed"};
        List<Object> headList = Arrays.asList(head);
        //数据
        List<List<Object>> dataList = new ArrayList<>();
        List<CommentFollower> comments = csvMapper.selectAllCommentFollower();
        for(CommentFollower comment : comments){
            List<Object> rowList = new ArrayList<>();
            rowList.add(comment.getAid());
            rowList.add(comment.getCommentId());
            rowList.add(comment.getCommentType());
            rowList.add(comment.getType());
            rowList.add(comment.getTag());
            rowList.add(comment.getCreatedAt());
            rowList.add(comment.getDeleted());
            rowList.add(comment.getDeletedAt());
            rowList.add(comment.getLastFollowed());

            dataList.add(rowList);
        }

        File csvFile = new File(csvPath + "tb_comment_follower.csv");
        File parent = csvFile.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        csvFile.createNewFile();

        // GB2312使正确读取分隔符","
        BufferedWriter csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GB2312"), 1024);

        //文件下载，使用如下代码
//            response.setContentType("application/csv;charset=gb18030");
//            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
//            ServletOutputStream out = response.getOutputStream();
//            csvWtriter = new BufferedWriter(new OutputStreamWriter(out, "GB2312"), 1024);

        // 写入文件头部
        writeRow(headList, csvWtriter);
        // 写入文件内容
        for (List<Object> row : dataList) {
            writeRow(row, csvWtriter);
        }
        csvWtriter.flush();
        csvWtriter.close();
    }

    public void getCommentLogCsv() throws Exception {
        // 表格头
        Object[] head = {"id","question_id","aid","nickname","type","content","objectid","reply_content","created_at","follower_count","isdisplay"};
        List<Object> headList = Arrays.asList(head);
        //数据
        List<List<Object>> dataList = new ArrayList<>();
        List<CommentLog> comments = csvMapper.selectAllCommentLog();
        for(CommentLog comment : comments){
            List<Object> rowList = new ArrayList<>();
            rowList.add(comment.getId());
            rowList.add(comment.getQuestionId());
            rowList.add(comment.getAid());
            rowList.add(comment.getNickname());
            rowList.add(comment.getType());
            rowList.add(comment.getContent());
            rowList.add(comment.getObjectid());
            rowList.add(comment.getReplyContent());
            rowList.add(comment.getCreatedAt());
            rowList.add(comment.getFollowerCount());
            rowList.add(comment.getIsdisplay());

            dataList.add(rowList);
        }

        File csvFile = new File(csvPath + "tb_comment_log.csv");
        File parent = csvFile.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        csvFile.createNewFile();

        // GB2312使正确读取分隔符","
        BufferedWriter csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GB2312"), 1024);

        //文件下载，使用如下代码
//            response.setContentType("application/csv;charset=gb18030");
//            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
//            ServletOutputStream out = response.getOutputStream();
//            csvWtriter = new BufferedWriter(new OutputStreamWriter(out, "GB2312"), 1024);

        // 写入文件头部
        writeRow(headList, csvWtriter);
        // 写入文件内容
        for (List<Object> row : dataList) {
            writeRow(row, csvWtriter);
        }
        csvWtriter.flush();
        csvWtriter.close();
    }

    public void getPraiseCsv() throws Exception {
        // 表格头
        Object[] head = {"id","artid","openid","commentid","ispraised","created_at","last_modified","aid"};
        List<Object> headList = Arrays.asList(head);
        //数据
        List<List<Object>> dataList = new ArrayList<>();
        List<Praise> comments = csvMapper.selectAllPraise();
        for(Praise comment : comments){
            List<Object> rowList = new ArrayList<>();
            rowList.add(comment.getId());
            rowList.add(comment.getArtId());
            rowList.add(comment.getOpenid());
            rowList.add(comment.getCommentid());
            rowList.add(comment.getIspraised());
            rowList.add(comment.getCreatedAt());
            rowList.add(comment.getLastModified());
            rowList.add(comment.getAid());

            dataList.add(rowList);
        }

        File csvFile = new File(csvPath + "tb_praise.csv");
        File parent = csvFile.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        csvFile.createNewFile();

        // GB2312使正确读取分隔符","
        BufferedWriter csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GB2312"), 1024);

        //文件下载，使用如下代码
//            response.setContentType("application/csv;charset=gb18030");
//            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
//            ServletOutputStream out = response.getOutputStream();
//            csvWtriter = new BufferedWriter(new OutputStreamWriter(out, "GB2312"), 1024);

        // 写入文件头部
        writeRow(headList, csvWtriter);
        // 写入文件内容
        for (List<Object> row : dataList) {
            writeRow(row, csvWtriter);
        }
        csvWtriter.flush();
        csvWtriter.close();
    }

    public void getMessagesCsv() throws Exception {
        // 表格头
        Object[] head = {"id","from_openid","to_openid","msg_type","content","objectId","priority",
                        "created_at","last_modified","publish_at","from_aid","from_nickname","to_aid",
                        "reference_type","reference_title","reference_url","reference_content","commentid"};
        List<Object> headList = Arrays.asList(head);
        //数据
        List<List<Object>> dataList = new ArrayList<>();
        List<Messages> comments = csvMapper.selectAllMessages();
        for(Messages comment : comments){
            List<Object> rowList = new ArrayList<>();
            rowList.add(comment.getId());
            rowList.add(comment.getFromOpenid());
            rowList.add(comment.getToOpenid());
            rowList.add(comment.getMsgType());
            rowList.add(comment.getContent());
            rowList.add(comment.getObjectId());
            rowList.add(comment.getPriority());
            rowList.add(comment.getCreatedAt());
            rowList.add(comment.getLastModified());
            rowList.add(comment.getPublishAt());
            rowList.add(comment.getFromAid());
            rowList.add(comment.getFromNickname());
            rowList.add(comment.getToAid());
            rowList.add(comment.getReferenceType());
            rowList.add(comment.getReferenceTitle());
            rowList.add(comment.getReferenceUrl());
            rowList.add(comment.getReferenceContent());
            rowList.add(comment.getCommentid());

            dataList.add(rowList);
        }

        File csvFile = new File(csvPath + "tb_messages.csv");
        File parent = csvFile.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        csvFile.createNewFile();

        // GB2312使正确读取分隔符","
        BufferedWriter csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GB2312"), 1024);

        //文件下载，使用如下代码
//            response.setContentType("application/csv;charset=gb18030");
//            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
//            ServletOutputStream out = response.getOutputStream();
//            csvWtriter = new BufferedWriter(new OutputStreamWriter(out, "GB2312"), 1024);

        // 写入文件头部
        writeRow(headList, csvWtriter);
        // 写入文件内容
        for (List<Object> row : dataList) {
            writeRow(row, csvWtriter);
        }
        csvWtriter.flush();
        csvWtriter.close();
    }

    public void getUserOplogCsv() throws Exception {
        // 表格头
        Object[] head = {"id","aid","admin_id","op_type","op_msg","created_at"};
        List<Object> headList = Arrays.asList(head);
        //数据
        List<List<Object>> dataList = new ArrayList<>();
        List<UserOplog> comments = csvMapper.selectAllUserOplog();
        for(UserOplog comment : comments){
            List<Object> rowList = new ArrayList<>();
            rowList.add(comment.getId());
            rowList.add(comment.getAid());
            rowList.add(comment.getAdminId());
            rowList.add(comment.getOpType());
            rowList.add(comment.getOpMsg());
            rowList.add(comment.getCreatedAt());

            dataList.add(rowList);
        }

        File csvFile = new File(csvPath + "tb_user_oplog.csv");
        File parent = csvFile.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        csvFile.createNewFile();

        // GB2312使正确读取分隔符","
        BufferedWriter csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GB2312"), 1024);

        //文件下载，使用如下代码
//            response.setContentType("application/csv;charset=gb18030");
//            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
//            ServletOutputStream out = response.getOutputStream();
//            csvWtriter = new BufferedWriter(new OutputStreamWriter(out, "GB2312"), 1024);

        // 写入文件头部
        writeRow(headList, csvWtriter);
        // 写入文件内容
        for (List<Object> row : dataList) {
            writeRow(row, csvWtriter);
        }
        csvWtriter.flush();
        csvWtriter.close();
    }










    private static void writeRow(List<Object> row, BufferedWriter csvWriter) throws IOException {
        for (Object data : row) {
            StringBuffer sb = new StringBuffer();
            String rowStr = sb.append("\"").append(data).append("\",").toString();
            csvWriter.write(rowStr);
        }
        csvWriter.newLine();
    }
}
