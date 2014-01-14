package com.hyrt.cnp.account.service;

import com.hyrt.cnp.account.CNPClient;
import com.hyrt.cnp.account.model.School;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by GYH on 14-1-3.
 */
public class SchoolService {
    private CNPClient cnpClient;

    public SchoolService(CNPClient cnpClient){
        this.cnpClient = cnpClient;
    }

    //TODO after modify
    public School.Model getSchoolList(RestTemplate restTemplate){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
        params.put("name","");
        params.put("property","");
        params.put("scale","");
        params.put("city","");
        return  restTemplate.getForObject("http://api.chinaxueqian.com/school/search/?" +
                "token={token}&uuid={uuid}&",
                School.Model.class,params);
    }

    //TODO after modify
    public School.Model2 getSchoolinfo(RestTemplate restTemplate){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
        params.put("token", "e1ac72b3cf9902f6db8c88f42728db82");
        params.put("uuid", "104");
        params.put("sid","2");
        return  restTemplate.getForObject("http://api.chinaxueqian.com/school/nursery_info/?" +
                "token={token}&uuid={uuid}&sid={sid}",
                School.Model2.class, params);
    }

}
