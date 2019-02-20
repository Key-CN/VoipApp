package io.keyss.base.utils;

/**
 * @author 钢铁侠
 * Time: 2018/6/21 21:40
 * Description:
 */
public class KeyDoubleClickCheckUtil {
    private static long lastTime;

    public static boolean checkFastDouble(long delayTime) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - lastTime < delayTime) {
            // 不记录，以免无限延迟400毫秒
            return true;
        } else {
            lastTime = currentTimeMillis;
            return false;
        }
    }
}
