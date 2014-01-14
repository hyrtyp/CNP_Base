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
        Float part1 = (float)uid/(float)10000;
        Float part2 = (float)uid%(float)10000/(float)1000;
        Float uppart1 = Float.valueOf((part1.intValue() + 1));
        Float uppart2 = Float.valueOf((part1.intValue() + 1));
        return basePath.append("/").append(Float.compare(part1+Float.valueOf(1),uppart1) == 0 ? part1.intValue() : part1.intValue()+1).append("/")
                .append(Float.compare(part2+Float.valueOf(1),uppart2) == 0 ? part2.intValue() : part2.intValue()+1)
                .append("/").append(uid).append("_").append(type).append(".jpg").toString();

    }


}
