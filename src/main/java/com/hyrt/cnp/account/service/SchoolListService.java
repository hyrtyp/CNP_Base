package com.hyrt.cnp.account.service;

import com.hyrt.cnp.account.CNPClient;
import com.hyrt.cnp.account.model.School;

import org.springframework.web.client.RestTemplate;

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
     return restTemplate.getForObject("http://api.chinaxueqian.com/school/search/?"+cnpClient.getCredentials()+"",School.Model.class);
//        return restTemplate.getForObject("http://api.chinaxueqian.com/school/search/?token=f89412c39110c73ac1ee39fcf07616bd&uuid=237",School.Model.class);
    }

}
