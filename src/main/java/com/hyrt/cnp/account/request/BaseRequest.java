package com.hyrt.cnp.account.request;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;

import com.google.inject.Inject;
import com.hyrt.cnp.account.AccountScope;
import com.hyrt.cnp.account.AccountUtils;
import com.hyrt.cnp.account.model.Base;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;
import roboguice.RoboGuice;
import roboguice.inject.ContextScope;

/**
 * Created by yepeng on 14-1-3.
 */
public abstract class BaseRequest extends SpringAndroidSpiceRequest{

    private Context context;

    @Inject
    private AccountScope accountScope;
    @Inject
    private Activity activity;
    @Inject
    private ContextScope contextScope;

    public Context getContext() {
        return context;
    }

    public BaseRequest(Class clazz,Context context) {
        super(clazz);
        this.context = context;
        RoboGuice.getInjector(context).injectMembers(this);
    }

    @Override
    public Base loadDataFromNetwork() throws Exception {
        try{
            final AccountManager manager = AccountManager.get(activity);
            final Account account = AccountUtils.getAccount(manager, activity);
            accountScope.enterWith(account, manager);
            try {
                contextScope.enter(activity);
                try {
                    return run();
                } catch (Exception e) {
                    // Retry task if authentication failure occurs and account is
                    // successfully updated
                    if (AccountUtils.isUnauthorized(e)
                            && AccountUtils.updateAccount(account, activity))
                        return run();
                    else
                        throw e;
                } finally {
                    contextScope.exit(activity);
                }
            } finally {
                accountScope.exit();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public abstract Base run();

}
