package io.keyss.base.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Key
 * Time: 2018/6/28 15:47
 * Description:
 */
public class KeySPUtil {

    private static SharedPreferences mSharedPreferences;
    /**
     * 默认文件名
     */
    private final static String DEFAULT_NAME = "SP_Android";
    /**
     * 默认Key的值
     */
    private final static String DEFAULT_KEY_NAME = "SP_KEY_Android";

    /**
     * SP操作对线程有要求，在哪条线程初始化的就要在哪条线程使用
     *
     * @param context 上下文，最好是Application的
     */
    public static void init(Context context) {
        init(context, DEFAULT_NAME);
    }

    public static void init(Context context, String name) {
        if (mSharedPreferences == null) {
            mSharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        }
    }

    public static void save(String value) {
        save(DEFAULT_KEY_NAME, value);
    }

    public static void save(String key, String value) {
        mSharedPreferences.edit().putString(key, value).apply();
    }

    public static String get() {
        return mSharedPreferences.getString(DEFAULT_KEY_NAME, "");
    }

    public static String get(String key) {
        return mSharedPreferences.getString(key, "");
    }
}
