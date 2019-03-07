package io.keyss.base.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import io.keyss.u36.R;

/**
 * @author Key
 * Time: 2019/03/05 22:52
 * Description:
 */
public class KeySureCancelDialog extends Dialog {

    protected Context mContext;

    protected WindowManager.LayoutParams mLayoutParams;

    public WindowManager.LayoutParams getLayoutParams() {
        return mLayoutParams;
    }

    public KeySureCancelDialog(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public KeySureCancelDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        initView(context);
    }

    protected KeySureCancelDialog(@NonNull Context context, boolean cancelable, @Nullable DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initView(context);
    }

    private void initView(Context context) {
        if (null == getWindow()) {
            Toast.makeText(context, "Dialog创建失败", Toast.LENGTH_SHORT).show();
            dismiss();
            return;
        }
        mContext = context;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 默认有个白色一点点圆角的背景
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        mLayoutParams = getWindow().getAttributes();
        mLayoutParams.alpha = 1f;
        getWindow().setAttributes(mLayoutParams);
        if (mLayoutParams != null) {
            mLayoutParams.height = android.view.ViewGroup.LayoutParams.MATCH_PARENT;
            mLayoutParams.gravity = Gravity.CENTER;
        }

        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_sure_cancel, null);
        setContentView(dialogView);
    }
}
