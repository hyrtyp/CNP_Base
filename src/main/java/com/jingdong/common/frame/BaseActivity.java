package com.jingdong.common.frame;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.inject.Key;
import com.hyrt.cnp.R;
import com.jingdong.app.pad.product.ProductListFragment;
import com.jingdong.app.pad.product.drawable.HandlerRecycleBitmapDrawable;
import com.jingdong.app.pad.utils.InflateUtil;
import com.jingdong.common.broadcastReceiver.InterfaceBroadcastReceiver;
import com.jingdong.common.broadcastReceiver.InterfaceBroadcastReceiver.Command;
import com.jingdong.common.frame.taskStack.ApplicationManager;
import com.jingdong.common.http.HttpGroup;
import com.jingdong.common.http.HttpGroupSetting;
import com.jingdong.common.http.HttpSetting;
import com.jingdong.common.utils.DPIUtil;
import com.jingdong.common.utils.Log;
import com.jingdong.common.utils.cache.GlobalImageCache;
import com.octo.android.robospice.JacksonSpringAndroidSpiceService;
import com.octo.android.robospice.SpiceManager;

import net.oschina.app.AppContext;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import roboguice.RoboGuice;
import roboguice.inject.RoboInjector;
import roboguice.util.RoboContext;

/**
 * 应用程序Activity的基类
 */
public class BaseActivity extends ActionBarActivity implements RoboContext {

    private static final String TAG = "BaseActivity";
    private MyActivity currentMyActivity;

    protected HashMap<Key<?>,Object> scopedObjects = new HashMap<Key<?>, Object>();

    protected View viewTitleBar;
    protected ActionBar actionBar;
    protected ImageView backimage;
    protected TextView titletext;

    @Override
    public Map<Key<?>, Object> getScopedObjectMap() {
        return scopedObjects;
    }

    public SpiceManager spiceManager = new SpiceManager(
            JacksonSpringAndroidSpiceService.class);

    public HttpGroup getHttpGroupaAsynPool() {
        return getHttpGroupaAsynPool(HttpGroupSetting.TYPE_JSON);
    }

    public HttpGroup getHttpGroupaAsynPool(int type) {
        HttpGroupSetting localHttpGroupSetting = new HttpGroupSetting();
        localHttpGroupSetting.setType(type);
        return getHttpGroupaAsynPool(localHttpGroupSetting);
    }

    public HttpGroup getHttpGroupaAsynPool(
            final HttpGroupSetting paramHttpGroupSetting) {
        HttpGroup.HttpGroupaAsynPool localHttpGroupaAsynPool = new HttpGroup.HttpGroupaAsynPool(
                paramHttpGroupSetting);
        return localHttpGroupaAsynPool;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                //ApplicationManager.back();
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final RoboInjector injector = RoboGuice.getInjector(this.getApplicationContext());
        injector.injectMembers(this);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        if (true) {
            Log.d("MainActivity",
                    " -->> this.getResources().getDisplayMetrics().density:"
                            + getResources().getDisplayMetrics().density);
            Log.d("MainActivity",
                    " -->> this.getResources().getDisplayMetrics().heightPixels:"
                            + getResources().getDisplayMetrics().heightPixels);
            Log.d("MainActivity",
                    " -->> this.getResources().getDisplayMetrics().widthPixels:"
                            + getResources().getDisplayMetrics().widthPixels);
            Log.d("MainActivity",
                    " -->> this.getResources().getDisplayMetrics().xdpi:"
                            + getResources().getDisplayMetrics().xdpi);
            Log.d("MainActivity",
                    " -->> this.getResources().getDisplayMetrics().ydpi:"
                            + getResources().getDisplayMetrics().ydpi);
            Log.d("MainActivity",
                    " -->> this.getResources().getDisplayMetrics().densityDpi:"
                            + getResources().getDisplayMetrics().densityDpi);
            try {
                Log.d("MainActivity",
                        "getSizeOfScreen() -->> " + DPIUtil.getSizeOfScreen());
            } catch (Exception e) {
                e.printStackTrace();
            }
            //效率观察
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads().detectDiskWrites().detectNetwork()
                    .penaltyLog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
                    .penaltyLog().penaltyDeath().build());

        }
        if(!(this.getLocalClassName().contains("FullscreenActivity"))){
            actionBar  = getSupportActionBar();
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setHomeButtonEnabled(true);
            initTitleview();
            getSupportActionBar().setDisplayShowCustomEnabled(true);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        AppContext.getInstance().setBaseActivity(this);
    }

