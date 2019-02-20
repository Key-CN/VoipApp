package io.keyss.base.utils;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Key
 * Time: 2018/8/6 21:38
 * Description: 设置系统类的工具
 */
public class TimeUtil {
    public static String getNetworkTime(String url) {
        String nowStr = "2019-01-30-15-28-32";
        try {
            URLConnection conn = new URL(url).openConnection();
            conn.connect();
            Date now = new Date(conn.getDate());
            nowStr = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.SIMPLIFIED_CHINESE).format(now);
        } catch (IOException e) {
            e.printStackTrace();
        }
        KeyCommonUtil.remoteLogE("当前获取到的时间: " + nowStr);
        return nowStr;
    }

    public static String getNetworkTime() {
        return getNetworkTime("http://aliyun.com");
    }
}
