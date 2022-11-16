package com.golablur.server.file.ai.service;

import com.golablur.server.file.overall.domain.AIFunctionDTO;
import com.golablur.server.file.overall.domain.ObjectEntity;
import com.golablur.server.file.overall.domain.ProcessingFileObjectDTO;
import com.golablur.server.file.overall.mapper.FileMapper;
import com.golablur.server.file.overall.mapper.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StoreObjectService {
    // TODO - DB 접근을 위한 서비스

    @Autowired
    ObjectMapper Objectmapper;
    @Autowired
    FileMapper fileMapper;



    public String storeObjects(List<ObjectEntity> objects) {
        for(ObjectEntity object : objects) {
            if(Objectmapper.storeObject(object) == 0){
                log.error("AIService.storeObject failed");
                return null;
            }
        }
        log.info("AIService.storeObject successful");
        return "200";
    }

    public AIFunctionDTO getAIFunctionDTO(ProcessingFileObjectDTO fileObject) {
        return null;
    }

}
