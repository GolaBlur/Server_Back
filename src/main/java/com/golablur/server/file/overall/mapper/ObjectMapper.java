package com.golablur.server.file.overall.mapper;

import com.golablur.server.file.overall.domain.FileEntity;
import com.golablur.server.file.overall.domain.ObjectEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ObjectMapper {

    @Insert("INSERT INTO object (Object_ID, File_ID, Object_Name, Object_Count, Path)" +
            " VALUES (#{Object_ID}, #{File_ID}, #{Object_Name}, #{Object_Count}, #{Path})")
    int storeObject(ObjectEntity object);

    @Delete("DELETE FROM object WHERE File_ID = #{file_id}")
    int deleteObjects(@Param("file_id") String file_id);

    @Delete("DELETE FROM object WHERE File_ID = #{file_id}")
    int deleteObject(FileEntity fileEntity);

    @Select("SELECT * FROM object WHERE File_ID = #{File_ID}")
    List<ObjectEntity> getObjectsByFileID(FileEntity file);
}
