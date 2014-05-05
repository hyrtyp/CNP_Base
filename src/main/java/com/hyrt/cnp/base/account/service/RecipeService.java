package com.hyrt.cnp.base.account.service;

import com.hyrt.cnp.base.account.CNPClient;
import com.hyrt.cnp.base.account.model.Recipe;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by GYH on 14-1-14.
 */
public class RecipeService {

    private CNPClient cnpClient;

    public RecipeService(CNPClient cnpClient){
        this.cnpClient = cnpClient;
    }

    public Recipe.Model getSchoolRecipeData(RestTemplate restTemplate, int sid){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
        if(sid != -1){
            params.put("sid", sid+"");
        }
        return  restTemplate.getForObject("http://api.chinaxueqian.com/school/recipe/?" +
                "token={token}&uuid={uuid}&sid={sid}",
                Recipe.Model.class, params);
    }
}
