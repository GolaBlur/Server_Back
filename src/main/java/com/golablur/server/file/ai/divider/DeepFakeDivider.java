package com.golablur.server.file.ai.divider;

import com.golablur.server.file.ai.service.StoreObjectService;
import com.golablur.server.file.ai.service.deepFake.DeepFakeService;
import com.golablur.server.file.loader.service.storeFileData.StoreFileDataService;
import com.golablur.server.file.overall.domain.AIFunctionDTO;
import com.golablur.server.file.overall.domain.FileObjectDTO;
import com.golablur.server.file.overall.domain.ProcessingFileObjectDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class DeepFakeDivider {


    @Autowired
    private StoreObjectService storeObjectService;
    @Autowired
    private StoreFileDataService storeFileDataService;
    @Autowired
    private DeepFakeService deepFakeService;

    // TODO 딥페이크
    // 사람 딥페이크 후 반환

    public FileObjectDTO deepFakeOneImage(ProcessingFileObjectDTO fileObject) {
        // DB에 접근하여 AIFUnctionDTO 를 채운다.
        AIFunctionDTO aiFunctionDTO = storeObjectService.getAIFunctionDTO(fileObject);
        if (aiFunctionDTO == null) {
            log.error("deepFakeOneImage getAIFunctionDTO failed");
            return null;
        }
        // AIFUnctionDTO 를 AI API 로 전송하고 처리된 파일을 반환 받는다.
        FileObjectDTO fileObjectDTO = DeepFakeService.processFakeOneImage(aiFunctionDTO);
        if(fileObjectDTO == null){
            log.error("deepFakeOneImage process failed");
            return null;
        }
        // 처리된 파일을 DB에 저장한다.
        if(storeFileDataService.storeFile(fileObjectDTO.getFile()).equals("500")){
            log.error("deepFakeOneImage storeFile failed");
            return null;
        }
        log.info("deepFakeOneImage successful");
        return fileObjectDTO;
    }


    public FileObjectDTO deepFakeOneVideo(ProcessingFileObjectDTO fileObject) {
        // DB에 접근하여 AIFUnctionDTO 를 채운다.
        AIFunctionDTO aiFunctionDTO = storeObjectService.getAIFunctionDTO(fileObject);
        if (aiFunctionDTO == null) {
            log.error("deepFakeOneVideo getAIFunctionDTO failed");
            return null;
        }
        // AIFUnctionDTO 를 AI API 로 전송하고 처리된 파일을 반환 받는다.
        FileObjectDTO fileObjectDTO = DeepFakeService.processFakeOneVideo(aiFunctionDTO);
        if (fileObjectDTO == null) {
            log.error("deepFakeOneVideo process failed");
            return null;
        }
        // 처리된 파일을 DB에 저장한다.
        storeFileDataService.storeFile(fileObjectDTO.getFile());
        if(storeFileDataService.storeFile(fileObjectDTO.getFile()).equals("500")){
            log.error("deepFakeOneVideo storeFile failed");
            return null;
        }
        log.info("deepFakeOneVideo successful");
        return null;
    }

    public List<FileObjectDTO> deepFakeALotImages(List<ProcessingFileObjectDTO> fileObjectList) {
        // 하나의 이미지 처리를 반복
        List<FileObjectDTO> processedList = new ArrayList<>();
        int cnt = 0;
        for(ProcessingFileObjectDTO fileObject : fileObjectList){
            FileObjectDTO fileObjectDTO = deepFakeOneImage(fileObject);
            if (fileObjectDTO == null) {
                log.error("deepFakeALotImages failed : index "+cnt);
            }
            processedList.add(fileObjectDTO);
            cnt++;
        }
        log.info("deepFakeALotImages successful");
        return processedList;
    }

}
