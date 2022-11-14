package com.golablur.server.file.loader.getFileData.service;

import com.golablur.server.file.overall.mapper.LoaderMapper;
import com.golablur.server.file.overall.domain.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class getFileDataService {

    @Autowired
    LoaderMapper mapper;

    public FileEntity getOneFileData(String file_id) {
        return mapper.getFileDataByFile_ID(file_id);
    }

    public List<FileEntity> getFileListData(String group_id) {
        return mapper.getFileDataByGroup_ID(group_id);
    }

}
