/*
 * Copyright 2012 GitHub Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hyrt.cnp.account.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.hyrt.cnp.R;
import com.jingdong.app.pad.utils.InflateUtil;
import com.jingdong.common.utils.cache.GlobalImageCache;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.FROYO;
import static android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH;

/**
 * 全局dialog
 * Progress dialog in Holo Light theme
 */
public class LightProgressDialog extends ProgressDialog {

    /**
     * Create progress dialog
     *
     * @param context
     * @param resId
     * @return dialog
     */
    public static AlertDialog create(Context context, int resId) {
        return create(context, context.getResources().getString(resId));
    }

    /**
     * Create progress dialog
     *
     * @param context
     * @param message
     * @return dialog
     */
    public static AlertDialog create(Context context, CharSequence message) {
        if (SDK_INT > FROYO) {
            ProgressDialog dialog;
            if (SDK_INT >= ICE_CREAM_SANDWICH)
                dialog = new LightProgressDialog(context, message);
            else {
                dialog = new ProgressDialog(context);
                dialog.setInverseBackgroundForced(true);
            }
            dialog.setMessage(message);
            dialog.setIndeterminate(true);
            dialog.setProgressStyle(STYLE_SPINNER);
            GlobalImageCache.BitmapDigest localBitmapDigest = new GlobalImageCache.BitmapDigest("spinner");
            localBitmapDigest.setWidth(50);
            localBitmapDigest.setHeight(50);
            Bitmap localBitmap = InflateUtil.loadImageWithCache(localBitmapDigest);
            if(localBitmap == null){
                Drawable drawable = context.getResources().getDrawable(R.drawable.spinner);
                GlobalImageCache.getLruBitmapCache().put(localBitmapDigest,drawableLayerToBitmap(drawable));
                dialog.setIndeterminateDrawable(drawable);
            }else{
                dialog.setIndeterminateDrawable(new BitmapDrawable(localBitmap));
            }
            return dialog;
        } else {
            AlertDialog dialog = LightAlertDialog.create(context);
            dialog.setInverseBackgroundForced(true);
            View view = LayoutInflater.from(context).inflate(
                    R.layout.progress_dialog, null);
            ((TextView) view.findViewById(R.id.tv_loading)).setText(message);
            dialog.setView(view);
            return dialog;
        }
    }

    private LightProgressDialog(Context context, CharSequence message) {
        super(context, THEME_HOLO_LIGHT);
    }

    private static Bitmap drawableLayerToBitmap(Drawable drawable){
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
