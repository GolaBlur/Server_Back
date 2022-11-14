package com.golablur.server.file.loader.result.service;

import com.golablur.server.file.overall.mapper.LoaderMapper;
import com.golablur.server.file.overall.domain.FileEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ResultService {

    @Autowired
    LoaderMapper mapper;


    public List getResultList(String user_id) {
        List<FileEntity> resultList;
        resultList = mapper.getFileDataByUser_ID(user_id);

        if(resultList == null) {
            log.error("결과물을 DB 에서 가져오는데 실패했습니다.");
            return null;
        }

        // 원본 파일에 따른 결과물로 리스트를 만들어 리스트에 담는다.
        List classifiedList = classified(resultList, user_id);

        log.info("결과물을 DB 에서 성공적으로 가져왔습니다.");
        return classifiedList;
    }


    //TODO
    // 원본 파일별로 분류하여 리스트에 저장
    public List classified(List<FileEntity> resultList, String user_id){
        List classifiedList = new ArrayList();



        return resultList;
    }

}
