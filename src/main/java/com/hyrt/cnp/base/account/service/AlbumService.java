package com.hyrt.cnp.base.account.service;

import com.hyrt.cnp.base.account.CNPClient;
import com.hyrt.cnp.base.account.model.Album;
import com.hyrt.cnp.base.account.model.Comment;

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


    public Album.Model getMyAlbumData(RestTemplate restTemplate){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
        return  restTemplate.getForObject(
                "http://api.chinaxueqian.com/home/album_list?"+
                        "token={token}&uuid={uuid}",Album.Model.class, params);
    }

    public Comment.Model3 AddMyAlbumData(RestTemplate restTemplate,String albumName,String describes){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
        params.put("albumName",albumName);
        params.put("describes",describes);
        return  restTemplate.getForObject(
                "http://api.chinaxueqian.com/home/album_add/?"+
                        "token={token}&uuid={uuid}&albumName={albumName}&describes={describes}",Comment.Model3.class, params);
    }
}