    @Override
    protected void onDestroy() {
        try {
            RoboGuice.destroyInjector(this);
        }finally {
            super.onDestroy();
            AppContext.getInstance().setBaseActivity(null);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        contentViewChanged();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        contentViewChanged();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        contentViewChanged();
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params) {
        super.addContentView(view, params);
        contentViewChanged();
    }

    private void contentViewChanged() {
        RoboGuice.getInjector(this).injectViewMembers(this);
    }

    public MyActivity getCurrentMyActivity() {
        return currentMyActivity;
    }

    public void setCurrentMyActivity(MyActivity paramMyActivity) {
        currentMyActivity = paramMyActivity;
    }

    public int getCurrentFragmentViewId() {
        if (getCurrentMyActivity() != null)
            Log.d("MainActivity", "getview id-->> "
                    + getCurrentMyActivity().getViewId());
        else if (Log.D)
            Log.d("MainActivity", "getCurrentMyActivity() == null -->> ");
        return R.id.container;
    }

    public void toTargetActivity(Command localCommand) {
        if (Log.D)
            Log.d(TAG, "MainActivity toTargetActivity() -->> ");
        Bundle localBundle = localCommand.getBundle();
        switch (localBundle.getInt("moduleId")) {
            case InterfaceBroadcastReceiver.MODULE_ID_HOME:
                ProductListFragment.ProductListTM productListTM = new ProductListFragment.ProductListTM();
                productListTM.setBundle(localBundle);
                ApplicationManager.go(productListTM);
                break;
        }
    }

    @Override
    protected void onStart() {
        spiceManager.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }

   /* @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_UP){
                ApplicationManager.back();
                return true;
            }

        }
        return super.dispatchKeyEvent(event);
    }*/


    /**
     * 初始化顶部标题栏
      */
   protected void initTitleview(){
       ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
               ActionBar.LayoutParams.MATCH_PARENT,
               ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
       viewTitleBar = getLayoutInflater().inflate(R.layout.layout_actionbar_title, null);
       backimage=(ImageView)viewTitleBar.findViewById(R.id.action_bar_title_back);
       titletext=(TextView)viewTitleBar.findViewById(R.id.action_bar_title_text);
       titletext.setText(this.getTitle());
       actionBar.setCustomView(viewTitleBar, lp);
       backimage.setVisibility(View.GONE);
       actionBar.setIcon(R.drawable.actionbar_title_back);
   }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(!this.getTitle().equals("转发动态")){
            menu.add("abc")
                    .setIcon(R.drawable.actionbar_right)
                    .setShowAsAction(
                            MenuItem.SHOW_AS_ACTION_ALWAYS);
        }

        return true;
    }

    /**
     * 获取图片,并附加到视图上
     * @param imageUrl
     * @param onEndListener
     */
    public void executeImage(String imageUrl, HttpGroup.OnEndListener onEndListener) {
        HttpGroup httpGroup = getHttpGroupaAsynPool();
        HttpSetting httpSetting = new HttpSetting();
        httpSetting.setFinalUrl(imageUrl);
        httpSetting.setType(HttpGroupSetting.TYPE_IMAGE);
        httpSetting.setPriority(HttpGroupSetting.PRIORITY_IMAGE);
        httpSetting.setCacheMode(HttpSetting.CACHE_MODE_ONLY_CACHE);
        httpSetting.setListener(onEndListener);
        httpGroup.add(httpSetting);
    }

    /**
     * 显示弹出大图
     * @param view 窗口view
     * @param bigImgPath 大图路径
     */
    public  void ShowPop(View view,String bigImgPath) {
        View popView = this.getLayoutInflater().inflate(
                R.layout.layout_popwindwos, null);
        final PopupWindow popWin = new PopupWindow(popView, ViewPager.LayoutParams.MATCH_PARENT,
                ViewPager.LayoutParams.MATCH_PARENT);

        popView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popWin.dismiss();
            }
        });
        // 需要设置一下此参数，点击外边可消失
        popWin.setBackgroundDrawable(new BitmapDrawable());
        //设置点击窗口外边窗口消失
        popWin.setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击
        popWin.setFocusable(true);
        popWin.showAtLocation(view,
                Gravity.CENTER, 0, 0);
        ImageView imageview = (ImageView)popView.findViewById(R.id.pop_img);
        showDetailImage(bigImgPath,imageview,false);
    }

    /**
     * 显示详细页面人员头像
     * facePath 头像图片地址
     * targetView 图片显示元素
     */
    protected GlobalImageCache.BitmapDigest showDetailImage(String facePath,final ImageView targetView,boolean isrefresh){
        final WeakReference<ImageView> weakImageView = new WeakReference<ImageView>(targetView);
        HandlerRecycleBitmapDrawable localHandlerRecycleBitmapDrawable = new HandlerRecycleBitmapDrawable(null, this);
        targetView.setImageDrawable(localHandlerRecycleBitmapDrawable);
        GlobalImageCache.BitmapDigest localBitmapDigest = new GlobalImageCache.BitmapDigest(facePath);
        localBitmapDigest.setWidth(targetView.getWidth());
        localBitmapDigest.setHeight(targetView.getHeight());
        Bitmap localBitmap = InflateUtil.loadImageWithCache(localBitmapDigest);
        if (localBitmap == null) {
            HandlerRecycleBitmapDrawable localHandlerRecycleBitmapDrawable2 = (HandlerRecycleBitmapDrawable) targetView.getDrawable();
            localHandlerRecycleBitmapDrawable2.setBitmap(null);
            localHandlerRecycleBitmapDrawable.invalidateSelf();
            InflateUtil.loadImageWithUrl(getHttpGroupaAsynPool(), localBitmapDigest,isrefresh, new InflateUtil.ImageLoadListener() {
                public void onError(GlobalImageCache.BitmapDigest paramAnonymousBitmapDigest) {
                }

                public void onProgress(GlobalImageCache.BitmapDigest paramAnonymousBitmapDigest, int paramAnonymousInt1, int paramAnonymousInt2) {
                }

                public void onStart(GlobalImageCache.BitmapDigest paramAnonymousBitmapDigest) {
                }

                public void onSuccess(GlobalImageCache.BitmapDigest paramAnonymousBitmapDigest, Bitmap paramAnonymousBitmap) {
                    if (weakImageView != null) {
                        ImageView targetIv = weakImageView.get();
                        if (targetIv != null) {
                            HandlerRecycleBitmapDrawable localHandlerRecycleBitmapDrawable = (HandlerRecycleBitmapDrawable) targetIv.getDrawable();
                            localHandlerRecycleBitmapDrawable.setBitmap(paramAnonymousBitmap);
                            localHandlerRecycleBitmapDrawable.invalidateSelf();
                        }
                    }
                }
            });
        } else {
            localHandlerRecycleBitmapDrawable.setBitmap(localBitmap);
            localHandlerRecycleBitmapDrawable.invalidateSelf();
        }
        return localBitmapDigest;
    }
}
