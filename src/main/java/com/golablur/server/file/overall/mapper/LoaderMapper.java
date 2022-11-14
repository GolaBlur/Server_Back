package com.golablur.server.file.overall.mapper;

import com.golablur.server.file.overall.domain.FileEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LoaderMapper {


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
}
