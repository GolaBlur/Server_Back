package com.golablur.server.file.overall.restcontroller;

import com.golablur.server.file.ai.divider.DeepFakeDivider;
import com.golablur.server.file.overall.domain.FileEntity;
import com.golablur.server.file.overall.domain.ProcessingFileObjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/file/process/deepFake")
@CrossOrigin("*")
public class DeepFakeProcessRestController {

    @Autowired
    private DeepFakeDivider deepFakeDivider;

    // 하나의 이미지
    @RequestMapping("/one/image")
    public FileEntity deepFakeOneImage(ProcessingFileObjectDTO fileObject){
        return deepFakeDivider.deepFakeOneImage(fileObject);
    }

    // 하나의 비디오
    @RequestMapping("/one/video")
    public FileEntity deepFakeOneVideo(ProcessingFileObjectDTO fileObject){
        return deepFakeDivider.deepFakeOneVideo(fileObject);
    }


    // 여러 장의 이미지
    @RequestMapping("/alot/images")
    public List<FileEntity> deepFakeALotImages(List<ProcessingFileObjectDTO> fileObjectList){
        return deepFakeDivider.deepFakeALotImages(fileObjectList);
    }


}
