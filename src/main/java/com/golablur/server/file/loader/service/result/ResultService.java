package com.golablur.server.file.loader.service.result;

import com.golablur.server.file.overall.domain.ResultFileDTO;
import com.golablur.server.file.overall.mapper.FileMapper;
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
    FileMapper mapper;


    public List<ResultFileDTO> getResultList(String user_id) {
        List<FileEntity> resultList;
        resultList = mapper.getFileDataByUser_ID(user_id);

        if(resultList == null) {
            log.error("결과물을 DB 에서 가져오는데 실패했습니다.");
            return null;
        }

        log.info("결과물을 DB 에서 성공적으로 가져왔습니다.");
        // 원본 파일에 따른 결과물로 리스트를 만들어 리스트에 담는다.
        return classified(resultList);
    }


    // 원본 파일별로 분류하여 리스트에 저장
    public List<ResultFileDTO> classified(List<FileEntity> resultList){
        List<ResultFileDTO> resultFileList = new ArrayList<>();

        for(FileEntity fileEntity : resultList){
            resultFileList.add(
                    ResultFileDTO.builder()
                            .originalFile_ID(fileEntity.getOriginal_File_ID())
                            .processedFile_ID(fileEntity.getFile_ID())
                            .build()
            );
        }
        return resultFileList;
    }

}
