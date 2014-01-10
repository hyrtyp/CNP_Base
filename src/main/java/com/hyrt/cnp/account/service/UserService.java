package com.hyrt.cnp.account.service;

import com.hyrt.cnp.account.CNPClient;
import com.hyrt.cnp.account.model.BaseTest;
import com.hyrt.cnp.account.model.UserDetail;

import org.springframework.web.client.RestTemplate;

/**
 * Created by yepeng on 13-12-11.
 *
 */
public class UserService{

     private CNPClient cnpClient;

    public UserService(CNPClient cnpClient){
        this.cnpClient = cnpClient;
    }

    public UserDetail.UserDetailModel getUser(RestTemplate restTemplate){
        cnpClient.configureRequest();
        UserDetail.UserDetailModel result = restTemplate.postForObject("http://api.chinaxueqian.com/user/info",cnpClient.getParams(),UserDetail.UserDetailModel.class);
        System.out.println(result);
        return restTemplate.getForObject("http://api.chinaxueqian.com/user" +  "/info?"+cnpClient.getCredentials(),UserDetail.UserDetailModel.class);
    }

}
