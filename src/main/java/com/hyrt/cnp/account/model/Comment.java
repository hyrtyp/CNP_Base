package com.hyrt.cnp.account.model;


import com.hyrt.cnp.account.utils.FaceUtils;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by GYH on 14-1-7.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {
    private String _id;
    private int infoID;
    private String infoTitle;
    private int infoUserId;
    private int infoNurseryId;
    private int infoClassroomId;
    private String siteid;
    private String url;
    private int userid;
    private String username;
    private int nursery_id;
    private String nursery_name;
    private int userGroup;
    private String creatdate;
    private String ip;
    private String lstatus;
    private String content;
    private String reply;
    private String recontent;
    private String reuserId;
    private String reusername;
    private String redate;
    private String infoid2;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (infoClassroomId != comment.infoClassroomId) return false;
        if (infoID != comment.infoID) return false;
        if (infoNurseryId != comment.infoNurseryId) return false;
        if (infoUserId != comment.infoUserId) return false;
        if (nursery_id != comment.nursery_id) return false;
        if (userGroup != comment.userGroup) return false;
        if (userid != comment.userid) return false;
        if (_id != null ? !_id.equals(comment._id) : comment._id != null) return false;
        if (content != null ? !content.equals(comment.content) : comment.content != null)
            return false;
        if (creatdate != null ? !creatdate.equals(comment.creatdate) : comment.creatdate != null)
            return false;
        if (infoTitle != null ? !infoTitle.equals(comment.infoTitle) : comment.infoTitle != null)
            return false;
        if (infoid2 != null ? !infoid2.equals(comment.infoid2) : comment.infoid2 != null)
            return false;
        if (ip != null ? !ip.equals(comment.ip) : comment.ip != null) return false;
        if (lstatus != null ? !lstatus.equals(comment.lstatus) : comment.lstatus != null)
            return false;
        if (nursery_name != null ? !nursery_name.equals(comment.nursery_name) : comment.nursery_name != null)
            return false;
        if (recontent != null ? !recontent.equals(comment.recontent) : comment.recontent != null)
            return false;
        if (redate != null ? !redate.equals(comment.redate) : comment.redate != null) return false;
        if (reply != null ? !reply.equals(comment.reply) : comment.reply != null) return false;
        if (reuserId != null ? !reuserId.equals(comment.reuserId) : comment.reuserId != null)
            return false;
        if (reusername != null ? !reusername.equals(comment.reusername) : comment.reusername != null)
            return false;
        if (siteid != null ? !siteid.equals(comment.siteid) : comment.siteid != null) return false;
        if (url != null ? !url.equals(comment.url) : comment.url != null) return false;
        if (username != null ? !username.equals(comment.username) : comment.username != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = _id != null ? _id.hashCode() : 0;
        result = 31 * result + infoID;
        result = 31 * result + (infoTitle != null ? infoTitle.hashCode() : 0);
        result = 31 * result + infoUserId;
        result = 31 * result + infoNurseryId;
        result = 31 * result + infoClassroomId;
        result = 31 * result + (siteid != null ? siteid.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + userid;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + nursery_id;
        result = 31 * result + (nursery_name != null ? nursery_name.hashCode() : 0);
        result = 31 * result + userGroup;
        result = 31 * result + (creatdate != null ? creatdate.hashCode() : 0);
        result = 31 * result + (ip != null ? ip.hashCode() : 0);
        result = 31 * result + (lstatus != null ? lstatus.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (reply != null ? reply.hashCode() : 0);
        result = 31 * result + (recontent != null ? recontent.hashCode() : 0);
        result = 31 * result + (reuserId != null ? reuserId.hashCode() : 0);
        result = 31 * result + (reusername != null ? reusername.hashCode() : 0);
        result = 31 * result + (redate != null ? redate.hashCode() : 0);
        result = 31 * result + (infoid2 != null ? infoid2.hashCode() : 0);
        return result;
    }

    public String getInfoid2() {
        return infoid2;
    }

    public void setInfoid2(String infoid2) {
        this.infoid2 = infoid2;
    }

    public String getphotoImage(){
        return  FaceUtils.getAvatar(userid, FaceUtils.FACE_BIG);
    }

    public static class Model extends Base{
        private static final long serialVersionUID = -1;
        private ArrayList<Comment> data;
        private String more;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            Model model = (Model) o;

            if (data != null ? !data.equals(model.data) : model.data != null) return false;
            if (more != null ? !more.equals(model.more) : model.more != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + (data != null ? data.hashCode() : 0);
            result = 31 * result + (more != null ? more.hashCode() : 0);
            return result;
        }

        public String getMore() {

            return more;
        }

        public void setMore(String more) {
            this.more = more;
        }

        public ArrayList<Comment> getData() {
            return data;
        }

        public void setData(ArrayList<Comment> data) {
            this.data = data;
        }
    }

    public static class Model2 extends Base{
        private static final long serialVersionUID = -1;
        private Comment data;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            Model2 model2 = (Model2) o;

            if (data != null ? !data.equals(model2.data) : model2.data != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + (data != null ? data.hashCode() : 0);
            return result;
        }

        public Comment getData() {
            return data;
        }

        public void setData(Comment data) {
            this.data = data;
        }
    }

    public static class Model3 extends Base{
        private static final long serialVersionUID = -1;
        private String data;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            Model3 model3 = (Model3) o;

            if (data != null ? !data.equals(model3.data) : model3.data != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + (data != null ? data.hashCode() : 0);
            return result;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public int getInfoID() {
        return infoID;
    }

    public void setInfoID(int infoID) {
        this.infoID = infoID;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
    }

    public int getInfoUserId() {
        return infoUserId;
    }

    public void setInfoUserId(int infoUserId) {
        this.infoUserId = infoUserId;
    }

    public int getInfoNurseryId() {
        return infoNurseryId;
    }

    public void setInfoNurseryId(int infoNurseryId) {
        this.infoNurseryId = infoNurseryId;
    }

    public int getInfoClassroomId() {
        return infoClassroomId;
    }

    public void setInfoClassroomId(int infoClassroomId) {
        this.infoClassroomId = infoClassroomId;
    }

    public String getSiteid() {
        return siteid;
    }

    public void setSiteid(String siteid) {
        this.siteid = siteid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNursery_id() {
        return nursery_id;
    }

    public void setNursery_id(int nursery_id) {
        this.nursery_id = nursery_id;
    }

    public String getNursery_name() {
        return nursery_name;
    }

    public void setNursery_name(String nursery_name) {
        this.nursery_name = nursery_name;
    }

    public int getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(int userGroup) {
        this.userGroup = userGroup;
    }

    public String getCreatdate() {
        return creatdate;
    }

    public void setCreatdate(String creatdate) {
        this.creatdate = creatdate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLstatus() {
        return lstatus;
    }

    public void setLstatus(String lstatus) {
        this.lstatus = lstatus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getRecontent() {
        return recontent;
    }

    public void setRecontent(String recontent) {
        this.recontent = recontent;
    }

    public String getReuserId() {
        return reuserId;
    }

    public void setReuserId(String reuserId) {
        this.reuserId = reuserId;
    }

    public String getReusername() {
        return reusername;
    }

    public void setReusername(String reusername) {
        this.reusername = reusername;
    }

    public String getRedate() {
        return redate;
    }

    public void setRedate(String redate) {
        this.redate = redate;
    }
}
