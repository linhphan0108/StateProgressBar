package com.example.linh.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by linh on 2/19/2017.
 */

public abstract class BaseCustomView extends View {
    private final static String TAG = BaseCustomView.class.getName();

    public BaseCustomView(Context context) {
        super(context);
        constructor(context, null, 0);
    }

    public BaseCustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        constructor(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BaseCustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        constructor(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        logSpec(MeasureSpec.getMode(widthMeasureSpec));
        Log.e(TAG, "size:" + MeasureSpec.getSize(widthMeasureSpec));
        Log.e(TAG, "********************");

        setMeasuredDimension(getImprovedDefaultWidth(widthMeasureSpec), getImprovedDefaultHeight(heightMeasureSpec));
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    protected abstract void constructor(Context context, AttributeSet attrs, int defStyleAttr);

    private void logSpec(int specMode)
    {
        /**
         *  Documentation says that this mode is passed in when the layout wants to
         know what the true size is. True size could be as big as it could be; layout will likely then scroll it.
         With that thought, we have returned the maximum size for our view.
         */
        if (specMode == MeasureSpec.UNSPECIFIED) {
            Log.e(TAG,"mode: unspecified");
            return;
        }

        /**
         * wrap_content:  The size that gets passed could be much larger, taking up the rest of the space. So it might
         say, “I have 411 pixels. Tell me your size that doesn’t exceed 411 pixels.” The question then to the
         programmer is: What should I return?
         */
        if (specMode == MeasureSpec.AT_MOST) {
            Log.e(TAG,"mode: at most");
            return;
        }
        /**
         *  match_parent: the size will be equal parent's size
         *  exact pixels: specified size which is set
         */
        if (specMode == MeasureSpec.EXACTLY) {
            Log.e(TAG,"mode: exact");
            return;
        }
    }

    private int getImprovedDefaultHeight(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize =  MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            /**
             *  Documentation says that this mode is passed in when the layout wants to
             know what the true size is. True size could be as big as it could be; layout will likely then scroll it.
             With that thought, we have returned the maximum size for our view.
             */
            case MeasureSpec.UNSPECIFIED:
                return hGetMaximumHeight();

            /**
             *wrap_content:  The size that gets passed could be much larger, taking up the rest of the space. So it might
             say, “I have 411 pixels. Tell me your size that doesn’t exceed 411 pixels.” The question then to the
             programmer is: What should I return?
             */
            case MeasureSpec.AT_MOST:
                return hGetMinimumHeight();

            /**
             *  match_parent: the size will be equal parent's size
             *  exact pixels: specified size which is set
             */
            case MeasureSpec.EXACTLY:
                return specSize;
        }
        //you shouldn't come here
        Log.e(TAG, "unknown specmode");
        return specSize;
    }

    private int getImprovedDefaultWidth(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize =  MeasureSpec.getSize(measureSpec);

        switch (specMode) {
            case MeasureSpec.UNSPECIFIED:
                return hGetMaximumWidth();

            /**
             * wrap_content:  The size that gets passed could be much larger, taking up the rest of the space. So it might
             say, “I have 411 pixels. Tell me your size that doesn’t exceed 411 pixels.” The question then to the
             programmer is: What should I return?
             */
            case MeasureSpec.AT_MOST:
                return hGetMinimumWidth();

            /**
             *  match_parent: the size will be equal parent's size
             *  exact pixels: specified size which is set
             */
            case MeasureSpec.EXACTLY:
                return specSize;
        }
        //you shouldn't come here
        Log.e(TAG, "unknown specmode");
        return specSize;
    }

    //Override these methods to provide a maximum size
    //"h" stands for hook pattern
    abstract protected int hGetMaximumHeight();
    abstract protected int hGetMaximumWidth();

    // For minimum height use the View's methods
    // "h" stands for hook pattern
    protected int hGetMinimumHeight() {
        return this.getSuggestedMinimumHeight();
    }
    protected int hGetMinimumWidth() {
        return this.getSuggestedMinimumWidth();
    }
}
