package com.hyrt.cnp.account.service;

import com.hyrt.cnp.account.CNPClient;
import com.hyrt.cnp.account.model.Teacher;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by GYH on 14-1-14.
 */
public class TeacherService {

    private CNPClient cnpClient;

    public TeacherService(CNPClient cnpClient){
        this.cnpClient = cnpClient;
    }


    public Teacher.Model getStarteacherData(RestTemplate restTemplate){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
        return  restTemplate.getForObject("http://api.chinaxueqian.com/school/teacher/?" +
                "token={token}&uuid={uuid}&sid={sid}",
                Teacher.Model.class, params);
    }


    public Teacher.Model2 getStarteacherinfoData(RestTemplate restTemplate,int userid){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
        params.put("userid",userid+"");
        return  restTemplate.getForObject("http://api.chinaxueqian.com/school/teacher_info/?" +
                "token={token}&uuid={uuid}&userid={userid}",
                Teacher.Model2.class, params);
    }

}
