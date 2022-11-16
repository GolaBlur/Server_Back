package com.golablur.server.file.loader.divider;

import com.golablur.server.file.loader.service.getFileData.getFileDataService;
import com.golablur.server.file.loader.service.result.ResultService;
import com.golablur.server.file.loader.service.storeFileData.StoreFileDataService;
import com.golablur.server.file.overall.domain.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoaderDivider {

    @Autowired
    private StoreFileDataService storeFileDataService;
    @Autowired
    private getFileDataService getFileDataService;
    @Autowired
    private ResultService resultService;


    public String uploadList(List fileList) {
        return storeFileDataService.storeFileList(fileList);
    }

    public String uploadOne(FileEntity file) {
        return storeFileDataService.storeFile(file);
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
