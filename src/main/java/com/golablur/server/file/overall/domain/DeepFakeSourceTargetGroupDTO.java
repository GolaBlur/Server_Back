package com.golablur.server.file.overall.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeepFakeSourceTargetGroupDTO {
    private List<FileEntity> sourceGroup;
    private ObjectEntity target;
}
