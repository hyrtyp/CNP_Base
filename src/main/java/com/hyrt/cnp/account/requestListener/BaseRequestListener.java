package com.hyrt.cnp.account.requestListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.hyrt.cnp.R;
import com.hyrt.cnp.account.ui.LightAlertDialog;
import com.hyrt.cnp.account.ui.LightProgressDialog;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.lang.ref.SoftReference;
import java.text.MessageFormat;

import static android.content.DialogInterface.BUTTON_POSITIVE;

/**
 * Created by yepeng on 14-1-9.
 *
 * 所有请求回调继承此父类，可转圈,具体使用例子可参照
 * https://github.com/hyrtyp/CNP_Account/blob/master/src/main/java/com/hyrt/cnp/account/requestListener/LoginRequestListener.java
 */
public abstract class BaseRequestListener implements RequestListener {

    protected SoftReference<Activity> context;

    /**
     * Progress dialog last displayed
     */
    protected AlertDialog progress;

    /**
     * @param context
     */
    protected BaseRequestListener(Activity context) {
        this.context = new SoftReference<Activity>(context);
    }

    /**
     * Dismiss and clear progress dialog field
     */
    protected void dismissProgress() {
        if (progress != null) {
            progress.dismiss();
            progress = null;
        }
    }

    /**
     * Show indeterminate progress dialog with given message
     *
     * @param message
     */
    protected void showIndeterminate(final CharSequence message) {
        if(context != null && context.get() != null){
            dismissProgress();
            progress = LightProgressDialog.create(context.get(), message);
            progress.show();
        }
    }

    /**
     * Show indeterminate progress dialog with given message
     *
     * @param resId
     */
    protected void showIndeterminate(final int resId) {
        if(context != null && context.get() != null){
            dismissProgress();
            progress = LightProgressDialog.create(context.get(), resId);
            progress.show();
        }
    }

    /**
     * Get string from context resources
     *
     * @param resId
     * @return string
     */
    protected String getString(int resId) {
        return context.get().getString(resId);
    }


    @Override
    public void onRequestFailure(SpiceException e) {
        dismissProgress();
    }

    @Override
    public void onRequestSuccess(Object o) {
        dismissProgress();
    }

    public abstract BaseRequestListener start();

}
