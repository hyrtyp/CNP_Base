package com.hyrt.cnp.base.account.service;

import android.util.Log;

import com.hyrt.cnp.base.account.CNPClient;
import com.hyrt.cnp.base.account.model.Dynamic;
import com.hyrt.cnp.base.account.model.DynamicPhoto;
import com.hyrt.cnp.base.account.model.Photo;

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

    public Photo.Model getphotolistData(RestTemplate restTemplate,String pkind, int sid){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
        params.put("pkind",pkind);
        if(sid != -1){
            params.put("sid",sid+"");
        }
        return  restTemplate.getForObject("http://api.chinaxueqian.com/school/photo/?" +
                "token={token}&uuid={uuid}&sid={sid}&pkind={pkind}",
                Photo.Model.class, params);
    }

    /**
     * 获取班级相册图片
     * */
    //TODO MODIFY CID
     public Photo.Model getClassroomAlbumphotolistData(RestTemplate restTemplate,int paid){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
//        params.put("cid","117");
        params.put("paid",paid+"");
         Log.i("tag", "cid:"+params.get("cid")+" paid:"+paid);
        return  restTemplate.getForObject("http://api.chinaxueqian.com/classroom/photo_album/?" +
                "token={token}&uuid={uuid}&cid={cid}&paid={paid}",
                Photo.Model.class, params);
    }

    public DynamicPhoto.Model getDynamicAlbumphotolistData(RestTemplate restTemplate, int paid){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
        params.put("paid",paid+"");
        android.util.Log.i("tag", "paid"+paid);
        return  restTemplate.getForObject("http://api.chinaxueqian.com/home/photo_list/?" +
                        "token={token}&uuid={uuid}&cid={cid}&paid={paid}",
                DynamicPhoto.Model.class, params);
    }
}
