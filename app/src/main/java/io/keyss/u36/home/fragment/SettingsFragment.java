package io.keyss.u36.home.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import io.keyss.base.view.BaseFragment;
import io.keyss.u36.R;
import io.keyss.u36.databinding.FragmentSettingsBinding;
import io.keyss.u36.home.data.SettingsData;

/**
 * @author Key
 * Time: 2019/02/20 16:06
 * Description:
 */
public class SettingsFragment extends BaseFragment<FragmentSettingsBinding> {

    private SettingsData settings;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void initLayout() {
        settings = new SettingsData();
        binding.setSettings(settings);
        binding.intercept.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEvent.ACTION_DOWN == event.getAction()) {
                    if (settings.isLogin.get()) {
                        new AlertDialog.Builder(mContext)
                                .setTitle("提示")
                                .setMessage("确定要关闭拦截功能吗？\n这会带来风险")
                                .setPositiveButton("确定", (dialog, which) -> {
                                    settings.isLogin.set(false);
                                    Toast.makeText(mContext, "已关闭", Toast.LENGTH_SHORT).show();
                                })
                                .setNegativeButton("取消", null)
                                .show();
                    } else {
                        new AlertDialog.Builder(mContext)
                                .setTitle("提示")
                                .setMessage("使用本功能需要先登陆")
                                .setPositiveButton("登陆", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        settings.isLogin.set(true);
                                        Toast.makeText(mContext, "登陆成功，已打开", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton("取消", null)
                                .show();
                    }
                }
                return true;
            }
        });
    }
}
