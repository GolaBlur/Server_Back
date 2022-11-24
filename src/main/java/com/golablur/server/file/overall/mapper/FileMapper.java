package com.golablur.server.file.overall.mapper;

import com.golablur.server.file.overall.domain.FileEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {


    // Upload File Data
    @Insert("INSERT INTO file (file_ID, user_ID, real_File_Name, group_ID, file_Extension, path) " +
            "VALUES (#{file_ID}, #{user_ID}, #{real_File_Name},#{group_ID} , #{file_Extension}, #{path})")
    int uploadOriginalFile(FileEntity file);

    // Upload Resultant File Data
    @Insert("INSERT INTO file (file_ID, user_ID, real_File_Name, group_ID, file_Extension, path)" +
            "VALUES (#{file_ID}, #{user_ID}, #{real_File_Name},#{group_ID} , #{file_Extension}, #{path})")
    int uploadResultantFile(FileEntity file);


    // Get File Data by user_id
    @Select("SELECT * FROM file WHERE user_ID = #{user_id}")
    List<FileEntity> getFileDataByUser_ID(@Param("user_id") String user_id);

    // Get File Data by file_id
    @Select("SELECT * FROM file WHERE file_ID = #{file_id}")
    FileEntity getFileDataByFile_ID(@Param("file_id") String file_id);

    // Get File Data by group_id
    @Select("SELECT * FROM file WHERE group_ID = #{group_id}")
    List<FileEntity> getFileDataByGroup_ID(@Param("group_id") String group_id);


    @Delete("DELETE FROM file WHERE user_ID = #{id}")
    int deleteFile(@Param("id") String user_id);

    @Select("SELECT DISTINCT group_ID FROM file WHERE user_ID = #{id} and original_File_ID IS NULL and group_ID IS NOT NULL")
    List<String> getNonProcessedGroupByUser_ID(@Param("id") String user_id);

    @Select("SELECT * FROM file WHERE user_ID = #{id} and original_File_ID IS NOT NULL Order by sysdate")
    List<FileEntity> getProcessedFileDataByUser_ID(@Param("id") String user_id);

    @Select("SELECT * FROM file " +
            "WHERE user_ID = #{id} and original_File_ID IS NULL and group_ID IS NULL and " +
            "(file_Extension = '.jpg' OR file_Extension = '.jpeg' OR file_Extension = '.png')")
    List<FileEntity> getNonProcessedImageDataByUser_ID(@Param("id") String user_id);

    @Select("SELECT * FROM file " +
            "where user_ID = #{id} and original_File_ID IS NULL and group_ID IS NULL and " +
            "file_Extension = '.mp4'")
    List<FileEntity> getNonProcessedVideoByUser_ID(@Param("id") String user_id);

    @Update("UPDATE file SET original_File_ID = #{id} WHERE file_ID = #{id}")
    int updateProcessedFileData(@Param("id") String original_id);
}
