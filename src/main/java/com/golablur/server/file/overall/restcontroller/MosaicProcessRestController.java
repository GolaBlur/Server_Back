package com.golablur.server.file.overall.restcontroller;

import com.golablur.server.file.ai.divider.MosaicDivider;
import com.golablur.server.file.overall.domain.FileEntity;
import com.golablur.server.file.overall.domain.ProcessingFileObjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/file/process/mosaic")
@CrossOrigin("*")
public class MosaicProcessRestController {

    @Autowired
    private MosaicDivider mosaicDivider;


    // 하나의 이미지
    @RequestMapping("/one/image")
    public FileEntity mosaicOneImage(ProcessingFileObjectDTO fileObject){
        return mosaicDivider.mosaicOneImage(fileObject);
    }

    // 하나의 비디오
    @RequestMapping("/one/video")
    public FileEntity mosaicOneVideo(ProcessingFileObjectDTO fileObject){
        return mosaicDivider.mosaicOneVideo(fileObject);
    }


    // 여러 장의 이미지
    @RequestMapping("/alot/images")
    public List<FileEntity> mosaicALotImages(List<ProcessingFileObjectDTO> fileObjectList){
        return mosaicDivider.mosaicALotImages(fileObjectList);
    }


}
