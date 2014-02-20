package com.hyrt.cnp.view;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;

/**
 * 图片滚动加载
 * Created by GYH on 14-1-8.
 */
public class ImageAdapter extends PagerAdapter {

    private ArrayList<PhotoView> imageViews;

    public ImageAdapter(ArrayList<PhotoView> imageViews){
        this.imageViews=imageViews;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int arg1) {
        container.addView(imageViews.get(arg1), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return imageViews.get(arg1);
    }

    @Override
    public int getCount() {
        return imageViews.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int arg1, Object arg2) {
        container.removeView((View) arg2);
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
