package com.golablur.server.file.overall.mapper;

import com.golablur.server.file.overall.domain.FileEntity;
import com.golablur.server.file.overall.domain.ObjectEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ObjectMapper {

    @Insert("INSERT INTO object (object_ID, file_ID, user_ID, object_Name, file_Extension, path)" +
            " VALUES (#{object_ID}, #{file_ID}, #{user_ID}, #{object_Name}, #{file_Extension}, #{path})")
    int storeObject(ObjectEntity object);

    @Delete("DELETE FROM object WHERE file_ID = #{file_id}")
    int deleteObject(FileEntity fileEntity);

    @Select("SELECT * FROM object WHERE file_ID = #{file_ID}")
    List<ObjectEntity> getDetectionObjectListByFile(FileEntity file);

    @Select("SELECT * FROM object WHERE object_ID = #{id}")
    ObjectEntity getObjectListByObjectID(@Param("id") String object_ID);

    @Select("SELECT * FROM object WHERE object_ID = #{id}")
    ObjectEntity getObjectByObjectID(@Param("id") String id);
}
