package com.golablur.server.file.overall.restcontroller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/file/process/mosaic")
@CrossOrigin("*")
public class MosaicProcessRestController {
    //TODO 객체 모자이크

    // 하나의 이미지
    @RequestMapping(value = "/t", method= RequestMethod.GET)
    public Map mosaicOneImage(){

        return null;
    }

    // 하나의 비디오
    @RequestMapping(value = "/d", method= RequestMethod.GET)
    public Map mosaicOneVideo(){

        return null;
    }


    // 여러 장의 이미지
    @RequestMapping(value = "/s", method= RequestMethod.GET)
    public Map mosaicALotImages(){

        return null;
    }


}
