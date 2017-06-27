package com.inforshuttle.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.inforshuttle.mybatis.dto.UserDto;

/**
 * @author gacl
 * 定义sql映射的接口，使用注解指明方法要执行的SQL
 */
public interface UserMapperI {

    //使用@Insert注解指明add方法要执行的SQL
    @Insert("insert into t_user(userid,username,password,address,createtime) values(#{userid},#{username},#{password},#{address},#{createtime,jdbcType=TIMESTAMP})")
    public int add(UserDto user);
    
    //使用@Delete注解指明deleteById方法要执行的SQL
    @Delete("delete from t_user where userid=#{userid}")
    public int deleteById(int id);
    
    //使用@Update注解指明update方法要执行的SQL
    @Update("update t_user set username=#{username},password=#{password},address=#{address} where userid=#{userid}")
    public int update(UserDto user);
    
    //使用@Select注解指明getById方法要执行的SQL
    @Select("select * from t_user where userid=#{userid}")
    public UserDto getById(int id);
    
    //使用@Select注解指明getAll方法要执行的SQL
    @Select("select * from t_user")
    public List<UserDto> getAll();
}