package com.hyrt.cnp.account.service;

import com.hyrt.cnp.account.CNPClient;
import com.hyrt.cnp.account.model.RecipeInfo;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by GYH on 14-1-15.
 */
public class RecipeInfoService {

    private CNPClient cnpClient;

    public RecipeInfoService(CNPClient cnpClient){
        this.cnpClient = cnpClient;
    }

    //TODO after modify
    public RecipeInfo.Model2 getRecipeWeekData(RestTemplate restTemplate){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
        params.put("year","2013");
        params.put("week","25");
        return  restTemplate.getForObject("http://api.chinaxueqian.com/school/week_recipe?"+
                "token={token}&uuid={uuid}&sid={sid}&year={year}&week={week}",
                RecipeInfo.Model2.class, params);
    }

    /**
     * 获取每天食谱信息
     * */
    //TODO after modify
    public RecipeInfo.Model getRecipeDayData(RestTemplate restTemplate,String time){
//        cnpClient.configureRequest();
//        HashMap<String, String> params = cnpClient.getParamsforGet();
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("token", "e1ac72b3cf9902f6db8c88f42728db82");
        params.put("uuid", "104");
        params.put("sid","2");
        params.put("time","2013-11-22");
        return  restTemplate.getForObject("http://api.chinaxueqian.com/school/recipe_info/?" +
                "token={token}&uuid={uuid}&sid={sid}&time={time}",
                RecipeInfo.Model.class, params);
    }
 }
