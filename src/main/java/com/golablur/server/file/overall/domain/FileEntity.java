package com.golablur.server.file.overall.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileEntity {
    private String File_ID;
    private String User_ID;
    private String Original_File_ID;
    private String Real_File_Name;
    private String Group_ID;
    private String File_Extension;
    private String Path;
    private Date Sysdate;
}
