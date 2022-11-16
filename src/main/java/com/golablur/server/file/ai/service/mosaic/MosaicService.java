package com.golablur.server.file.ai.service.mosaic;

import com.golablur.server.file.overall.domain.AIFunctionDTO;
import com.golablur.server.file.overall.domain.FileObjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MosaicService {
    // restTemplate
    @Autowired
    private RestTemplate restTemplate;

    public FileObjectDTO processMosaicOneImage(AIFunctionDTO aiFunctionDTO) {
        return null;
    }

    public FileObjectDTO processMosaicOneVideo(AIFunctionDTO aiFunctionDTO) {
        return null;
    }

}
