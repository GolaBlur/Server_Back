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

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    FileMapper fileMapper;



    public String storeObjects(List<ObjectEntity> objects) {
        log.info("storeObjects : "+ objects);
        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        for(Object obj : objects) {
            ObjectEntity object= mapper.convertValue(obj, ObjectEntity.class);
            if(objectMapper.storeObject(object) == 0){
                log.error("AIService.storeObject failed");
                return null;
            }
        }
        log.info("AIService.storeObject successful");
        return "200";
    }

    public FileObjectDTO getFileObjectDTO(ProcessingFileObjectDTO fileObject) {
        List<ObjectEntity> objectList = new ArrayList<>();
        log.info("getFileObjectDTO fileObject : " + fileObject );
        for (String id : fileObject.getObject_IDList()) {
            log.info(id);
            ObjectEntity object = objectMapper.getObjectListByObjectID(id);
            if(object == null){
                log.error("ObjectService getAIFunctionDTO failed");
            }
            objectList.add(object);
        }
        return FileObjectDTO.builder()
                .file(fileMapper.getFileDataByFile_ID(fileObject.getFile_ID()))
                .objectList(objectList)
                .build();
    }

    // FileObjectDTO => FileEntity, List<ObjectEntity> -> Detection/Processed Object List
    public FileObjectDTO returnFileObjectByFile(FileEntity file) {
        return FileObjectDTO.builder()
                .file(file)
                .objectList(objectMapper.getDetectionObjectListByFile(file))
                .build();
    }

}
