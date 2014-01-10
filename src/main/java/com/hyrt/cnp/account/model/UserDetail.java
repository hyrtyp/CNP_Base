package com.hyrt.cnp.account.model;

import java.io.Serializable;

/** https://github.com/hyrtyp/CNP_Account/wiki/AccountInfo
 * Created by yepeng on 14-1-3.
 */
public class UserDetail implements Serializable{

    private static final long serialVersionUID = -1;

    private String user_id;
    private String renname;
    private String birthday;
    private String sex;
    private String bloodType;
    private String nationality;
    private String ethnic;
    private String nursery_id;
    private String nurseryName;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRenname() {
        return renname;
    }

    public void setRenname(String renname) {
        this.renname = renname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public String getNursery_id() {
        return nursery_id;
    }

    public void setNursery_id(String nursery_id) {
        this.nursery_id = nursery_id;
    }

    public String getNurseryName() {
        return nurseryName;
    }

    public void setNurseryName(String nurseryName) {
        this.nurseryName = nurseryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDetail)) return false;

        UserDetail that = (UserDetail) o;

        if (!birthday.equals(that.birthday)) return false;
        if (!bloodType.equals(that.bloodType)) return false;
        if (!ethnic.equals(that.ethnic)) return false;
        if (!nationality.equals(that.nationality)) return false;
        if (!nurseryName.equals(that.nurseryName)) return false;
        if (!nursery_id.equals(that.nursery_id)) return false;
        if (!renname.equals(that.renname)) return false;
        if (!sex.equals(that.sex)) return false;
        if (!user_id.equals(that.user_id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user_id.hashCode();
        result = 31 * result + renname.hashCode();
        result = 31 * result + birthday.hashCode();
        result = 31 * result + sex.hashCode();
        result = 31 * result + bloodType.hashCode();
        result = 31 * result + nationality.hashCode();
        result = 31 * result + ethnic.hashCode();
        result = 31 * result + nursery_id.hashCode();
        result = 31 * result + nurseryName.hashCode();
        return result;
    }

    public static class UserDetailModel extends Base{

        private static final long serialVersionUID = -1;

        private UserDetail data;

        public UserDetail getData() {
            return data;
        }

        public void setData(UserDetail data) {
            this.data = data;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof UserDetailModel)) return false;

            UserDetailModel that = (UserDetailModel) o;

            if (data != null ? !data.equals(that.data) : that.data != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return data != null ? data.hashCode() : 0;
        }
    }

}
