package io.keyss.base.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Key
 * Time: 2018/6/28 17:27
 * Description: 控制子线程，new不好管理
 */
public class KeyThreadUtil {

    private static ExecutorService threadPool = Executors.newCachedThreadPool();

    public static void execThread(Runnable runnable) {
        threadPool.execute(runnable);
    }

    public static ExecutorService getThreadPool() {
        return threadPool;
    }
}
