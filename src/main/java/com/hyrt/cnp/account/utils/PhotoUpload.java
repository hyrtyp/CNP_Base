package com.hyrt.cnp.account.utils;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.net.Uri;

import com.hyrt.cnp.R;
import com.hyrt.cnp.account.model.Photo;
import com.hyrt.cnp.account.ui.LightAlertDialog;
import com.jingdong.common.frame.BaseActivity;

import static com.hyrt.cnp.account.ui.LightAlertDialog.Builder;

/**
 * 图片处理
 * Created by yepeng on 14-1-17.
 */
public class PhotoUpload {

    public static int FROM_CAMERA = 2;
    public static int PHOTO_ZOOM = 3;

    private BaseActivity baseActivity;
    private Uri uri;
    //private static PhotoUpload instance;

    /**
     *
     * @param baseActivity 当前活动
     * @param uri 图片保存路径uri
     */
    public PhotoUpload(BaseActivity baseActivity,Uri uri){
        this.baseActivity = baseActivity;
        this.uri = uri;
    }

   /* public static PhotoUpload getInstance(BaseActivity baseActivity){
        if(instance == null){
            instance = new PhotoUpload(baseActivity);
        }
        return instance;
    }*/

    /**
     * 图片剪切功能,需要在活动中监听forresult方法
     * @param paramUri 图片所在资源路径
     */
    public void startPhotoZoom(Uri paramUri){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(paramUri, "image/*");
        intent.putExtra("crop","true");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        baseActivity.startActivityForResult(intent,PHOTO_ZOOM);
    }

    /**
     * 获取照相的图片 需要在活动中监听forresult方法
     * @param uri 照片保存路径
     */
    private void getFromCamera(Uri uri){
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output",uri);
        baseActivity.startActivityForResult(intent, FROM_CAMERA);
    }

    /**
     * 选择两种图片方式，最终需要在活动中监听forresult方法
     */
    public void choiceItem(){
        AlertDialog.Builder builder =  Builder.create(baseActivity);
        builder.setTitle(R.string.picupload_title);
        builder.setItems(R.array.upload_type,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        getFromLocal();
                        break;
                    case 1:
                        getFromCamera(uri);
                        break;
                }
            }
        });
        builder.create();
        builder.show();
    }

    /**
     * 获取本地图片，并剪切
     */
    public void getFromLocal(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        baseActivity.startActivityForResult(intent, PhotoUpload.PHOTO_ZOOM);
    }
}
