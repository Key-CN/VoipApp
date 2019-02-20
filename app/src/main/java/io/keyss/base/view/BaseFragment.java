package io.keyss.base.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Key
 * Time: 2019/02/20 16:09
 * Description: Databinding版
 * 生命周期: setUserVisibleHint() -> onAttach() -> onCreate() -> onCreateView()
 * -> onActivityCreated() -> onStart() -> onResume()
 * 销毁:    onPause() -> onStop() -> onDestroyView() -> onDestroy() -> onDetach()
 */
public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {

    // 安全起见只给子类使用
    protected Context mContext;
    protected T binding;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (0 == getContentViewId()) {
            return null;
        }
        binding = DataBindingUtil.inflate(inflater, getContentViewId(), container, false);
        return binding.getRoot();
    }

    /**
     * 执行该方法时，与Fragment绑定的Activity的onCreate方法已经执行完成并返回，在该方法内可以进行与Activity交互的UI操作
     * 所以在该方法之前Activity的onCreate方法并未执行完成，如果提前进行交互操作，会引发空指针异常。
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initLayout();
    }

    /**
     * 布局文件
     *
     * @return Layout的ID
     */
    protected abstract int getContentViewId();

    /**
     * 初始化数据和视图
     */
    protected abstract void initLayout();
}