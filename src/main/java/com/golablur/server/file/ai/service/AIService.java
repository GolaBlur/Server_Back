package com.golablur.server.file.ai.service;

import com.golablur.server.file.overall.domain.FileEntity;
import com.golablur.server.file.overall.domain.ObjectEntity;
import com.golablur.server.file.overall.mapper.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AIService {

    @Autowired
    ObjectMapper mapper;

    public String storeObjects(List<ObjectEntity> objects) {
        for(ObjectEntity object : objects) {
            if(mapper.storeObject(object) == 0){
                log.error("AIService.storeObject failed");
                return null;
            }
        }
        return "200";
    }
}
