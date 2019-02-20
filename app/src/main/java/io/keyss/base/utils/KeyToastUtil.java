package io.keyss.base.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.DrawableRes;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import io.keyss.u36.R;

/**
 * @author MrKey
 */
public class KeyToastUtil {

    private static Handler handler = new Handler(Looper.getMainLooper());

    private static Toast toast;

    private static TextView toastText;

    private KeyToastUtil() {
    }

    public static void initToast(Context context) {
        initToast(context, null, null, Gravity.CENTER_HORIZONTAL | Gravity.TOP, 0, 500);
    }

    public static void initToast(Context context, Float alpha, @DrawableRes Integer resId, int gravity, int xOffset, int yOffset) {
        toast = new Toast(context);
        View toastView = View.inflate(context, R.layout.base_toast_normal, null);
        if (null != alpha) {
            toastView.setAlpha(alpha);
        }
        if (null != resId) {
            toastView.setBackgroundResource(resId);
        }
        toastText = toastView.findViewById(R.id.tv_toast);
        toast.setView(toastView);
        toast.setGravity(gravity, xOffset, yOffset);
        toast.setDuration(Toast.LENGTH_SHORT);
    }

    public static void showToast(String showMessage) {
        if (toast == null || TextUtils.isEmpty(showMessage)) {
            return;
        }
        new Thread(() -> handler.post(() -> {
            synchronized (KeyToastUtil.class) {
                toastText.setText(showMessage);
                toast.show();
            }
        })).start();
    }
}
