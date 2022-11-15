package com.golablur.server.file.ai.divider;

import com.golablur.server.file.ai.service.AIService;
import com.golablur.server.file.ai.service.deepFake.DeepFakeService;
import com.golablur.server.file.ai.service.latentDiffusion.LatentDiffusionService;
import com.golablur.server.file.ai.service.mosaic.MosaicService;
import com.golablur.server.file.ai.service.object.detection.ObjectDetectionService;
import com.golablur.server.file.overall.domain.FileEntity;
import com.golablur.server.file.overall.domain.FileObjectDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class AIDivider {

    // service
    @Autowired
    private ObjectDetectionService objectDetectionService;
    @Autowired
    private DeepFakeService deepFakeService;
    @Autowired
    private LatentDiffusionService latentDiffusionService;
    @Autowired
    private MosaicService mosaicService;
    @Autowired
    private AIService aiService;




    // TODO 객체 탐지
    // 객체 탐지 후 반환

    // 하나의 파일의 객체 탐지 후 반환
    public FileObjectDTO getObjects(FileEntity fileEntity) {
        // 객체 탐지
        FileObjectDTO fileObjectDTO =
                FileObjectDTO.builder()
                        .file(fileEntity)
                        .detectionObjectList(
                                objectDetectionService.detectObjects(fileEntity)
                        ).build();
        // DB에 저장
        aiService.storeObjects(fileObjectDTO.getDetectionObjectList());
        return fileObjectDTO;
    }
    // 여러 파일의 객체 탐지 후 각 파일 리스트로 반환
    // TODO 개선 방안 : 지금은 각각의 파일들을 차례대로 처리하는 방법이지만
    //            한 번에 보내어 처리한 후 다시 받을 수도 있다. => 퍼포먼스 증가
    public List<FileObjectDTO> getObjectsList(List<FileEntity> fileList) {
        // 객체 탐지 및 DB 저장 반복
        List<FileObjectDTO> fileObjectList = new ArrayList<>();
        FileObjectDTO fileObjectDTO;
        for(FileEntity fileEntity : fileList){
            fileObjectDTO = FileObjectDTO.builder()
                    .file(fileEntity)
                    .detectionObjectList(
                            objectDetectionService.detectObjects(fileEntity)
                    )
                    .build();
            fileObjectList.add(fileObjectDTO);
            aiService.storeObjects(fileObjectDTO.getDetectionObjectList());
        }
        return fileObjectList;
    }


    // TODO 딥페이크
    // 사람 딥페이크 후 반환


    // TODO 객체 삭제
    // 객체 삭제 후 반환



    // TODO 객체 모자이크
    //

}
