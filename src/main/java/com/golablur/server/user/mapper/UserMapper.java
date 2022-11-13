package com.golablur.server.user.mapper;

import com.golablur.server.user.domain.UserEntity;
import org.apache.ibatis.annotations.*;


@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE User_ID = #{id}")
    public UserEntity findUser(@Param("id") String id);

    @Insert("INSERT INTO user VALUES (#{id},#{name},#{pw})")
    public int signUp(@Param("id") String id, @Param("name") String name, @Param("pw") String pw);

    @Select("SELECT * FROM user WHERE User_ID = #{id} and User_PW = #{pw}")
    public UserEntity login(@Param("id") String id, @Param("pw") String pw);

    @Update("UPDATE user SET User_ID = #{sessionToken} WHERE User_ID = #{id}")
    public int updateFileData(@Param("id") String id, @Param("sessionToken") String sessionToken);

}
