package com.golablur.server.file.ai.service.object.detection;

import com.golablur.server.file.overall.domain.FileEntity;
import com.golablur.server.file.overall.domain.ObjectEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ObjectDetectionService {
    // restTemplate
    @Autowired
    private RestTemplate restTemplate;

    public List<ObjectEntity> detectObjects(FileEntity file) {
        return null;
    }

}
