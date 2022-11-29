package com.golablur.server.file.ai.divider;

import com.golablur.server.file.ai.service.ObjectService;
import com.golablur.server.file.ai.service.SendToAPIService;
import com.golablur.server.file.loader.service.storeFileData.StoreFileDataService;
import com.golablur.server.file.overall.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class DeepFakeDivider {


    @Autowired
    private ObjectService objectService;
    @Autowired
    private StoreFileDataService storeFileDataService;
    @Autowired
    private SendToAPIService send;

    // 사람 딥페이크 후 반환

//    public FileEntity deepFakeOneImage(ProcessingFileObjectDTO fileObject) {
//        // DB에 접근하여 AIFUnctionDTO 를 채운다.
//        FileObjectDTO fileObjectDTO = objectService.getFileObjectDTO(fileObject);
//        if (fileObjectDTO == null) {
//            log.error("deepFakeOneImage getAIFunctionDTO failed");
//            return null;
//        }
//        // AIFUnctionDTO 를 AI API 로 전송하고 처리된 파일을 반환 받는다.
//        FileEntity processedFile = send.processFakeOneImage(fileObjectDTO);
//        if(processedFile == null){
//            log.error("deepFakeOneImage process failed");
//            return null;
//        }
//        // 처리된 파일을 DB에 저장한다.
//        if(storeFileDataService.storeFile(processedFile).equals("500")){
//            log.error("deepFakeOneImage storeFile failed");
//            return null;
//        }
//        storeFileDataService.updateProcessedFileData(processedFile.getOriginal_File_ID());
//        log.info("deepFakeOneImage successful");
//        return processedFile;
//    }
//
//
//    public FileEntity deepFakeOneVideo(ProcessingFileObjectDTO fileObject) {
//        // DB에 접근하여 AIFUnctionDTO 를 채운다.
//        FileObjectDTO fileObjectDTO = objectService.getFileObjectDTO(fileObject);
//        if (fileObjectDTO == null) {
//            log.error("deepFakeOneVideo getAIFunctionDTO failed");
//            return null;
//        }
//        // AIFUnctionDTO 를 AI API 로 전송하고 처리된 파일을 반환 받는다.
//        FileEntity processedFile = send.processFakeOneVideo(fileObjectDTO);
//        if (processedFile == null) {
//            log.error("deepFakeOneVideo process failed");
//            return null;
//        }
//        // 처리된 파일을 DB에 저장한다.
//        if(storeFileDataService.storeFile(processedFile).equals("500")){
//            log.error("deepFakeOneVideo storeFile failed");
//            return null;
//        }
//        storeFileDataService.updateProcessedFileData(processedFile.getOriginal_File_ID());
//        log.info("deepFakeOneVideo successful");
//        return processedFile;
//    }
//
//    public List<FileEntity> deepFakeALotImages(List<ProcessingFileObjectDTO> fileObjectList) {
//        // 하나의 이미지 처리를 반복
//        List<FileEntity> processedList = new ArrayList<>();
//        int cnt = 0;
//        for(ProcessingFileObjectDTO fileObject : fileObjectList){
//            FileEntity fileEntity = deepFakeOneImage(fileObject);
//            if (fileEntity == null) {
//                log.error("deepFakeALotImages failed : index "+cnt);
//            }
//            processedList.add(fileEntity);
//            cnt++;
//        }
//        log.info("deepFakeALotImages successful");
//        return processedList;
//    }


    public FileEntity deepFakeOneImage(DeepFakeDTO deepFakeDTO) {
        // 데이터베이스에서 FileEntity 채우기
        log.info("deepFakeOneImage deepFakeDTO : "+deepFakeDTO.toString());
        DeepFakeFileEntityDTO deepFakeFileEntityDTO =
                objectService.getDeepFakeFileEntity(deepFakeDTO);

        // AI api 에 요청
        FileEntity processedFile = send.processFakeOneImage(deepFakeFileEntityDTO);
        log.info("deepFakeOneImage processedFile : "+processedFile.toString());

        // DB에 처리된 파일 정보 저장
        storeFileDataService.storeFile(processedFile);

        return processedFile;
    }

    public FileEntity deepFakeOneVideo(DeepFakeDTO deepFakeDTO) {
        // 데이터베이스에서 FileEntity 채우기
        log.info("deepFakeOneVideo deepFakeDTO : "+ deepFakeDTO.toString());
        DeepFakeFileEntityDTO deepFakeFileEntityDTO =
                objectService.getDeepFakeFileEntity(deepFakeDTO);

        // AI api 에 요청
        FileEntity processedFile = send.processFakeOneVideo(deepFakeFileEntityDTO);
        log.info("deepFakeOneVideo processedFile : "+processedFile.toString());

        // DB에 처리된 파일 정보 저장
        storeFileDataService.storeFile(processedFile);

        return processedFile;
    }

    public List<FileEntity> deepFakeALotImages(List<DeepFakeDTO> deepFakeDTOList) {
        // 1개 이미지 반복
        List<FileEntity> processedList = new ArrayList<>();
        for(DeepFakeDTO deepFakeDTO : deepFakeDTOList){
            log.info("deepFakeDTO: " + deepFakeDTO.toString());
            processedList.add(deepFakeOneImage(deepFakeDTO));
        }
        return processedList;
    }

}
