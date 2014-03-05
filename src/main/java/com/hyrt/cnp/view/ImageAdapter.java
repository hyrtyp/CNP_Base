package com.hyrt.cnp.view;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.jingdong.common.frame.BaseActivity;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;

/**
 * 图片滚动加载
 * Created by GYH on 14-1-8.
 */
public class ImageAdapter extends PagerAdapter {

    private ArrayList<PhotoView> imageViews;
    private ArrayList<String> imageurls;
    private BaseActivity baseActivity;
    private boolean isfirst=true;

    public ImageAdapter(ArrayList<PhotoView> imageViews,ArrayList<String> imageurls,BaseActivity baseActivity){
        this.imageViews=imageViews;
        this.imageurls=imageurls;
        this.baseActivity=baseActivity;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);

    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        baseActivity.showDetailImage1(imageurls.get(position), imageViews.get(position), false,isfirst);
        isfirst=false;
        container.addView(imageViews.get(position), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return imageViews.get(position);
    }

    @Override
    public int getCount() {
        return imageViews.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int arg1, Object arg2) {
        container.removeView((View) arg2);
        imageViews.get(arg1).setImageBitmap(null);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {

    }

    @Override
    public Parcelable saveState() {
        return null;
    }

}
