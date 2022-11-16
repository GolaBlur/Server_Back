package com.golablur.server.file.ai.divider;

import com.golablur.server.file.ai.service.StoreObjectService;
import com.golablur.server.file.ai.service.deepFake.DeepFakeService;
import com.golablur.server.file.ai.service.latentDiffusion.LatentDiffusionService;
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
public class LatentDiffusionDivider {

    @Autowired
    private StoreObjectService storeObjectService;
    @Autowired
    private StoreFileDataService storeFileDataService;
    @Autowired
    private LatentDiffusionService latentDiffusionService;

    // TODO 객체 삭제
    // 객체 삭제 후 반환


    public FileObjectDTO deleteOneImage(ProcessingFileObjectDTO fileObject) {
        // DB에 접근하여 AIFUnctionDTO 를 채운다.
        AIFunctionDTO aiFunctionDTO = storeObjectService.getAIFunctionDTO(fileObject);
        // AIFUnctionDTO 를 AI API 로 전송하고 처리된 파일을 반환 받는다.
        FileObjectDTO fileObjectDTO = latentDiffusionService.processDeleteOneImage(aiFunctionDTO);
        if(fileObjectDTO == null){
            log.error("deleteOneImage process failed");
            return null;
        }
        // 처리된 파일을 DB에 저장한다.
        if(storeFileDataService.storeFile(fileObjectDTO.getFile()).equals("500")){
            log.error("deleteOneImage storeFile failed");
            return null;
        }
        log.info("DeleteOneImage successful");
        return null;
    }


    public List<FileObjectDTO> deleteALotImages(List<ProcessingFileObjectDTO> fileObjectList) {
        // 하나의 이미지 처리를 반복
        List<FileObjectDTO> processedList = new ArrayList<>();
        int cnt = 0;
        for(ProcessingFileObjectDTO fileObject : fileObjectList){
            FileObjectDTO fileObjectDTO = deleteOneImage(fileObject);
            if (fileObjectDTO == null) {
                log.error("deleteALotImages failed : index "+cnt);
            }
            processedList.add(fileObjectDTO);
            cnt++;
        }
        log.info("DeleteALotImages successful");
        return processedList;
    }

}
