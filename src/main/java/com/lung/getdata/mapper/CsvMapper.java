package com.lung.getdata.mapper;

import com.lung.getdata.pojo.*;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CsvMapper {


    @Select("select * from tb_articles where created_at <= #{date}")
    public List<Article> selectAllArticles(Long date);

    @Select("select * from tb_comment")
    public List<Comment> selectAllComment();

    @Select("select * from tb_comment_follower")
    public List<CommentFollower> selectAllCommentFollower();

    @Select("select * from tb_comment_log")
    public List<CommentLog> selectAllCommentLog();

    @Select("select * from tb_praise")
    public List<Praise> selectAllPraise();

    @Select("select * from tb_messages")
    public List<Messages> selectAllMessages();

    @Select("select * from tb_user_oplog")
    public List<UserOplog> selectAllUserOplog();
}
