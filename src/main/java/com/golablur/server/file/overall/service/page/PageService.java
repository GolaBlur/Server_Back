package com.golablur.server.file.overall.service.page;

import com.golablur.server.file.overall.domain.FileEntity;
import com.golablur.server.file.overall.domain.FileObjectDTO;
import com.golablur.server.file.overall.domain.ObjectEntity;
import com.golablur.server.file.overall.mapper.FileMapper;
import com.golablur.server.file.overall.mapper.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class PageService {

    @Autowired
    FileMapper fileMapper;
    @Autowired
    ObjectMapper objectMapper;


    public List<FileObjectDTO> getNonProcessedImages(String user_id) {
        List<FileObjectDTO> list = new ArrayList<>();

        // 해당하는 File 을 가져와서 FileObjectDTO 생성한 후 리스트에 add
        List<FileEntity> fileList = fileMapper.getNonProcessedImageDataByUser_ID(user_id);
        for(FileEntity file : fileList){
            list.add(FileObjectDTO.builder()
                    .file(file)
                    .objectList(objectMapper.getDetectionObjectListByFile(file))
                    .build());
        }

        return list;
    }

    public List<FileObjectDTO> getNonProcessedVideos(String user_id){
        List<FileObjectDTO> list = new ArrayList<>();

        // 해당하는 File 을 가져와서 FileObjectDTO 생성한 후 리스트에 add
        List<FileEntity> fileList = fileMapper.getNonProcessedVideoByUser_ID(user_id);
        for(FileEntity file : fileList){
            list.add(FileObjectDTO.builder()
                    .file(file)
                    .objectList(objectMapper.getDetectionObjectListByFile(file))
                    .build());
        }

        return list;
    }

    public List<List<FileObjectDTO>> getNonProcessedImageGroups(String user_id) {
        List<List<FileObjectDTO>> list = new ArrayList<>();

        // Group_ID 가 있는 File 들 중에서 Original_File_ID 가 없는 그룹만을 가져옴
        List<String> groupList = fileMapper.getNonProcessedGroupByUser_ID(user_id);
        log.info(groupList.toString());
        for(String group : groupList){
            List<FileObjectDTO> fileObjectDTOList = new ArrayList<>();
            List<FileEntity> fileList = fileMapper.getFileDataByGroup_ID(group);

            for(FileEntity file : fileList){
                fileObjectDTOList.add(
                        FileObjectDTO.builder()
                                .file(file)
                                .objectList(objectMapper.getDetectionObjectListByFile(file))
                                .build()
                );
            }
            list.add(fileObjectDTOList);
        }

        return list;
    }

    public List<List<FileEntity>> getResulFiletList(String user_id) {
        List<List<FileEntity>> list = new ArrayList<>();

        // Original_File_ID 가 있는 Group_ID 를 가져옴 ( Sysdate 로 정렬 됨 )
        List<FileEntity> processedFileList = fileMapper.getProcessedFileDataByUser_ID(user_id);

        // 그룹별로 나누어 저장
        int cnt = 0;
        for(FileEntity file : processedFileList) {
            // 결과물이 아니라 원본이면 continue
            if(file.getFile_ID().equals(file.getOriginal_File_ID())) continue;
            // 처음이 아니고 그룹일 때
            if(list.size() != 0 && list.get(cnt).size() != 0 && file.getGroup_ID() != null){
                // 이전 파일과 같은 그룹일 때 그 리스트에 add 해줌
                if(file.getGroup_ID().equals(list.get(cnt).get(0).getGroup_ID())){
                    List<FileEntity> fileList = list.get(cnt);
                    fileList.add(cnt, file);
                    list.add(cnt, fileList);
                }
            }
            else {
                List<FileEntity> fileList = new ArrayList<>();
                fileList.add(file);
                list.add(cnt, fileList);
                cnt++;
            }
        }

        return list;
    }

}
