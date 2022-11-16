package com.golablur.server.file.ai.service.deepFake;

import com.golablur.server.file.overall.domain.AIFunctionDTO;
import com.golablur.server.file.overall.domain.FileObjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DeepFakeService {
    // restTemplate
    @Autowired
    private RestTemplate restTemplate;


    public static FileObjectDTO processFakeOneImage(AIFunctionDTO aiFunctionDTO) {
        return null;
    }

    public static FileObjectDTO processFakeOneVideo(AIFunctionDTO aiFunctionDTO) {
        return null;
    }

}
