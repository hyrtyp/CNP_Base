package com.hyrt.cnp.account.model;

import java.io.Serializable;

/** https://github.com/hyrtyp/CNP_Account/wiki/AccountInfo
 * Created by yepeng on 14-1-3.
 */
public class UserDetail implements Serializable{

    private static final long serialVersionUID = -1;

    private int user_id;
    private String renname;
    private String birthday;
    private String sex;
    private String bloodType;
    private String nationality;
    private String ethnic;
    private String nursery_id;
    private String nurseryName;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
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

        if (user_id != that.user_id) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null)
            return false;
        if (bloodType != null ? !bloodType.equals(that.bloodType) : that.bloodType != null)
            return false;
        if (ethnic != null ? !ethnic.equals(that.ethnic) : that.ethnic != null) return false;
        if (nationality != null ? !nationality.equals(that.nationality) : that.nationality != null)
            return false;
        if (nurseryName != null ? !nurseryName.equals(that.nurseryName) : that.nurseryName != null)
            return false;
        if (nursery_id != null ? !nursery_id.equals(that.nursery_id) : that.nursery_id != null)
            return false;
        if (renname != null ? !renname.equals(that.renname) : that.renname != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user_id;
        result = 31 * result + (renname != null ? renname.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (bloodType != null ? bloodType.hashCode() : 0);
        result = 31 * result + (nationality != null ? nationality.hashCode() : 0);
        result = 31 * result + (ethnic != null ? ethnic.hashCode() : 0);
        result = 31 * result + (nursery_id != null ? nursery_id.hashCode() : 0);
        result = 31 * result + (nurseryName != null ? nurseryName.hashCode() : 0);
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
