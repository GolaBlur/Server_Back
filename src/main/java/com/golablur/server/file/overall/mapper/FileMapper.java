package com.golablur.server.file.overall.mapper;

import com.golablur.server.file.overall.domain.FileEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FileMapper {


    // Upload File Data
    @Insert("INSERT INTO file (File_ID, User_ID, Real_File_Name, Group_ID, File_Extension, Path) " +
            "VALUES (#{File_ID}, #{User_ID}, #{Real_File_Name},#{Group_ID} , #{File_Extension}, #{Path})")
    int uploadOriginalFile(FileEntity file);

    // Upload Resultant File Data
    @Insert("INSERT INTO file (File_ID, User_ID, Real_File_Name, Group_ID, File_Extension, Path)" +
            "VALUES (#{File_ID}, #{User_ID}, #{Real_File_Name},#{Group_ID} , #{File_Extension}, #{Path})")
    int uploadResultantFile(FileEntity file);


    // Get File Data by user_id
    @Select("SELECT * FROM file WHERE User_ID = #{user_id}")
    List<FileEntity> getFileDataByUser_ID(@Param("user_id") String user_id);

    // Get File Data by file_id
    @Select("SELECT * FROM file WHERE File_ID = #{file_id}")
    FileEntity getFileDataByFile_ID(@Param("file_id") String file_id);

    // Get File Data by group_id
    @Select("SELECT * FROM file WHERE Group_ID = #{group_id}")
    List<FileEntity> getFileDataByGroup_ID(@Param("group_id") String group_id);


    @Delete("DELETE FROM file WHERE User_ID = #{id}")
    int deleteFile(@Param("id") String user_id);

    @Select("SELECT DISTINCT Group_ID FROM file WHERE User_ID = #{id} and Original_File_ID IS NULL and Group_ID IS NOT NULL")
    List<String> getNonProcessedGroupByUser_ID(@Param("id") String user_id);

    @Select("SELECT * FROM file WHERE User_ID = #{id} and Original_File_ID IS NOT NULL Order by Sysdate")
    List<FileEntity> getProcessedFileDataByUser_ID(@Param("id") String user_id);

    @Select("SELECT * FROM file " +
            "WHERE User_ID = #{id} and Original_File_ID IS NULL and " +
            "(File_Extension = '.jpg' OR File_Extension = '.jpeg' OR File_Extension = '.png')")
    List<FileEntity> getNonProcessedImageDataByUser_ID(@Param("id") String user_id);

    @Select("SELECT * FROM file where User_ID = #{id} and Original_File_ID IS NULL and " +
            "File_Extension = '.mp4'")
    List<FileEntity> getNonProcessedVideoByUser_ID(@Param("id") String user_id);

    @Update("UPDATE file SET Original_File_ID = #{id} WHERE File_ID = #{id}")
    int updateProcessedFileData(@Param("id") String original_id);
}
