package io.keyss.u36.app;

import android.app.Application;

import org.pjsip.pjsua2.AccountConfig;

/**
 * @author Key
 * Time: 2019/02/19 16:46
 * Description:
 */
public class App extends Application {

    static {
        System.loadLibrary("pjsua2");
    }

    private static App mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = this;
    }

    public static App getAppContext() {
        return mAppContext;
    }
}
