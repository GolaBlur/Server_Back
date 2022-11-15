package com.golablur.server.file.overall.service.delete;

import com.golablur.server.file.overall.domain.FileEntity;
import com.golablur.server.file.overall.mapper.FileMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FileDeleteService {

    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private ObjectDeleteService objectDeleteService;

    public int deleteFilesByUserID(String user_id) {
        List fileList = fileMapper.getFileDataByUser_ID(user_id);
        for(Object obj : fileList) {
            FileEntity file = (FileEntity) obj;
            objectDeleteService.deleteObjectsByFileID(file);
        }
        return fileMapper.deleteFile(user_id);
    }

}
