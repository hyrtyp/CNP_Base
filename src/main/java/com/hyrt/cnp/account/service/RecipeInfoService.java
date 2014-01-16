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
}
