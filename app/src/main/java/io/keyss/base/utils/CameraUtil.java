package io.keyss.base.utils;

import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.support.annotation.NonNull;
import android.view.SurfaceHolder;

/**
 * @author Key
 * Time: 2018/8/17 11:38
 * Description:
 */
public class CameraUtil {
    private static Camera mCamera;
    private static byte[] mCurrentPreviewData;

    /**
     * 预览摄像头需要在界面初始化完成前操作，否则SurfaceView完成不了渲染
     *
     * @param surfaceHolder
     * @param width                 预览的宽
     * @param height                预览的高
     * @param onCameraStartListener 操作用回调
     */
    public static void openCamera(SurfaceHolder surfaceHolder, int width, int height, OnCameraStartListener onCameraStartListener) {
        init(surfaceHolder, width, height, onCameraStartListener);
    }

    private static void init(SurfaceHolder surfaceHolder, int width, int height, OnCameraStartListener onCameraStartListener) {
        closeCamera();
        try {
            mCamera = Camera.open();
        } catch (Exception e) {
            e.printStackTrace();
            closeCamera();
            onCameraStartListener.onOpenCameraFailed(e);
            return;
        }
        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setPreviewFormat(ImageFormat.NV21);
        parameters.setPreviewSize(width, height);
        parameters.setRecordingHint(true);//去掉这句，12fps，预览不卡
        parameters.setAutoExposureLock(true);//去掉这句，30fps
        parameters.setAutoWhiteBalanceLock(true);//去掉这句，30fps
        parameters.setWhiteBalance(Camera.Parameters.WHITE_BALANCE_AUTO);
        parameters.setSceneMode(Camera.Parameters.SCENE_MODE_AUTO);
//        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        mCamera.setParameters(parameters);

        int size = ((width * height) * ImageFormat.getBitsPerPixel(ImageFormat.NV21)) / 8;
        KeyCommonUtil.logI("data-size: " + size);
        onCameraStartListener.onDataCreated(size);
        mCurrentPreviewData = new byte[size];
        mCamera.addCallbackBuffer(mCurrentPreviewData);
        mCamera.setPreviewCallbackWithBuffer((data, camera) -> {
            // data 和 mCurrentPreviewData 是同一个对象
            camera.addCallbackBuffer(data);
            onCameraStartListener.onPreviewFrame(data);
        });


        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    mCamera.setPreviewDisplay(holder);
                    mCamera.startPreview();
                    onCameraStartListener.onSurfaceCreated();
                    KeyCommonUtil.logI("USB摄像头启动成功");
                } catch (Exception e) {
                    e.printStackTrace();
                    KeyCommonUtil.remoteLogE("Camera启动失败: " + e.getMessage());
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                closeCamera();
                onCameraStartListener.onSurfaceDestroyed();
            }
        });
    }

    public static void closeCamera() {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.setPreviewCallbackWithBuffer(null);
            mCamera.release();
            mCamera = null;
        }
    }

    public static byte[] getCurrentPreviewData() {
        return mCurrentPreviewData;
    }

    public static Camera getCamera() {
        return mCamera;
    }

    public static void copyData(@NonNull byte[] copyData) {
        System.arraycopy(mCurrentPreviewData, 0, copyData, 0, mCurrentPreviewData.length);
    }

    public interface OnCameraStartListener {
        void onOpenCameraFailed(Exception e);

        void onSurfaceCreated();

        void onSurfaceDestroyed();

        /**
         * 不要做耗时操作，最大耗时不可超过50MS
         *
         * @param data 图像数据，NV21
         */
        void onPreviewFrame(byte[] data);

        void onDataCreated(int size);
    }
}
