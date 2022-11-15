package com.golablur.server.file.overall.restcontroller;

import com.golablur.server.file.ai.divider.AIDivider;
import com.golablur.server.file.loader.divider.LoaderDivider;
import com.golablur.server.file.overall.domain.FileEntity;
import com.golablur.server.file.overall.domain.FileObjectDTO;
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
    private LoaderDivider loaderDivider;
    @Autowired
    private AIDivider aiDivider;


    // 하나의 파일 업로드 및 객체 탐지 후 반환
    @RequestMapping("/upload/one")
    public FileObjectDTO uploadOne(FileEntity file){
        // 업로드
        if(loaderDivider.uploadOne(file).equals("500")) {
            log.error("uploadOne failed");
            return null;
        }
        // 객체 탐지 후 반환
        FileObjectDTO fileObject = aiDivider.getObjects(file);
        if(fileObject == null) {
            log.error("getObjects failed");
            return null;
        }
        return fileObject;
    }

    // 여러 개의 파일 업로드 및 객체 탐지 후 반환
    @RequestMapping("/upload/list")
    public List uploadList(List fileList){
        // 업로드
        if(loaderDivider.uploadList(fileList).equals("500")){
            log.error("uploadList failed");
            return null;
        }
        // 객체 탐지 후 반환
        List fileObjectList = aiDivider.getObjectsList(fileList);
        if(fileObjectList == null) {
            log.error("getObjectsList failed");
            return null;
        }

        return fileObjectList;
    }

    // 결과물 다운로드를 위한 파일 데이터 반환
    @RequestMapping("/download/one")
    public FileEntity downloadFileOne(@RequestParam("File_ID") String File_ID){
        return loaderDivider.getOneFileData(File_ID);
    }

    // 그룹 결과물 다운로드를 위한 파일 데이터 반환
    @RequestMapping("/download/list")
    public List downloadFileList(@RequestParam("Group_ID") String Group_ID){
        return loaderDivider.getFileListData(Group_ID);
    }


    // 결과물 페이지를 위한 결과물 리스트 반환
    @RequestMapping("/get/result/list")
    public List getResultList(@RequestParam("User_ID") String User_ID){
        return loaderDivider.getResultList(User_ID);
    }


}
