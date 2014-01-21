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

    /*
    * 获取班级公告列表
    * */
    public Notice.Model getClassroomNoticelistData(RestTemplate restTemplate){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
//        HashMap<String, String> params = new HashMap<String, String>();
//        params.put("token", "e1ac72b3cf9902f6db8c88f42728db82");
//        params.put("uuid", "104");
//        params.put("cid","117");
        return  restTemplate.getForObject("http://api.chinaxueqian.com/classroom/notice/?" +
                "token={token}&uuid={uuid}&cid={cid}",
                Notice.Model.class,params);
    }
 }
