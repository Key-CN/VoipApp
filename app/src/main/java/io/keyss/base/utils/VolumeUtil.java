package io.keyss.base.utils;

import android.content.Context;
import android.media.AudioManager;
import android.support.annotation.NonNull;

/**
 * @author Key
 * Time: 2018/9/5 21:56
 * Description: streamMaxVolume: 15
 */
public class VolumeUtil {

    private static AudioManager audioManager;

    public static int getCurrentStreamMusicVolume(@NonNull Context context) {
        if (null == audioManager) {
            audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        }
        int currentVolume = 15;
        try {
            currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentVolume;
    }

    /**
     * @param volume 0-15
     */
    public static void setStreamMusicVolume(@NonNull Context context, int volume) {
        if (null == audioManager) {
            audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        }
        if (null != audioManager) {
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volume < 0 ? 0 : volume > 15 ? 15 : volume, AudioManager.FLAG_PLAY_SOUND);
        }
    }
}
