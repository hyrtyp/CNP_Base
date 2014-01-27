package com.hyrt.cnp.account.service;

import com.hyrt.cnp.account.CNPClient;
import com.hyrt.cnp.account.model.Photo;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by GYH on 14-1-14.
 */
public class PhotoService {
    private CNPClient cnpClient;

    public PhotoService(CNPClient cnpClient){
        this.cnpClient = cnpClient;
    }

    /**
     * 获取学校图片
     * */

    //TODO after modify
    public Photo.Model getphotolistData(RestTemplate restTemplate,String pkind){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
        params.put("pkind",pkind);
        return  restTemplate.getForObject("http://api.chinaxueqian.com/school/photo/?" +
                "token={token}&uuid={uuid}&sid={sid}&pkind={pkind}",
                Photo.Model.class, params);
    }

    /**
     * 获取班级相册图片
     * */
    public Photo.Model getClassroomAlbumphotolistData(RestTemplate restTemplate,int paid){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
//        params.put("cid","117");
        params.put("paid",paid+"");
        return  restTemplate.getForObject("http://api.chinaxueqian.com/classroom/photo_album/?" +
                "token={token}&uuid={uuid}&cid={cid}&paid={paid}",
                Photo.Model.class, params);
    }
}
