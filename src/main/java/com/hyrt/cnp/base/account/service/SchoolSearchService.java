package com.hyrt.cnp.base.account.service;

import com.hyrt.cnp.base.account.CNPClient;
import com.hyrt.cnp.base.account.model.SchoolSearch;
import com.hyrt.cnp.base.account.utils.Log;

import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by Zoe on 2014-04-01.
 */
public class SchoolSearchService {

    private CNPClient mCnpClient;

    public SchoolSearchService(CNPClient cnpClient){
        this.mCnpClient = cnpClient;
    }

    public SchoolSearch.Model getSchoolSearchData(
            RestTemplate restTemplate, String keytName,
            String keytDistrict, String keytProperty, String keytScale, Double lng, Double lat, String province){
        mCnpClient.configureRequest();
        HashMap<String, String> params = mCnpClient.getParamsforGet();
        params.put("name", keytName);
        StringBuffer path = new StringBuffer("http://api.chinaxueqian.com/school/search/?"
                + "token={token}&uuid={uuid}&name={name}");
        if (keytDistrict.length() > 0) {
            path.append("&city="+keytDistrict);
        }
        if (keytProperty.length() > 0) {
            path.append("&property="+keytProperty);
        }
        if (keytScale.length() > 0) {
           path.append("&scale="+keytScale);
        }
        if(lng != 0 && lat != 0){
            path.append("&jingwei="+lng+","+lat);
        }
        if (province != null && province.length() > 0) {
           path.append("&province="+province);
        }

        return restTemplate.getForObject(path.toString(),
                SchoolSearch.Model.class, params);
    }
}
