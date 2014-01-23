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

    //TODO after modify  album
    public Album.Model getAlbumData(RestTemplate restTemplate){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
//        HashMap<String, String> params = new HashMap<String, String>();
//        params.put("token", "e1ac72b3cf9902f6db8c88f42728db82");
//        params.put("uuid", "104");
        params.put("cid","117");
        return  restTemplate.getForObject("http://api.chinaxueqian.com/classroom/album/?" +
                "token={token}&uuid={uuid}&cid={cid}",
                Album.Model.class, params);
    }

    //TODO after modify  album
    public Album.Model getBabayAlbumData(RestTemplate restTemplate,String uid){
//        cnpClient.configureRequest();
//        HashMap<String, String> params = cnpClient.getParamsforGet();
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", "e1ac72b3cf9902f6db8c88f42728db82");
        params.put("uuid", "104");
        params.put("uid","222");
        return  restTemplate.getForObject(
                "http://api.chinaxueqian.com/home/album_list?"+
                "token={token}&uuid={uuid}&uid={uid}",Album.Model.class, params);
    }
}
