package com.hyrt.cnp.base.account.service;

import com.hyrt.cnp.base.account.CNPClient;
import com.hyrt.cnp.base.account.model.Comment;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by GYH on 14-1-22.
 */
public class CommentService {

    private CNPClient cnpClient;

    public CommentService(CNPClient cnpClient){
        this.cnpClient = cnpClient;
    }

    public Comment.Model getCommentlistData(RestTemplate restTemplate,String infoid,String siteid){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
        params.put("infoid",infoid);
        params.put("siteid",siteid);
        return  restTemplate.getForObject("http://api.chinaxueqian.com/classroom/comment?" +
                "token={token}&uuid={uuid}&infoid={infoid}&siteid={siteid}",
                Comment.Model.class,params);
    }

    public Comment.Model getCommenthomeData(RestTemplate restTemplate,String infoid,String siteid){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
        params.put("infoid",infoid);
        params.put("siteid",siteid);
        return  restTemplate.getForObject("http://api.chinaxueqian.com/home/comment?" +
                "token={token}&uuid={uuid}&infoid={infoid}&siteid={siteid}",
                Comment.Model.class,params);
    }

    public Comment.Model3 addCommentData(Comment comment){
        cnpClient.configureRequest();
        MultiValueMap<String,Object> params = cnpClient.getParams();
        params.set("infoid", comment.getInfoID()+"");
        params.set("title", comment.getInfoTitle());
        params.set("userid", comment.get_id() + "");
        params.set("nid", comment.getInfoNurseryId() + "");
        params.set("infocid", comment.getInfoClassroomId() + "");
        params.set("siteid", comment.getSiteid());
        params.set("url", comment.getUrl());
        params.set("lstatus", comment.getLstatus());
        params.set("con", comment.getContent());
        params.set("reply", comment.getReply());
        params.set("rcon", comment.getRecontent());
        params.set("ruid", comment.getReuserId());
        params.set("rename", comment.getReusername());
        params.set("redate", comment.getRedate());
        return  getRestTemplate().postForObject(
                "http://api.chinaxueqian.com/classroom/comment_add", params, Comment.Model3.class);
    }

    public Comment.Model3 adddynamicCommentData(Comment comment){
        cnpClient.configureRequest();
        MultiValueMap<String,Object> params = cnpClient.getParams();
        params.set("infoid", comment.getInfoid2()+"");
        params.set("title", comment.getInfoTitle());
        params.set("userid", comment.get_id() + "");
        params.set("nid", comment.getInfoNurseryId() + "");
        params.set("infocid", comment.getInfoClassroomId() + "");
        params.set("siteid", comment.getSiteid());
        params.set("url", comment.getUrl());
        params.set("lstatus", comment.getLstatus());
        params.set("con", comment.getContent());
        params.set("reply", comment.getReply());
        params.set("rcon", comment.getRecontent());
        params.set("ruid", comment.getReuserId());
        params.set("rename", comment.getReusername());
        params.set("redate", comment.getRedate());
        return  getRestTemplate().postForObject(
                "http://api.chinaxueqian.com/classroom/comment_add", params, Comment.Model3.class);
    }

    protected RestTemplate getRestTemplate() {
        return new RestTemplate(true, new HttpComponentsClientHttpRequestFactory());
    }
}
