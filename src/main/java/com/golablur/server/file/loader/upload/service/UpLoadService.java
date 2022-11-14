package com.golablur.server.file.loader.upload.service;

import com.golablur.server.file.overall.mapper.LoaderMapper;
import com.golablur.server.file.overall.domain.FileEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UpLoadService {

    @Autowired
    LoaderMapper mapper;


    public String uploadList(List fileList) {
        int cnt = 0;
        for(Object obj : fileList){
            FileEntity file = (FileEntity) obj;
            cnt += mapper.uploadOriginalFile(file);
        }
        if(cnt != fileList.size()) {
            log.error("일부 파일 데이터의 DB 저장을 실패했습니다.");
            return "500";
        }
        log.info("파일 업로드 성공");
        return "200";
    }

    public String uploadOne(FileEntity file) {
        if(mapper.uploadOriginalFile(file) == 0) {
            log.error("파일 데이터의 DB 저장을 실패했습니다.");
            return "500";
        }
        log.info("파일 업로드 성공");
        return "200";
    }

}
