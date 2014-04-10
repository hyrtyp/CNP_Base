package com.hyrt.cnp.account.service;

import com.hyrt.cnp.base.account.CNPClient;
import com.hyrt.cnp.base.account.model.ClassRoom;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by GYH on 14-1-14.
 */
public class ClassRoomService {

    private CNPClient cnpClient;

    public ClassRoomService(CNPClient cnpClient){
        this.cnpClient = cnpClient;
    }

    public ClassRoom.Model getClassRoomListData(RestTemplate restTemplate){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
        return  restTemplate.getForObject("http://api.chinaxueqian.com/school/classroom/?" +
                "token={token}&uuid={uuid}&sid={sid}",
                ClassRoom.Model.class, params);
    }

    public ClassRoom.Model2 getClassRoomInfoData1(RestTemplate restTemplate){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
        return  restTemplate.getForObject("http://api.chinaxueqian.com/classroom/classroom/?" +
                "token={token}&uuid={uuid}&cid={cid}",
                ClassRoom.Model2.class, params);
    }
}