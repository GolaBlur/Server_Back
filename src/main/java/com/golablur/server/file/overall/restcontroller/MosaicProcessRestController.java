package com.golablur.server.file.overall.restcontroller;

import com.golablur.server.file.ai.divider.MosaicDivider;
import com.golablur.server.file.overall.domain.FileEntity;
import com.golablur.server.file.overall.domain.FileObjectDTO;
import com.golablur.server.file.overall.domain.ProcessingFileObjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/file/process/mosaic")
@CrossOrigin("*")
public class MosaicProcessRestController {
    // TODO 객체 모자이크

    @Autowired
    private MosaicDivider mosaicDivider;


    // 하나의 이미지
    @RequestMapping("/one/image")
    public FileObjectDTO mosaicOneImage(ProcessingFileObjectDTO fileObject){
        return mosaicDivider.mosaicOneImage(fileObject);
    }

    // 하나의 비디오
    @RequestMapping("/one/video")
    public FileObjectDTO mosaicOneVideo(ProcessingFileObjectDTO fileObject){
        return mosaicDivider.mosaicOneVideo(fileObject);
    }


    // 여러 장의 이미지
    @RequestMapping("/alot/images")
    public List<FileObjectDTO> mosaicALotImages(List<ProcessingFileObjectDTO> fileObjectList){
        return mosaicDivider.mosaicALotImages(fileObjectList);
    }


}
