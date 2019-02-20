package io.keyss.base.utils;

import android.os.SystemClock;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;

/**
 * @author Key
 * Time: 2019/1/22 14:49
 * Description:
 */
public class PingTestUtil {
    private static double sDelay = 9999;
    private static final int TIMEOUT_TIME = 600;
    private static boolean isStart;

    public static boolean isPingStart() {
        return isStart;
    }

    public static double getDelay() {
        return sDelay;
    }

    public static String getDelayStr() {
        return isTimeout() ? "超时" : sDelay + "ms";
    }

    public static boolean isTimeout() {
        return sDelay > TIMEOUT_TIME;
    }

    public static void pingTest(final ExecutorService pool) {
        pool.execute(() -> {
            try {
                isStart = true;
                ping(pool);
            } catch (Exception e) {
                isStart = false;
                KeyCommonUtil.remoteLogE("ping检测已结束, Exception: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }

    private static void ping(ExecutorService pool) throws Exception {
        while (true) {
            try {
                Process exec = Runtime.getRuntime().exec("ping aliyun.com");

                printError(pool, exec.getErrorStream());

                printSuccess(pool, exec.getInputStream());

                int waitFor = exec.waitFor();
                KeyCommonUtil.remoteLogE("网络ping检测 waitFor: " + waitFor);
                exec.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
            SystemClock.sleep(2_000);
        }
    }

    private static void printSuccess(ExecutorService pool, final InputStream inputStream) {
        pool.execute(() -> {
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            try {
                while (null != (line = bf.readLine())) {
                    // 64 bytes from aliyun.com (140.205.32.13): icmp_seq=1 ttl=37 time=57.5 ms
                    if (line.contains("time=") && line.contains(" ms")) {
                        String time = line.substring(line.indexOf("time=") + 5, line.indexOf(" ms")).trim();
                        try {
                            sDelay = Double.valueOf(time);
                            if (sDelay > TIMEOUT_TIME) {
                                Log.d("ping", "延迟: " + time);
                            }
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private static void printError(ExecutorService pool, final InputStream inputStream) {
        pool.execute(() -> {
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            try {
                while ((line = bf.readLine()) != null) {
                    sDelay = 9999;
                    KeyCommonUtil.remoteLogE(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
