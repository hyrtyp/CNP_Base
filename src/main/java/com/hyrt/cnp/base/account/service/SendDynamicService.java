package com.hyrt.cnp.base.account.service;

import com.hyrt.cnp.base.account.CNPClient;
import com.hyrt.cnp.base.account.model.BaseTest;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by HY on 2014-04-10.
 */
public class SendDynamicService{

    private CNPClient cnpClient;

    public SendDynamicService(CNPClient cnpClient) {
        this.cnpClient = cnpClient;
    }

    public BaseTest addDynamic(String content, File picUrl, String toUid, String toName){
        cnpClient.configureRequest();
        MultiValueMap<String, Object> params = cnpClient.getParams();
        params.set("content", content);
        if(picUrl != null){
            Resource face = new FileSystemResource(picUrl);
            params.set("picUrl", face);
        }
        if(toUid != null){
            params.set("toUid", toUid);
        }
        if(toName != null){
            params.set("toName", toName);
        }
        BaseTest result =  getRestTemplate().postForObject(
                "http://api.chinaxueqian.com/home/dynamic_add/",
                cnpClient.getParams(),BaseTest.class);
        return result;
    }

    protected RestTemplate getRestTemplate() {
        return new RestTemplate(true, new HttpComponentsClientHttpRequestFactory());
    }
}
