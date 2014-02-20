package com.hyrt.cnp.view;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

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
    public Object instantiateItem(View arg0, int arg1) {
        ((ViewPager) arg0).addView(imageViews.get(arg1));
        return imageViews.get(arg1);
    }

    @Override
    public int getCount() {
        return imageViews.size();
    }

    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);
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

    @Override
    public void startUpdate(View arg0) {

    }

    @Override
    public void finishUpdate(View arg0) {

    }
}
