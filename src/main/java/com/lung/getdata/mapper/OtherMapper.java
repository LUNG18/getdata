package com.lung.getdata.mapper;

import com.lung.getdata.pojo.Comment;
import com.lung.getdata.pojo.PushUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface OtherMapper {

    @Insert("insert into tb_sku(aid,`group`,`value`) values ${val}")
    public void skuInit(@Param("val") String conSql);

//    @Select("select * from tb_comment where id=#{cid}")
    List<Comment> selectCommentByCid(List<Integer> ids);

    @Select("select openid from tb_push_detail where aid=#{aid}")
    String selectCommentByAid(String aid);

    @Insert("insert into tb_push_detail2(pid,aid,openid,nickname,`status`,created_at) values (0,#{aid},#{openid},#{nickname},0,0)")
    void insertUser(PushUser user);

    @Update("update tb_sku set `value`=#{s} where aid=#{aid}")
    void updateSkuByAid(@Param("aid") String key, @Param("s") String s);
}
