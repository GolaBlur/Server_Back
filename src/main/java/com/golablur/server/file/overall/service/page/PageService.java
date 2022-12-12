package com.golablur.server.file.overall.service.page;

import com.golablur.server.file.ai.divider.LatentDiffusionDivider;
import com.golablur.server.file.overall.domain.*;
import com.golablur.server.file.overall.mapper.FileMapper;
import com.golablur.server.file.overall.mapper.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class PageService {

    @Autowired
    FileMapper fileMapper;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    LatentDiffusionDivider latentDiffusionDivider;


    public List<FileObjectDTO> getNonProcessedImages(String user_id) {
        List<FileObjectDTO> list = new ArrayList<>();

        // 해당하는 File 을 가져와서 FileObjectDTO 생성한 후 리스트에 add
        List<FileEntity> fileList = fileMapper.getNonProcessedImageDataByUser_ID(user_id);
        log.info("FileList: " + fileList);
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

    public List<GroupFileObjectNameDTO> getNonProcessedImageGroups(String user_id) {
        List<GroupFileObjectNameDTO> list = new ArrayList<>();

        // Group_ID 가 있는 File 들 중에서 Original_File_ID 가 없는 그룹만을 가져옴
        List<String> groupList = fileMapper.getNonProcessedGroupByUser_ID(user_id);
        log.info("getNonProcessedImageGroups "+groupList.toString());
        for(String group : groupList){
            List<FileEntity> fileList = fileMapper.getFileDataByGroup_ID(group);
            list.add(
                    GroupFileObjectNameDTO.builder()
                            .groupFileEntity(fileList)
                            .objectNameList(getObjectNameList(fileList))
                            .build()
            );
        }

        log.info("response :  "+list.toString());

        return list;
    }

    private List<String> getObjectNameList(List<FileEntity> fileList) {
        // fileList 의 object 이름들을 중복을 제거하여 반환
        List<String> list = new ArrayList<String>();
        Set<String> set = new HashSet<>();
        for(FileEntity file : fileList){
            List<String> nameList = objectMapper.getObjectNameByFile(file);
            for(String name : nameList){
                set.add(name);
            }
        }
        // 세트를 리스트로 변환
        for(String name : set){
            list.add(name);
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
            log.info("file: "+file.toString());
            // 결과물이 아니라 원본이면 continue
            if(file.getFile_ID().equals(file.getOriginal_File_ID())) continue;
            // 처음이 아니고 그룹일 때
            if(list.size() != 0 && list.get(cnt-1).size() != 0 && file.getGroup_ID() != null){
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
                cnt = cnt+1;
                log.info("added list is : "+list.toString());
            }
        }

        return list;
    }


    public DeepFakeSourceTargetDTO getDeepFakeFileEntity(String sourceFileId) {
        return DeepFakeSourceTargetDTO.builder()
                .source(fileMapper.getFileDataByFile_ID(sourceFileId))
                .target(objectMapper.getDeepFakeFileBySourceFile_ID(sourceFileId))
                .build();
    }

}
