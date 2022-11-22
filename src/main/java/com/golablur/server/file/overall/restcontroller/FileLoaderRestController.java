package com.golablur.server.file.overall.restcontroller;

import com.golablur.server.file.ai.divider.ObjectDetectionDivider;
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
    private ObjectDetectionDivider objectDetectionDivider;


    // 하나의 파일 업로드 및 객체 탐지 후 반환
    @PostMapping("/upload/one")
    public FileObjectDTO uploadOne(FileEntity fileEntity){
        // 업로드
        log.info("uploadOne"+ fileEntity);
        loaderDivider.uploadOne(fileEntity);
        // 객체 탐지 후 반환
        return objectDetectionDivider.getObjects(fileEntity);
    }

    // 여러 개의 파일 업로드 및 객체 탐지 후 반환
    @RequestMapping("/upload/list")
    public List<FileObjectDTO> uploadList(List<FileEntity> fileList){
        // 업로드
        loaderDivider.uploadList(fileList);
        // 객체 탐지 후 반환
        return objectDetectionDivider.getObjectsList(fileList);
    }

    // 결과물 다운로드를 위한 파일 데이터 반환
    @RequestMapping("/download/one")
    public FileEntity downloadFileOne(@RequestParam("File_ID") String File_ID){
        return loaderDivider.getOneFileData(File_ID);
    }

    // 그룹 결과물 다운로드를 위한 파일 데이터 반환
    @RequestMapping("/download/list")
    public List<FileEntity> downloadFileList(@RequestParam("Group_ID") String Group_ID){
        return loaderDivider.getFileListData(Group_ID);
    }



    // 이미지 커스텀 편집기를 통해 편집된 이미지를 DB에 저장
    @RequestMapping("/save/custom")
    public String saveCustomImage(@RequestParam("result") FileEntity result){
        return loaderDivider.saveCustomImage(result);
    }

}
