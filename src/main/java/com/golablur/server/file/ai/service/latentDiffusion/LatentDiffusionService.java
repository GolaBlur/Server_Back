package com.golablur.server.file.ai.service.latentDiffusion;

import com.golablur.server.file.overall.domain.AIFunctionDTO;
import com.golablur.server.file.overall.domain.FileObjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LatentDiffusionService {
    // restTemplate
    @Autowired
    private RestTemplate restTemplate;


    public FileObjectDTO processDeleteOneImage(AIFunctionDTO aiFunctionDTO) {
        return null;
    }

}
