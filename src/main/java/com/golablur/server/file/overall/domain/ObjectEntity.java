package com.golablur.server.file.overall.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ObjectEntity {
    private String Object_ID;
    private String File_ID;
    private String User_ID;
    private String Object_Name;
    private String File_Extension;
    private String Path;
}
