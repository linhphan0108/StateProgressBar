package com.example.linh.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by linh on 2/19/2017.
 */

public class CircleView extends BaseCustomView implements View.OnClickListener{

    private final static String TAG = BaseCustomView.class.getName();

    private int defRadius;
    private int strokeWidth;
    private int strokeColor;

    private Paint paint;

    public CircleView(Context context) {
        super(context);
        constructor(context, null, 0);
    }

    public CircleView(Context context, AttributeSet attrs) {
//        super(context, attrs);
        this(context, attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        constructor(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CircleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        constructor(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        int w = this.getWidth();//include padding
        int h = this.getHeight();//include padding
        int t = this.getTop();
        int l = this.getLeft();

        int ox = w/2;
        int oy = h/2;
        int rad = Math.min(ox - getPaddingLeft() - getPaddingRight(), oy - getPaddingTop() - getPaddingBottom()) - strokeWidth;
        canvas.drawCircle(ox, oy, rad, paint);
    }

    @Override
    protected void onRestoreInstanceState(Parcelable p) {
        Log.d(TAG, "onRestoreInstanceState");
        super.onRestoreInstanceState(p);
    }
    @Override
    protected Parcelable onSaveInstanceState() {
        Log.d(TAG, "onSaveInstanceState");
        Parcelable p = super.onSaveInstanceState();
        return p;
    }

    @Override
    public void onClick(View v) {
        //increase the radius
        defRadius *= 1.2;
        setMinimumDimension();
        requestLayout();
        invalidate();
    }


    @Override
    protected int hGetMaximumHeight() {
        return defRadius * 2;
    }

    @Override
    protected int hGetMaximumWidth() {
        return defRadius * 2;
    }

    @Override
     protected void constructor(Context context, AttributeSet attrs, int defStyleAttr){
        getAttrs(context, attrs, defStyleAttr);
        init();

        //Say we respond to clicks
        this.setOnClickListener(this);
        this.setClickable(true);

        //allow for stat management
        this.setSaveEnabled(true);
    }


    private void init(){
        //Set the minimum width and height
        setMinimumDimension();

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(strokeColor);
        paint.setStyle(Paint.Style.STROKE);
    }

    private void getAttrs(Context context, AttributeSet attrs, int defStyle){
        if (attrs == null){
            return;
        }

        TypedArray t = context.obtainStyledAttributes(attrs,
                R.styleable.CircleView,
                defStyle, //if any values are in the theme
                0); //Do you have your own style group

        //Use the offset in the bag to get your value
        strokeColor = t.getColor(R.styleable.CircleView_strokeColor, strokeColor);
        strokeWidth = t.getDimensionPixelSize(R.styleable.CircleView_strokeWidth, strokeWidth);

        //Recycle the typed array
        t.recycle();
    }

    private void setMinimumDimension() {
        this.setMinimumHeight(defRadius * 2);
        this.setMinimumWidth(defRadius * 2);
    }
}
