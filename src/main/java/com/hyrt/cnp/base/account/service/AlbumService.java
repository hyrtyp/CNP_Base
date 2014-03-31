package com.hyrt.cnp.base.account.service;

import com.hyrt.cnp.base.account.CNPClient;
import com.hyrt.cnp.base.account.model.Album;

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
    public Album.Model getAlbumData(RestTemplate restTemplate){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
//        params.put("cid","117");
        return  restTemplate.getForObject("http://api.chinaxueqian.com/classroom/album/?" +
                "token={token}&uuid={uuid}&cid={cid}",
                Album.Model.class, params);
    }

    public Album.Model getBabayAlbumData(RestTemplate restTemplate,String uid){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
        params.put("uid",uid);
//        params.put("uid","222");
        return  restTemplate.getForObject(
                "http://api.chinaxueqian.com/home/album_list?"+
                "token={token}&uuid={uuid}&uid={uid}",Album.Model.class, params);
    }
}
