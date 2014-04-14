package com.hyrt.cnp.base.account.service;

import com.hyrt.cnp.base.account.CNPClient;
import com.hyrt.cnp.base.account.model.BaseTest;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;

/**
 * Created by Zoe on 2014-04-11.
 */
public class AddPhotoService {

    private CNPClient cnpClient;

    public AddPhotoService(CNPClient cnpClient) {
        this.cnpClient = cnpClient;
    }

    public BaseTest addPhoto(
            String paid, String photoname,
            String introduce, File photo_add){

        cnpClient.configureRequest();
        MultiValueMap<String, Object> params = cnpClient.getParams();
        params.set("paid", paid);
        params.set("photoname", photoname);
        params.set("introduce", introduce);
        android.util.Log.i("tag", "paid:"+paid+" photoname:"+photoname+" introduce："+introduce);
        Resource face = new FileSystemResource(photo_add);
        params.set("file", face);
        BaseTest result =  getRestTemplate().postForObject(
                "http://api.chinaxueqian.com/home/photo_add/",
                cnpClient.getParams(), BaseTest.class);

        return result;
    }

    protected RestTemplate getRestTemplate() {
        return new RestTemplate(true, new HttpComponentsClientHttpRequestFactory());
    }
}