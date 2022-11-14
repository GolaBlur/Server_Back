package com.golablur.server.file.overall.restcontroller;

import com.golablur.server.file.ai.divider.AIDivider;
import com.golablur.server.file.loader.divider.LoaderDivider;
import com.golablur.server.file.overall.domain.FileEntity;
import com.golablur.server.file.overall.domain.ObjectEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/file/loader")
@CrossOrigin("*")
@Slf4j
public class FileLoaderRestController {

    @Autowired
    LoaderDivider loaderDivider;
    @Autowired
    AIDivider aiDivider;


    // 하나의 파일 업로드 및 객체 탐지 후 반환
    @RequestMapping(value = "/upload/one", method= RequestMethod.GET)
    public List<ObjectEntity> uploadOne(FileEntity file){
        // 업로드
        if(loaderDivider.uploadOne(file).equals("500")) {
            log.error("uploadOne failed");
            return null;
        }
        // 객체 탐지 후 반환
        List<ObjectEntity> objectList = aiDivider.getObjects();
        if(objectList == null) {
            log.error("getObjects failed");
            return null;
        }
        return objectList;
    }

    // 여러 개의 파일 업로드 및 객체 탐지 후 반환
    @RequestMapping(value = "/upload/list", method= RequestMethod.GET)
    public List<List<ObjectEntity>> uploadList(List fileList){
        // 업로드
        if(loaderDivider.uploadList(fileList).equals("500")){
            log.error("uploadList failed");
            return null;
        }
        // 객체 탐지 후 반환
        List<List<ObjectEntity>> objectList = aiDivider.getObjectsList();
        if(objectList == null) {
            log.error("getObjectsList failed");
            return null;
        }
        return objectList;
    }

    // 결과물 다운로드를 위한 파일 데이터 반환
    @RequestMapping(value = "/download/one", method= RequestMethod.GET)
    public FileEntity downloadFileOne(@RequestParam("File_ID") String File_ID){
        return loaderDivider.getOneFileData(File_ID);
    }

    // 그룹 결과물 다운로드를 위한 파일 데이터 반환
    @RequestMapping(value = "/download/list", method= RequestMethod.GET)
    public List downloadFileList(@RequestParam("Group_ID") String Group_ID){
        return loaderDivider.getFileListData(Group_ID);
    }

    // 결과물 페이지를 위한 결과물 리스트 반환
    @RequestMapping(value = "/get/result/list", method= RequestMethod.GET)
    public List getResultList(@RequestParam("User_ID") String User_ID){
        return loaderDivider.getResultList(User_ID);
    }


}
