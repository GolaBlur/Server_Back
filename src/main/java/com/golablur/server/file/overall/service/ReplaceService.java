package com.golablur.server.file.overall.service;

import com.golablur.server.file.overall.domain.ReplaceResultDTO;
import com.golablur.server.file.overall.mapper.FileMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReplaceService {

    @Autowired
    private FileMapper mapper;

    public String replaceResult(ReplaceResultDTO replaceResultDTO) {
        // 객체를 삭제한 결과물을 편집까지 완료된 결과물로 대체한다.
        log.info("Replace result");
        log.info(replaceResultDTO.toString());
        if(mapper.replaceFileEntity(replaceResultDTO)==0){
            log.info("ReplaceResult Failed");
            return "500";
        }
        return "200";
    }

}
