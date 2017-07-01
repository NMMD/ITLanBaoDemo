package com.birdslove.android.jni;

import android.graphics.Bitmap;

/**
 * 图片模糊效果处理
 *  
 *
 */
public class ImageBlur {

    public static native void blurBitMap(Bitmap bitmap, int radius);

    static {
        System.loadLibrary("JNI_ImageBlur");
    }
}
