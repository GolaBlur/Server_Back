package com.golablur.server.file.overall.restcontroller;

import com.golablur.server.file.loader.controller.LoaderController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/file/load")
@CrossOrigin("*")
public class FileLoaderRestController {

    @Autowired
    LoaderController loaderController;

    

    // 하나의 이미지 업로드
    @RequestMapping(value = "/", method= RequestMethod.GET)
    public Map uploadImage(){

        return null;
    }

    // 하나의 비디오 업로드
    @RequestMapping(value = "/", method= RequestMethod.GET)
    public Map uploadVideo(){

        return null;
    }

    // 여러 장의 이미지 업로드
    @RequestMapping(value = "/", method= RequestMethod.GET)
    public Map uploadImages(){

        return null;
    }

    // 결과물 다운로드
    @RequestMapping(value = "/download/result", method= RequestMethod.GET)
    public Map downloadResult(){

        return null;
    }

    // 결과물 리스트 반환
    @RequestMapping(value = "/get/result/list", method= RequestMethod.GET)
    public Map getResultList(){

        return null;
    }


}
