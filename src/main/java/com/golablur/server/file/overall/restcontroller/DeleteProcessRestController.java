package com.golablur.server.file.overall.restcontroller;

import com.golablur.server.file.ai.divider.LatentDiffusionDivider;
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
@RequestMapping("/file/process/delete")
@CrossOrigin("*")
public class DeleteProcessRestController {

    @Autowired
    private LatentDiffusionDivider latentDiffusionDivider;

    // 하나의 이미지
    @RequestMapping("/one/image")
    public FileEntity deleteOneImage(ProcessingFileObjectDTO fileObject){
        return latentDiffusionDivider.deleteOneImage(fileObject);
    }

    // 여러 장의 이미지
    @RequestMapping("/alot/images")
    public List<FileEntity> deleteALotImages(List<ProcessingFileObjectDTO> fileObjectList){
        return latentDiffusionDivider.deleteALotImages(fileObjectList);
    }


}
