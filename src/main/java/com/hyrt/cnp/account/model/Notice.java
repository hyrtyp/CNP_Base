package com.hyrt.cnp.account.model;

import java.util.ArrayList;

/**
 * Created by GYH on 14-1-3.
 */
public class Notice {

    private int annource_id;
    private String title;
    private String contetn;
    private int posttime;
    private String renname;

    public int getAnnource_id() {
        return annource_id;
    }

    public void setAnnource_id(int annource_id) {
        this.annource_id = annource_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContetn() {
        return contetn;
    }

    public void setContetn(String contetn) {
        this.contetn = contetn;
    }

    public int getPosttime() {
        return posttime;
    }

    public void setPosttime(int posttime) {
        this.posttime = posttime;
    }

    public String getRenname() {
        return renname;
    }

    public void setRenname(String renname) {
        this.renname = renname;
    }

    public static class Model extends Base {
        private ArrayList<Notice> data;

        public ArrayList<Notice> getData() {
            return data;
        }

        public void setData(ArrayList<Notice> data) {
            this.data = data;
        }

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
    }
}
