package com.golablur.server.file.overall.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileEntity {
    @NotNull private String File_ID;
    @NotNull private String User_ID;
    private String Original_File_ID;
    @NotNull private String Real_File_Name;
    private String Group_ID;
    @NotNull private String File_Extension;
    @NotNull private String Path;
    private Date Sysdate;
}
