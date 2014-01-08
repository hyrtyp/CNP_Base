package com.hyrt.cnp.account.service;

import com.hyrt.cnp.account.CNPClient;
import com.hyrt.cnp.account.model.School;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by GYH on 14-1-3.
 */
public class SchoolListService {
    private CNPClient cnpClient;

    public SchoolListService(CNPClient cnpClient){
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

}
