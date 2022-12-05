package com.golablur.server.file.overall.restcontroller;

import com.golablur.server.file.ai.divider.LatentDiffusionDivider;
import com.golablur.server.file.overall.domain.*;
import com.golablur.server.file.overall.service.ReplaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/file/process/delete")
@CrossOrigin("*")
public class DeleteProcessRestController {

    @Autowired
    private LatentDiffusionDivider latentDiffusionDivider;
    @Autowired
    private ReplaceService replaceService;


    // 하나의 이미지
    @RequestMapping("/one/image")
    public FileEntity deleteOneImage(@RequestBody ProcessingFileObjectDTO fileObject){
        return latentDiffusionDivider.deleteOneImage(fileObject);
    }

    // 여러 장의 이미지
    @RequestMapping("/alot/images")
    public List<FileEntity> deleteALotImages(List<ProcessingFileObjectDTO> fileObjectList){
        return latentDiffusionDivider.deleteALotImages(fileObjectList);
    }

    // 클라이언트에서 유저가 삭제 후 저장버튼을 클릭했을 때
    @RequestMapping("/edit/image/save")
    public String saveEditedImage(@RequestBody ReplaceResultDTO replaceResultDTO){
        return replaceService.replaceResult(replaceResultDTO);
    }



}
