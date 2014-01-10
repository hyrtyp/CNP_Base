package com.hyrt.cnp.account;


import org.springframework.http.HttpEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;

import static org.eclipse.egit.github.core.client.IGitHubConstants.AUTH_TOKEN;

/**
 * Created by yepeng on 13-12-11.
 */
public class CNPClient {

    MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
    HashMap<String, String> paramsforGet = new HashMap<String, String>();

    public HashMap<String, String> getParamsforGet() {
        return paramsforGet;
    }

    public MultiValueMap<String, String> getParams() {
        return params;
    }

    /**
     * Set credentials
     *
     * @param user
     * @param password
     * @return this client
     */
    public CNPClient setCredentials(final String user, final String password) {
        paramsforGet.clear();
        this.params.clear();
        if (user != null && user.length() > 0 && password != null
                && password.length() > 0) {
            params.set("username", user);
            params.set("password", password);
        }
        return this;
    }

    /**
     * Set OAuth2 token
     *
     * @param token
     * @return this client
     */
    public CNPClient setOAuth2Token(String token) {
        if (token != null && token.length() > 0) {
            paramsforGet.clear();
            this.params.clear();
            if (token != null && token.length() > 0) {
                String credentials = AUTH_TOKEN + '=' + token;
                String[] params = credentials.split("&");
                for (int i = 0; i < params.length; i++) {
                    String[] param = params[i].split("=");
                    this.params.set(param[0], param[1]);
                    this.paramsforGet.put(param[0], param[1]);
                }
            }

        }
        return this;
    }

    /**
     * configure http request
     *
     * @return this client
     */
    public void configureRequest() {
    }


}
