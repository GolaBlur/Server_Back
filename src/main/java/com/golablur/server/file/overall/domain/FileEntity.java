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
    String File_ID;
    String User_ID;
    String Original_File_ID;
    String Real_File_Name;
    String Group_ID;
    String File_Extension;
    String Path;
    Date Sysdate;
}
