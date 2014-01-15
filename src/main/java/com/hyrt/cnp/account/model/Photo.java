package com.hyrt.cnp.account.model;

import java.util.ArrayList;

/**
 * Created by GYH on 14-1-3.
 */
public class Photo {

    private int photoID;
    private int paId;
    private String pics;
    private String thpath;
    private String title;
    private String introduce;
    private int isFocus;
    private int pkind;

    public String getImagethpath(){
        String str= "http://img.chinaxueqian.com/avatartest/"+thpath;
        return str;
    }
    public String getImagepics(){
        String str= "http://img.chinaxueqian.com/avatartest/"+pics;
        return str;
    }

    public int getPhotoID() {
        return photoID;
    }

    public void setPhotoID(int photoID) {
        this.photoID = photoID;
    }

    public int getPaId() {
        return paId;
    }

    public void setPaId(int paId) {
        this.paId = paId;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public String getThpath() {
        return thpath;
    }

    public void setThpath(String thpath) {
        this.thpath = thpath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getIsFocus() {
        return isFocus;
    }

    public void setIsFocus(int isFocus) {
        this.isFocus = isFocus;
    }

    public int getPkind() {
        return pkind;
    }

    public void setPkind(int pkind) {
        this.pkind = pkind;
    }

    public static class Model extends Base {
        private ArrayList<Photo> data;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            Model model = (Model) o;

            if (data != null ? !data.equals(model.data) : model.data != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + (data != null ? data.hashCode() : 0);
            return result;
        }

        public ArrayList<Photo> getData() {
            return data;
        }

        public void setData(ArrayList<Photo> data) {
            this.data = data;
        }
    }
}
