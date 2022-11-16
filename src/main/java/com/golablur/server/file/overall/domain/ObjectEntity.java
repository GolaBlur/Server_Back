package com.golablur.server.file.overall.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ObjectEntity {
    @NotNull private String Object_ID;
    @NotNull private String File_ID;
    @NotNull private String Object_Name;
    @NotNull private String Object_Count;
//    @NotNull private boolean Processed;
    @NotNull private String Path;
}
