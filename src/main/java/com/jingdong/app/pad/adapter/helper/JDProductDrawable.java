package com.jingdong.app.pad.adapter.helper;

import net.oschina.app.AppContext;
import android.R.color;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.hyrt.cnp.R;
import com.jingdong.app.pad.adapter.SimpleBeanAdapter;
import com.jingdong.common.utils.DPIUtil;
import com.jingdong.common.utils.Log;
import com.jingdong.common.utils.cache.GlobalImageCache;

/**
 * 产品图片类，当图片为空时通知SimpleImageProcessor获取图片
 * 
 * @author yepeng
 * 
 */
public class JDProductDrawable extends Drawable {
	private static final String TAG = JDProductDrawable.class.getSimpleName();
	private static Bitmap logoBitmap = null;
	private static int logoBitmapHeight;
	private static int logoBitmapWidth;
	private static String text = null;
	private Paint bitmaPaint;
	private Bitmap bitmap;
	private int bitmapState = 3;
	private GlobalImageCache.BitmapDigest digest;
	private boolean isGc;
	private boolean needKeepVisibleBitmap = false;
	private int padding;
	private Paint paint;
	private Resources resources;
	private SimpleBeanAdapter.SubViewHolder subViewHolder;

	public JDProductDrawable(Resources paramResources,
			SimpleBeanAdapter.SubViewHolder paramSubViewHolder,
			GlobalImageCache.BitmapDigest paramBitmapDigest, Bitmap paramBitmap) {
        this.paint = new Paint();
		this.paint.setStyle(Paint.Style.FILL);
		int textSize;
		if (!DPIUtil.isBigScreen())
			textSize = DPIUtil.dip2px(12.0F);
		else
			textSize = 12;
        this.paint.setTextSize(textSize);
		this.paint.setTextAlign(Paint.Align.CENTER);
		this.paint.setAntiAlias(true);
		this.resources = paramResources;
		this.bitmap = paramBitmap;
		this.subViewHolder = paramSubViewHolder;
		this.digest = paramBitmapDigest;
		this.needKeepVisibleBitmap = paramBitmapDigest.isKeepVisibleBitmap();
		if (this.needKeepVisibleBitmap)
			paramBitmapDigest.setInUsing(true);
		this.bitmaPaint = new Paint();
		this.bitmaPaint.setAntiAlias(true);
		this.bitmaPaint.setFilterBitmap(true);
		if (logoBitmap == null) {
			logoBitmap = ((BitmapDrawable) this.resources
					.getDrawable(R.drawable.ic_launcher)).getBitmap();
			logoBitmapWidth = logoBitmap.getWidth();
			logoBitmapHeight = logoBitmap.getHeight();
		}
		if (text == null)
			text = this.resources.getString(R.string.app_name);

	}

	public JDProductDrawable(
			SimpleBeanAdapter.SubViewHolder paramSubViewHolder,
			GlobalImageCache.BitmapDigest paramBitmapDigest) {
		this(AppContext.getInstance().getResources(), paramSubViewHolder,
				paramBitmapDigest, null);
	}

	private void drawException(Canvas paramCanvas, int centerX, int centerY) {
		paramCanvas.drawText(text, centerX, centerY, this.paint);
		if (logoBitmap != null)
			paramCanvas.drawBitmap(logoBitmap, centerX - logoBitmapWidth / 2,
					centerY - logoBitmapHeight / 2 + DPIUtil.dip2px(10.0F),
					this.paint);
	}

	public void draw(Canvas paramCanvas) {
		Rect rect = getBounds();
		int i = rect.centerX();
		int j = rect.centerY();
		if (Log.D) {
			Log.d(TAG, "draw x-->> " + i);
			Log.d(TAG, "draw y-->> " + j);
			Log.d(TAG, "draw bitmapState-->> " + this.bitmapState);
		}
		switch (getBitmapState()) {
		case GlobalImageCache.STATE_NONE:
			drawException(paramCanvas, i, j);
			break;
		case GlobalImageCache.STATE_LOADING:
			drawException(paramCanvas, i, j);
			break;
		case GlobalImageCache.STATE_FAILURE:
			drawException(paramCanvas, i, j);
			break;
		case GlobalImageCache.STATE_SUCCESS:
			if (this.bitmap != null) {
				try {
					Rect disRect = new Rect(0, 0, rect.width(), rect.height());
					paramCanvas.drawBitmap(this.bitmap, null, disRect,
							this.bitmaPaint);
				} catch (Exception exception) {
					// 当加载图片为发生异常时说明图片被回收了。
					exception.printStackTrace();
					if (Log.D)
						Log.d(this.getClass().getSimpleName(),
								" -->>getBitmap().isRecycled()");
					if (!this.isGc) {
						new SimpleImageProcessor().show(this.subViewHolder,
								GlobalImageCache.getImageState(this.digest));
						gc();
					}
				}
			} else {
				// 当图片为空时说明图片被回收了。
				if (Log.D)
					Log.d(this.getClass().getSimpleName(),
							" -->>getBitmap().isRecycled()");
				if (!this.isGc) {
					new SimpleImageProcessor().show(this.subViewHolder,
							GlobalImageCache.getImageState(this.digest));
					gc();
				}
				drawException(paramCanvas, i, j);
			}
		}
	}

	public void gc() {
		if ((this.digest != null) && (this.needKeepVisibleBitmap))
			this.digest.setInUsing(false);
		this.subViewHolder = null;
		this.digest = null;
		this.isGc = true;
	}

	public int getBitmapState() {
		return this.bitmapState;
	}

	public int getOpacity() {
		return 0;
	}

	public int getPadding() {
		return this.padding;
	}

	public void refresh(Bitmap paramBitmap) {
		if (Log.D)
			Log.d(TAG, "refresh bitmap -->> " + paramBitmap);
		if ((paramBitmap != null)
				&& (this.bitmapState != GlobalImageCache.STATE_SUCCESS))
			setBitmapState(GlobalImageCache.STATE_SUCCESS);
		this.bitmap = paramBitmap;
		invalidateSelf();
	}

	public void setAlpha(int paramInt) {
	}

	public void setBitmapState(int bitmapState) {
		if (Log.D)
			Log.d(TAG, "setBitmapState -->> " + bitmapState);
		this.bitmapState = bitmapState;
		if (Log.D)
			Log.d(TAG, "setBitmapState -->> " + this.bitmapState);
	}

	public void setColorFilter(ColorFilter paramColorFilter) {
	}

	public void setPadding(int paramInt) {
		this.padding = paramInt;
	}

	public void update(SimpleBeanAdapter.SubViewHolder paramSubViewHolder,
			GlobalImageCache.BitmapDigest paramBitmapDigest, Bitmap paramBitmap) {
		gc();
		this.bitmap = paramBitmap;
		this.subViewHolder = paramSubViewHolder;
		this.digest = paramBitmapDigest;
		if (paramBitmapDigest != null) {
			this.needKeepVisibleBitmap = paramBitmapDigest
					.isKeepVisibleBitmap();
			if (this.needKeepVisibleBitmap)
				this.digest.setInUsing(true);
		}
		this.isGc = false;
	}
}