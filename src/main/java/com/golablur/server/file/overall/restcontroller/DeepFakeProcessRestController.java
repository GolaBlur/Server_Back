package com.golablur.server.file.overall.restcontroller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/file/process/deepFake")
@CrossOrigin("*")
public class DeepFakeProcessRestController {
    //TODO 객체 딥페이크

    // 하나의 이미지
    @RequestMapping(value = "/t", method= RequestMethod.GET)
    public Map deepFakeOneImage(){

        return null;
    }

    // 하나의 비디오
    @RequestMapping(value = "/d", method= RequestMethod.GET)
    public Map deepFakeOneVideo(){

        return null;
    }


    // 여러 장의 이미지
    @RequestMapping(value = "/s", method= RequestMethod.GET)
    public Map deepFakeALotImages(){

        return null;
    }


}
