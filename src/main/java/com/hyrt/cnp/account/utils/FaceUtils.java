package com.hyrt.cnp.account.utils;

/**
 * 计算头像的路径
 * Created by yepeng on 14-1-14.
 */
public class FaceUtils {

    //图片类型
    public static final String FACE_SMALL = "small";
    public static final String FACE_BIG = "big";
    public static final String FACE_MIDDLE = "middle";

    /**
     *
     * @param uid 用户id
     * @param type 图片类型
     * @return
     */
    public static String getAvatar(int uid,String type){
        StringBuilder basePath = new StringBuilder("http://img.chinaxueqian.com/avatartest");
        float part1 = uid/10000;

        float part2 = uid%10000/1000;
        return basePath.append("/").append(part1).append("/").append(part2)
                .append("/").append(uid).append("/").append(type).append(".jpg").toString();

    }


}
