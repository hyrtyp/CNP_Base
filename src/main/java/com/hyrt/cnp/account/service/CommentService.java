package com.hyrt.cnp.account.service;

import com.hyrt.cnp.account.CNPClient;
import com.hyrt.cnp.account.model.Comment;

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

    //TODO after modify  album
    public Comment.Model getCommentlistData(RestTemplate restTemplate,String infoid,String siteid){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
        params.put("infoid",infoid);
        params.put("siteid",siteid);
        return  restTemplate.getForObject("http://api.chinaxueqian.com/classroom/comment?" +
                "token={token}&uuid={uuid}&infoid={infoid}&siteid={siteid}",
                Comment.Model.class,params);
    }

    //TODO after modify  album
    public Comment.Model3 addCommentData(RestTemplate restTemplate,Comment comment){
        cnpClient.configureRequest();
        HashMap<String, String> params = cnpClient.getParamsforGet();
        params.put("infoID", comment.getInfoID()+"");
        params.put("infoTitle", comment.getInfoTitle());
        params.put("infoUserId",comment.getInfoUserId()+"");
        params.put("infoNurseryId",comment.getInfoNurseryId()+"");
        params.put("infoClassroomId",comment.getInfoClassroomId()+"");
        params.put("siteid",comment.getSiteid());
        params.put("url",comment.getUrl());
        params.put("lstatus",comment.getLstatus());
        params.put("content",comment.getContent());
        params.put("reply",comment.getReply());
        params.put("recontent",comment.getRecontent());
        params.put("reuserId",comment.getReuserId());
        params.put("reusername",comment.getReusername());
        params.put("redate",comment.getRedate());
        return  restTemplate.getForObject("http://api.chinaxueqian.com/classroom/comment_add/?" +
                "token={token}&uuid={uuid}&infoID={infoID}&infoTitle={infoTitle}&infoUserId={infoUserId}&infoNurseryId={infoNurseryId}" +
                "&infoClassroomId={infoClassroomId}&siteid={siteid}&url={url}&lstatus={lstatus}" +
                "&content={content}&reply={reply}&recontent={recontent}&reuserId={reuserId}" +
                "&reusername={reusername}&redate={redate}",
                Comment.Model3.class, params);
    }
}
