package com.hyrt.cnp.account.service;

import com.hyrt.cnp.account.CNPClient;
import com.hyrt.cnp.account.model.Dynamic;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by GYH on 14-1-16.
 */
public class DynamicService {

    private CNPClient cnpClient;

    public DynamicService(CNPClient cnpClient){
        this.cnpClient = cnpClient;
    }

    //TODO MODIFY UID
    public Dynamic.Model getBabayDynamicData(RestTemplate restTemplate,String uid){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
        params.put("uid",uid);
//        params.put("uid","222");
        return  restTemplate.getForObject("http://api.chinaxueqian.com/home/dynamic_user/?" +
                "token={token}&uuid={uuid}&uid={uid}",
                Dynamic.Model.class, params);
    }
    public Dynamic.Model getBabayDynamicMoreData(RestTemplate restTemplate,String uid,String more){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
        params.put("uid",uid);
//        params.put("uid","222");
        params.put("more",more);
        return  restTemplate.getForObject("http://api.chinaxueqian.com/home/dynamic_user/?" +
                "token={token}&uuid={uuid}&uid={uid}&more={more}",
                Dynamic.Model.class, params);
    }

    public Dynamic.Model getBabaywordData(RestTemplate restTemplate,String uid){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
        params.put("uid",uid);
        return  restTemplate.getForObject("http://api.chinaxueqian.com/home/dynamic_my?" +
                "token={token}&uuid={uuid}&uid={uid}",
                Dynamic.Model.class, params);
    }
    public Dynamic.Model getBabaywordDataMore(RestTemplate restTemplate,String uid,String more){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
        params.put("uid",uid);
        params.put("more",more);
        return  restTemplate.getForObject("http://api.chinaxueqian.com/home/dynamic_my?" +
                "token={token}&uuid={uuid}&uid={uid}&more={more}",
                Dynamic.Model.class, params);
    }


    public Dynamic.Model3 adddynamiczfData(Dynamic dynamic){
        cnpClient.configureRequest();
        MultiValueMap<String,Object> params = cnpClient.getParams();
        params.set("did", dynamic.get_id());
        params.set("content", dynamic.getContent());

        Dynamic.Model3 result = getRestTemplate().postForObject(
                "http://api.chinaxueqian.com/home/dynamic_tran/", params, Dynamic.Model3.class);
        return result;
    }

    protected RestTemplate getRestTemplate() {
        return new RestTemplate(true, new HttpComponentsClientHttpRequestFactory());
    }
}
