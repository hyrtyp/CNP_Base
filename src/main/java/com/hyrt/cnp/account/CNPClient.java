package com.hyrt.cnp.account;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.eclipse.egit.github.core.client.IGitHubConstants.AUTH_TOKEN;

/**
 * Created by yepeng on 13-12-11.
 */
public class CNPClient {

    /**
     * send data argments
     */
    private String credentials;

    public String getCredentials() {
        return credentials;
    }

    /**
     * Set credentials
     *
     * @param user
     * @param password
     * @return this client
     */
    public CNPClient setCredentials(final String user, final String password) {
        if (user != null && user.length() > 0 && password != null
                && password.length() > 0)
            credentials = "username="+user
                    +"&password="+ password;
        else
            credentials = null;
        return this;
    }

    /**
     * Set OAuth2 token
     *
     * @param token
     * @return this client
     */
    public CNPClient setOAuth2Token(String token) {
        if (token != null && token.length() > 0)
            credentials = AUTH_TOKEN + '=' + token;
        else
            credentials = null;
        return this;
    }

    /**
     * configure http request
     * @return this client
     */
    public void configureRequest(){
    }

}
