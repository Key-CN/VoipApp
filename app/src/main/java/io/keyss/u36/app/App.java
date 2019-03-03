package io.keyss.u36.app;

import android.app.Application;

/**
 * @author Key
 * Time: 2019/02/19 16:46
 * Description:
 */
public class App extends Application {

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
