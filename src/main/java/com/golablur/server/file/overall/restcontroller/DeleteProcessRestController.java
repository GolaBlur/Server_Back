package com.golablur.server.file.overall.restcontroller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/file/process/delete")
@CrossOrigin("*")
public class DeleteProcessRestController {
    //TODO 객체 삭제

    // 하나의 이미지
    @RequestMapping(value = "/", method= RequestMethod.GET)
    public Map deleteOneImage(){

        return null;
    }

    // 여러 장의 이미지
    @RequestMapping(value = "/", method= RequestMethod.GET)
    public Map deleteALotImages(){

        return null;
    }


}
