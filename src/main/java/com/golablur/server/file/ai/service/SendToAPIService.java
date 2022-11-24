package com.golablur.server.file.ai.service;

import com.golablur.server.file.overall.domain.FileEntity;
import com.golablur.server.file.overall.domain.FileObjectDTO;
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

    // AI API URL
    @Value("${deepFakeUrl}")
    private String deepFakeUrl;
    @Value("${deleteUrl}")
    private String deleteUrl;
    @Value("${mosaicUrl}")
    private String mosaicUrl;
    @Value("${detection}")
    private String detectionUrl;



    public FileEntity processFakeOneImage(FileObjectDTO fileObjectDTO) {
        log.info("Processing fake one image");
        // AI API 에서 처리 후 처리된 파일 데이터 반환받음
        return restTemplate
                .postForEntity(deepFakeUrl+"/image/deepFake/execute", fileObjectDTO, FileEntity.class)
                .getBody();
    }

    public FileEntity processFakeOneVideo(FileObjectDTO fileObjectDTO) {
        log.info("Processing fake one video");
        // AI API 에서 처리 후 처리된 파일 데이터 반환받음
        return restTemplate
                .postForEntity(deepFakeUrl+"/video/deepfake/execute", fileObjectDTO, FileEntity.class)
                .getBody();
    }



    public FileEntity processDeleteOneImage(FileObjectDTO fileObjectDTO) {
        log.info("Processing delete one image");
        // AI API 에서 처리 후 처리된 파일 데이터 반환받음
        return restTemplate
                .postForEntity(deleteUrl+"/image/delete/execute", fileObjectDTO, FileEntity.class)
                .getBody();
    }



    public FileEntity processMosaicOneImage(FileObjectDTO fileObjectDTO) {
        log.info("Processing mosaic one image");
        // AI API 에서 처리 후 처리된 파일 데이터 반환받음
        return restTemplate
                .postForEntity(mosaicUrl+"/image/mosaic/execute", fileObjectDTO, FileEntity.class)
                .getBody();
    }

    public FileEntity processMosaicOneVideo(FileObjectDTO fileObjectDTO) {
        log.info("Processing mosaic one video");
        // AI API 에서 처리 후 처리된 파일 데이터 반환받음
        return restTemplate
                .postForEntity(mosaicUrl+"/video/mosaic/execute", fileObjectDTO, FileEntity.class)
                .getBody();
    }



    public List<ObjectEntity> detectObjects(FileEntity fileEntity) {
        log.info("detectObjects");
        // AI API 에서 처리 후 처리된 파일 데이터 반환받음
        return (List<ObjectEntity>) restTemplate
                .postForEntity(detectionUrl+"/image/detection/execute", fileEntity, List.class)
                .getBody();
    }

}
