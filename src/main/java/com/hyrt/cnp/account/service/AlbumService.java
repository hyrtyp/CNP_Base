package com.hyrt.cnp.account.service;

import com.hyrt.cnp.account.CNPClient;
import com.hyrt.cnp.account.model.Album;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by GYH on 14-1-16.
 */
public class AlbumService {

    private CNPClient cnpClient;

    public AlbumService(CNPClient cnpClient){
        this.cnpClient = cnpClient;
    }

    //TODO after modify
    public Album.Model getAlbumData(RestTemplate restTemplate){
//        cnpClient.configureRequest();
//        HashMap<String, String> params = cnpClient.getParamsforGet();
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", "e1ac72b3cf9902f6db8c88f42728db82");
        params.put("uuid", "104");
        params.put("cid","117");
        params.put("pkind","1");
        return  restTemplate.getForObject("http://api.chinaxueqian.com/classroom/album/?" +
                "token={token}&uuid={uuid}&cid={cid}&pkind={pkind}",
                Album.Model.class, params);
    }
}
