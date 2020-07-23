package com.push.sweateliteathletics.softrasol.services.utils;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.VideoView;

public class ScalableVideoView extends VideoView {

    private int mVideoWidth;
    private int mVideoHeight;
    private DisplayMode displayMode = DisplayMode.ORIGINAL;

    public enum DisplayMode {
        ORIGINAL,       // original aspect ratio
        FULL_SCREEN,    // fit to screen
        ZOOM            // zoom in
    };

    public ScalableVideoView(Context context) {
        super(context);
    }

    public ScalableVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScalableVideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mVideoWidth = 0;
        mVideoHeight = 0;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        ((Activity)getContext()).getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
        int screenHeight = metrics.heightPixels;
        int screenWidth = (screenHeight*9)/16;
        setMeasuredDimension(screenWidth, screenHeight);
    }

    public void changeVideoSize(int width, int height) {
        mVideoWidth = width;
        mVideoHeight = height;

        // not sure whether it is useful or not but safe to do so
        getHolder().setFixedSize(width, height);

        requestLayout();
        invalidate();     // very important, so that onMeasure will be triggered
    }

    public void setDisplayMode(DisplayMode mode) {
        displayMode = mode;
    }
}