package com.hyrt.cnp.account.service;

import com.hyrt.cnp.account.CNPClient;
import com.hyrt.cnp.account.model.BaseTest;
import com.hyrt.cnp.account.model.UserDetail;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.InputStream;

/**
 * Created by yepeng on 13-12-11.
 *
 */
public class UserService{

     private CNPClient cnpClient;

    public UserService(CNPClient cnpClient){
        this.cnpClient = cnpClient;
    }

    public UserDetail.UserDetailModel getUser(){
        cnpClient.configureRequest();
        return  getRestTemplate().getForObject("http://api.chinaxueqian.com/user/info?token={token}&uuid={uuid}",UserDetail.UserDetailModel.class,cnpClient.getParamsforGet());
    }

    public BaseTest modifyUserFace(File faceFile){
        cnpClient.configureRequest();
        MultiValueMap<String, Object> params = cnpClient.getParams();
        Resource face = new FileSystemResource(faceFile);
        params.set("file", face);
        BaseTest result = getRestTemplate().postForObject("http://api.chinaxueqian.com/user/avatar/", cnpClient.getParams(),BaseTest.class);
        return result;
    }



    protected RestTemplate getRestTemplate() {
        return new RestTemplate(true, new HttpComponentsClientHttpRequestFactory());
    }

}
