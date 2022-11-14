package com.golablur.server.file.ai.divider;

import com.golablur.server.file.ai.service.function.object.detection.ObjectDetectionService;
import com.golablur.server.file.overall.domain.ObjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AIDivider {

    @Autowired
    ObjectDetectionService objectDetectionService;

    public List<ObjectEntity> getObjects() {
        return null;
    }

    public List<List<ObjectEntity>> getObjectsList() {
        return null;
    }
}
