package com.golablur.server.file.loader.divider;

import com.golablur.server.file.loader.getFileData.service.getFileDataService;
import com.golablur.server.file.loader.result.service.ResultService;
import com.golablur.server.file.loader.upload.service.UpLoadService;
import com.golablur.server.file.overall.domain.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoaderDivider {

    @Autowired
    UpLoadService upLoadService;
    @Autowired
    getFileDataService getFileDataService;
    @Autowired
    ResultService resultService;


    public String uploadList(List fileList) {
        return upLoadService.uploadList(fileList);
    }

    public String uploadOne(FileEntity file) {
        return upLoadService.uploadOne(file);
    }

    public FileEntity getOneFileData(String file_id) {
        return getFileDataService.getOneFileData(file_id);
    }

    public List getFileListData(String group_id) {
        return getFileDataService.getFileListData(group_id);
    }

    public List getResultList(String user_id) {
        return resultService.getResultList(user_id);
    }



}
