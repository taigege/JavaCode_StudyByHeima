package com.qingtai.ssm.dao;

import com.qingtai.ssm.domain.Role;
import com.qingtai.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.qingtai.ssm.dao.IRoleDao.findRoleByUserId"))
    })
    public UserInfo findByUsername(String username) throws Exception;



    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    @Insert("insert into users(id,email,username,password,phoneNum,status) values(#{username},#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;


    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,
                    many = @Many(select = "com.qingtai.ssm.dao.IRoleDao.findRoleByUserId"))
    })
    public UserInfo finById(String id) throws Exception;


    @Select("select * from role where id not in (select roleId from users_role where userId=#{userId})")
    public List<Role> findOtherRoles(String userId);

    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    public void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);

}
