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
    @NotNull String File_ID;
    @NotNull String User_ID;
    String Original_File_ID;
    @NotNull String Real_File_Name;
    String Group_ID;
    @NotNull String File_Extension;
    @NotNull String Path;
    Date Sysdate;
}
