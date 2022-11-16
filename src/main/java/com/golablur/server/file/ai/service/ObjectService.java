package com.golablur.server.file.ai.service;

import com.golablur.server.file.overall.domain.*;
import com.golablur.server.file.overall.mapper.FileMapper;
import com.golablur.server.file.overall.mapper.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ObjectService {
    // TODO - DB 접근을 위한 서비스

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    FileMapper fileMapper;



    public String storeObjects(List<ObjectEntity> objects) {
        for(ObjectEntity object : objects) {
            if(objectMapper.storeObject(object) == 0){
                log.error("AIService.storeObject failed");
                return null;
            }
        }
        log.info("AIService.storeObject successful");
        return "200";
    }

    public AIFunctionDTO getAIFunctionDTO(ProcessingFileObjectDTO fileObject) {
        List<ObjectEntity> objectList = new ArrayList<>();
        for (String id : fileObject.getObject_IDList()) {
            ObjectEntity object = objectMapper.getObjectListByObjectID(id);
            if(object == null){
                log.error("ObjectService getAIFunctionDTO failed");
            }
            objectList.add(object);
        }
        return AIFunctionDTO.builder()
                .file(fileMapper.getFileDataByFile_ID(fileObject.getFile_ID()))
                .processingObjectList(objectList)
                .build();
    }

    // FileObjectDTO => FileEntity, List<ObjectEntity> -> Detection/Processed Object List
    public FileObjectDTO returnFileObjectByFile(FileEntity file) {
        return FileObjectDTO.builder()
                .file(file)
                .detectionObjectList(objectMapper.getDetectionObjectListByFile(file))
                .processedObjectList(objectMapper.getProcessedObjectListByFile(file))
                .build();
    }

}