package io.keyss.base.view;

import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.keyss.base.utils.KeyActivityUtil;
import io.keyss.base.utils.KeyCommonUtil;

/**
 * @author Key
 * Time: 2018/6/27 11:59
 * Description: Databinding版
 */
public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {

    protected BaseActivity mContext;
    protected boolean isResume;
    protected T binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KeyActivityUtil.addActivity(mContext = this);
        if (0 != getContentViewId()) {
            binding = DataBindingUtil.setContentView(this, getContentViewId());
        }

        // 竖屏显示，不能转动，建议设在第二层
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // 初始化本地数据和布局
        initLayout(savedInstanceState);

        Looper.myQueue().addIdleHandler(() -> {
            KeyCommonUtil.logI("Looper.myQueue().addIdleHandler() -> new MessageQueue.IdleHandler() { 后台空闲线程 }");
            onActivityInitialized();
            //false 表示只监听一次IDLE事件,之后就不会再执行这个函数了. true则会一直执行
            return false;
        });
    }

    /**
     * 布局文件
     *
     * @return Layout的ID
     */
    protected abstract int getContentViewId();

    /**
     * 初始化界面
     *
     * @param savedInstanceState onCreate中传下来的
     */
    protected abstract void initLayout(@Nullable Bundle savedInstanceState);

    /**
     * 界面初始化完成，只执行一次
     * 也可以用onWindowFocusChanged等待渲染完成,比这个方法先运行，但此方法多次执行
     * 按需实现
     * onStart - onResume - onWindowFocusChanged: true - Looper.myQueue().addIdleHandler()
     * - (进入后台)onWindowFocusChanged: false - onPause - onStop
     * 返回时：onStart - onResume - onWindowFocusChanged: true
     */
    protected void onActivityInitialized(){}

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KeyActivityUtil.removeActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isResume = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isResume = false;
    }
}
