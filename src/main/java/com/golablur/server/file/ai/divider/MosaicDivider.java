package com.golablur.server.file.ai.divider;

import com.golablur.server.file.ai.service.ObjectService;
import com.golablur.server.file.ai.service.SendToAPIService;
import com.golablur.server.file.loader.service.storeFileData.StoreFileDataService;
import com.golablur.server.file.overall.domain.AIFunctionDTO;
import com.golablur.server.file.overall.domain.FileEntity;
import com.golablur.server.file.overall.domain.FileObjectDTO;
import com.golablur.server.file.overall.domain.ProcessingFileObjectDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class MosaicDivider {

    @Autowired
    private ObjectService objectService;
    @Autowired
    private StoreFileDataService storeFileDataService;
    @Autowired
    private SendToAPIService send;


    // TODO 객체 모자이크
    //

    public FileObjectDTO mosaicOneImage(ProcessingFileObjectDTO fileObject) {
        // DB에 접근하여 AIFUnctionDTO 를 채운다.
        AIFunctionDTO aiFunctionDTO = objectService.getAIFunctionDTO(fileObject);
        // AIFUnctionDTO 를 AI API 로 전송하고 처리된 파일을 반환 받는다.
        FileEntity processedFile = send.processMosaicOneImage(aiFunctionDTO);
        if(processedFile == null){
            log.error("mosaicOneImage process failed");
            return null;
        }
        // 처리된 파일을 DB에 저장한다.
        if(storeFileDataService.storeFile(processedFile).equals("500")){
            log.error("mosaicOneImage storeFile failed");
            return null;
        }
        log.info("MosaicOneImage successful");
        // 처리된 object 들의 processedFileID 값을 수정해준다.

        return objectService.returnFileObjectByFile(processedFile);
    }


    public FileObjectDTO mosaicOneVideo(ProcessingFileObjectDTO fileObject) {
        // DB에 접근하여 AIFUnctionDTO 를 채운다.
        AIFunctionDTO aiFunctionDTO = objectService.getAIFunctionDTO(fileObject);
        // AIFUnctionDTO 를 AI API 로 전송하고 처리된 파일을 반환 받는다.
        FileEntity processedFile = send.processMosaicOneVideo(aiFunctionDTO);
        if(processedFile == null){
            log.error("mosaicOneVideo process failed");
            return null;
        }
        // 처리된 파일을 DB에 저장한다.
        if(storeFileDataService.storeFile(processedFile).equals("500")){
            log.error("mosaicOneVideo storeFile failed");
            return null;
        }
        log.info("MosaicOneVideo successful");
        // 처리된 object 들의 processedFileID 값을 수정해준다.

        return objectService.returnFileObjectByFile(processedFile);
    }

    public List<FileObjectDTO> mosaicALotImages(List<ProcessingFileObjectDTO> fileObjectList) {
        // 하나의 이미지 처리를 반복
        List<FileObjectDTO> processedList = new ArrayList<>();
        int cnt = 0;
        for(ProcessingFileObjectDTO fileObject : fileObjectList){
            FileObjectDTO fileObjectDTO = mosaicOneImage(fileObject);
            if (fileObjectDTO == null) {
                log.error("mosaicALotImages failed : index "+cnt);
            }
            processedList.add(fileObjectDTO);
            cnt++;
        }
        log.info("MosaicALotImages successful");
        // 처리된 object 들의 processedFileID 값을 수정해준다.

        return processedList;
    }

}
