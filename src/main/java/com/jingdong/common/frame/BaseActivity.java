package com.jingdong.common.frame;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.inject.Key;
import com.hyrt.cnp.R;
import com.jingdong.app.pad.product.ProductListFragment;
import com.jingdong.common.broadcastReceiver.InterfaceBroadcastReceiver;
import com.jingdong.common.broadcastReceiver.InterfaceBroadcastReceiver.Command;
import com.jingdong.common.frame.taskStack.ApplicationManager;
import com.jingdong.common.http.HttpGroup;
import com.jingdong.common.http.HttpGroupSetting;
import com.jingdong.common.utils.DPIUtil;
import com.jingdong.common.utils.Log;
import com.octo.android.robospice.JacksonSpringAndroidSpiceService;
import com.octo.android.robospice.SpiceManager;

import net.oschina.app.AppContext;

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

    protected SpiceManager spiceManager = new SpiceManager(
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
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        if (Log.D) {
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
            final RoboInjector injector = RoboGuice.getInjector(this);
            injector.injectMembersWithoutViews(this);

        }
        actionBar  = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setHomeButtonEnabled(true);
        initTitleview();
        getSupportActionBar().setDisplayShowCustomEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppContext.getInstance().setBaseActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppContext.getInstance().setBaseActivity(null);
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
/*
    * 顶部实现布局
    * */
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
        menu.add("abc")
                .setIcon(R.drawable.actionbar_right)
                .setShowAsAction(
                        MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }
}
