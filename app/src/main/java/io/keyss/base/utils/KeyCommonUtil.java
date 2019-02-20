package io.keyss.base.utils;

import android.util.Log;

import java.util.Collection;

import io.keyss.u36.BuildConfig;

/**
 * @author Key
 * Time: 2018/6/27 11:59
 * Description:
 */
public class KeyCommonUtil {

    private static final String mCutLine = "<<<<<--------------------<<<<<-------------------->>>>>-------------------->>>>>";
    private static final String mCutLine2 = "********************";
    private static final String TAG = "KeyCommonUtil";

    /**
     * 判断集合是否为空
     *
     * @param collection 集合
     * @return {@code true}: 空
     */
    public static boolean isCollectionEmpty(Collection collection) {
        return null == collection || collection.isEmpty();
    }

    public static boolean isArrayEmpty(Object[] arrays) {
        return null == arrays || 0 == arrays.length;
    }

    /**
     * 判断字符串是否为空
     *
     * @param str 字符串
     * @return {@code true}: 空
     */
    public static boolean isStringEmpty(String str) {
        return null == str || str.length() == 0 || "null".equals(str);
    }

    public static boolean isStringNotEmpty(String str) {
        return null != str && str.length() != 0 && !"null".equals(str);
    }

    public static String intToIp(int ipInt) {
        StringBuilder sb = new StringBuilder();
        sb.append(ipInt & 0xFF).append(".");
        sb.append((ipInt >> 8) & 0xFF).append(".");
        sb.append((ipInt >> 16) & 0xFF).append(".");
        sb.append((ipInt >> 24) & 0xFF);
        return sb.toString();
    }

    public static void logD(String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, getLogString(msg));
        }
    }

    public static void logI(String msg) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, getLogString(msg));
        }
    }

    public static void logE(String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, getLogString(msg));
        }
    }

    public static void remoteLogI(String msg) {
        Log.i(TAG, getLogString(msg));
        remoteLog("Level: I\t" + msg);
    }

    public static void remoteLogW(String msg) {
        Log.w(TAG, getLogString(msg));
        remoteLog("Level: W\t" + msg);
    }

    public static void remoteLogE(String msg) {
        Log.e(TAG, getLogString(msg));
        remoteLog("Level: E\t" + msg);
    }

    private static void remoteLog(String msg) {
        //Thread thread = Thread.currentThread();
        // 要看在第几层调用
        //StackTraceElement stackTraceElement = thread.getStackTrace()[4];
        // 记录到本地输出到远程服务器
        //ConfigUtil.writerLog("Thread: " + thread.getName() + "\tMethod: " + stackTraceElement.getMethodName() + "(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ")\t" + msg);
    }

    private static String getLogString(String msg) {
        Thread thread = Thread.currentThread();
        // 要看在第几层调用
        StackTraceElement stackTraceElement = thread.getStackTrace()[4];
        return "|----  Thread: " + thread.getName() + "\t\tMethod: " + stackTraceElement.getMethodName() + "(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ")  ----|\n"
                + mCutLine
                + "\n(*for search*)msg: " + msg + "\n"
                + mCutLine;
    }

    public static String join(String[] array, String append) {
        if (isArrayEmpty(array)) {
            return "";
        }
        if (null == append) {
            append = "";
        }

        StringBuilder buf = new StringBuilder();
        int length = array.length;
        for (int i = 0; i < length; i++) {
            buf.append(array[i]);
            if (i < length - 1) {
                buf.append(append);
            }
        }

        return buf.toString();
    }
}
