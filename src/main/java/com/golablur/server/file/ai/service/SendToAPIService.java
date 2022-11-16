package com.golablur.server.file.ai.service;

import com.golablur.server.file.overall.domain.AIFunctionDTO;
import com.golablur.server.file.overall.domain.FileEntity;
import com.golablur.server.file.overall.domain.ObjectEntity;
import com.golablur.server.file.overall.mapper.FileMapper;
import com.golablur.server.file.overall.mapper.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class SendToAPIService {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    FileMapper fileMapper;
    @Autowired
    ObjectMapper objectMapper;

    @Value("${ai_api_url}")
    private String AI_API_URL;


    // TODO 딥페이크
    public FileEntity processFakeOneImage(AIFunctionDTO aiFunctionDTO) {
        // AI API 에서 처리 후 처리된 파일 데이터 반환받음
        return restTemplate
                .postForEntity(AI_API_URL+"/process/deepFake/one/image", aiFunctionDTO, FileEntity.class)
                .getBody();
    }

    public FileEntity processFakeOneVideo(AIFunctionDTO aiFunctionDTO) {
        // AI API 에서 처리 후 처리된 파일 데이터 반환받음
        return restTemplate
                .postForEntity(AI_API_URL+"/process/deepFake/one/video", aiFunctionDTO, FileEntity.class)
                .getBody();
    }


    // TODO 삭제

    public FileEntity processDeleteOneImage(AIFunctionDTO aiFunctionDTO) {
        // AI API 에서 처리 후 처리된 파일 데이터 반환받음
        return restTemplate
                .postForEntity(AI_API_URL+"/process/delete/one/image", aiFunctionDTO, FileEntity.class)
                .getBody();
    }


    // TODO 모자이크

    public FileEntity processMosaicOneImage(AIFunctionDTO aiFunctionDTO) {
        // AI API 에서 처리 후 처리된 파일 데이터 반환받음
        return restTemplate
                .postForEntity(AI_API_URL+"/process/mosaic/one/image", aiFunctionDTO, FileEntity.class)
                .getBody();
    }

    public FileEntity processMosaicOneVideo(AIFunctionDTO aiFunctionDTO) {
        // AI API 에서 처리 후 처리된 파일 데이터 반환받음
        return restTemplate
                .postForEntity(AI_API_URL+"/process/mosaic/one/video", aiFunctionDTO, FileEntity.class)
                .getBody();
    }


    // TODO 객체 탐지

    public List<ObjectEntity> detectObjects(FileEntity fileEntity) {
        // AI API 에서 처리 후 처리된 파일 데이터 반환받음
        return (List<ObjectEntity>) restTemplate
                .postForEntity(AI_API_URL+"/process/detection", fileEntity, List.class)
                .getBody();
    }

}
