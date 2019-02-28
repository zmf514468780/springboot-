package com.zmf.dao;

import com.zmf.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Auther: zmf
 * @Date: 2019-01-16 11:47
 * @Description:
 */

@Mapper
public interface MemberDao {
    @Select("select  * from mb_user")
    List<UserEntity> findAll();

    @Select("select  id,username,password,phone,email,created,updated from mb_user where id =#{userId}")
    UserEntity findByID(@Param("userId") Long userId);

    @Insert("INSERT  INTO `mb_user`  (username,password,phone,email,created,updated) VALUES (#{username}, #{password},#{phone},#{email},#{created},#{updated});")
    Integer insertUser(UserEntity userEntity);

    @Select("select * from mb_user where password =#{password} and username = #{username}")
    UserEntity findByUsernameAndPassword(@Param("username") String username,@Param("password")  String password );

    @Select("select  id,username,password,phone,email,created,updated ,openid from mb_user where openid =#{openid}")
    UserEntity findByOpenIdUser(@Param("openid") String openid);
    @Update("update mb_user set openid=#{openid} where id=#{userId}")
    Integer updateByOpenIdUser(@Param("openid") String openid,@Param("userId") Integer userId);

}
