package com.golablur.server.file.overall.restcontroller;

import com.golablur.server.file.loader.controller.LoaderController;
import com.golablur.server.file.overall.domain.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/file/load")
@CrossOrigin("*")
public class FileLoaderRestController {

    @Autowired
    LoaderController loaderController;



    // 하나의 이미지 업로드
    @RequestMapping(value = "/one/image/upload", method= RequestMethod.GET)
    public Map uploadImage(FileEntity file){

        return null;
    }

    // 하나의 비디오 업로드
    @RequestMapping(value = "/one/video/upload", method= RequestMethod.GET)
    public Map uploadVideo(FileEntity file){

        return null;
    }

    // 여러 장의 이미지 업로드
    @RequestMapping(value = "/alot/images/upload", method= RequestMethod.GET)
    public Map uploadImages(FileEntity file){

        return null;
    }

    // 결과물 다운로드
    @RequestMapping(value = "/download/result", method= RequestMethod.GET)
    public Map downloadResult(@RequestParam("Origina") String File_ID){

        return null;
    }

    // 결과물 리스트 반환
    @RequestMapping(value = "/get/result/list", method= RequestMethod.GET)
    public Map getResultList(){

        return null;
    }


}
