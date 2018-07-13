package com.lung.getdata.mapper;

import com.lung.getdata.pojo.PushUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OtherMapper {


    @Select("select aid,openid,nickname from `tb_push_detail` where  pid=84 and status in (0,1)")
    public List<PushUser> findAllPushUserByStatusAndTimeNull();

    @Select("select aid,openid,nickname from `tb_push_detail` where  created_at=1529906320 and status in (0,1) and push_at is not null")
    public List<PushUser> findAllPushUserByStatusAndTimeNotNull();

    @Insert("insert into tb_sku(aid,`group`,`value`) values ${val}")
    public void skuInit(@Param("val") String conSql);
}
