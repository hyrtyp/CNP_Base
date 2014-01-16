package com.hyrt.cnp.account.service;

import com.hyrt.cnp.account.CNPClient;
import com.hyrt.cnp.account.model.Notice;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by GYH on 14-1-9.
 */
public class SchoolNoticeService {

    private CNPClient cnpClient;

    public SchoolNoticeService(CNPClient cnpClient){
        this.cnpClient = cnpClient;
    }

    public Notice.Model getNoticelistData(RestTemplate restTemplate){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
        return  restTemplate.getForObject("http://api.chinaxueqian.com/school/notice?" +
                "token={token}&uuid={uuid}&sid={sid}",
                Notice.Model.class,params);
    }
}
