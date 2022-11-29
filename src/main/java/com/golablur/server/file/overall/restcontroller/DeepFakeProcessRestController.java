package com.golablur.server.file.overall.restcontroller;

import com.golablur.server.file.ai.divider.DeepFakeDivider;
import com.golablur.server.file.overall.domain.DeepFakeDTO;
import com.golablur.server.file.overall.domain.FileEntity;
import com.golablur.server.file.overall.domain.ProcessingFileObjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public FileEntity deepFakeOneImage(DeepFakeDTO deepFakeDTO){
        return deepFakeDivider.deepFakeOneImage(deepFakeDTO);
    }

    // 하나의 비디오
    @RequestMapping("/one/video")
    public FileEntity deepFakeOneVideo(DeepFakeDTO deepFakeDTO){
        return deepFakeDivider.deepFakeOneVideo(deepFakeDTO);
    }


    // TODO 여러 장의 이미지
    @RequestMapping("/alot/images")
    public List<FileEntity> deepFakeALotImages(List<DeepFakeDTO> deepFakeDTOList){
        return deepFakeDivider.deepFakeALotImages(deepFakeDTOList);
    }


}
