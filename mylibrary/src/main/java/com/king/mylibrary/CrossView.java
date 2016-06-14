package com.king.mylibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;

public class CrossView extends View {

    private Point cp;
    private Paint paint;
    private boolean drawLine = true;
    public int sen = 50;//滑动手势灵敏度
    public float parentX;//填充 父控件的   决定控件可拖动的位置大小
    public float parentY;
    private float[] mLineA;
    private float[] mLineB;

    public CrossView(Context context) {
        super(context);
//        init(parent);
        if (paint == null || cp == null) {
            paint = new Paint();
            cp = new Point();
        }
        setFocusable(true);
    }

    public void init(final View parent, final int initX, final int initY) {

        ViewTreeObserver vto = parent.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                parentY = parent.getHeight();
                parentX = parent.getWidth();

                mLineA = new float[]{0, parentY / 2, parentX, parentY / 2};
                mLineB = new float[]{parentX / 2, parentY, parentX / 2, 0};

                setX(initX);
                setY(initY);

                postInvalidate();
                parent.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

    }

    public void init(View parent) {//可做初始化中间使用
        init(parent, -1, -1);
    }

    public void setX(int x) {
        cp.x = (x == -1 ? (int) (parentX / 2 + .5f) : x);

        mLineB[0] = (x == -1 ? (int) (parentX / 2 + .5f) : x);
        mLineB[2] = (x == -1 ? (int) (parentX / 2 + .5f) : x);
    }

    /**
     * @param y y=0
     */
    public void setY(int y) {
        cp.y = (y == -1 ? (int) (parentY / 2 + .5f) : y);
        mLineA[1] = (y == -1 ? (int) (parentY / 2 + .5f) : y);
        mLineA[3] = (y == -1 ? (int) (parentY / 2 + .5f) : y);
    }

    // the method that draws the balls
    @Override
    protected void onDraw(Canvas canvas) {
        // canvas.drawColor(0xFFCCCCCC); //if you want another background color
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.FILL);//填充绘制
        paint.setStrokeJoin(Paint.Join.ROUND);//交界圆弧
//        paint.setStrokeCap(Paint.Cap.ROUND);//末尾圆角

        if (drawLine) {
            paint.setColor(Color.RED);
            paint.setStrokeWidth(15);//线宽
        }

        canvas.drawLine(mLineA[0], mLineA[1], mLineA[2], mLineA[3], paint);
        canvas.drawLine(mLineB[0], mLineB[1], mLineB[2], mLineB[3], paint);

        paint.setColor(Color.BLUE);
        canvas.drawCircle(cp.x, cp.y, 25f, paint);//绘制点
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {

        super.onLayout(changed, left, top, right, bottom);
    }


    public boolean onTouchEvent(MotionEvent event) {

        int X = (int) (event.getX() + .5f);
        int Y = (int) (event.getY() + .5f);

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:

                if (X <= cp.x + sen & X > cp.x - sen && Y <= cp.y + sen & Y > cp.y - sen) {//只能拖动点 不能随意绘制位置
                    // Log.d("XY: ", "X" + X + "-----" + "Y" + Y);
                    if (X >= parentX) {
                        X = (int) (parentX + .5f);
                    } else if (X <= 0) {
                        X = 0;
                    }

                    if (Y >= parentY) {
                        Y = (int) (parentY + .5f);
                    } else if (Y <= 0) {
                        Y = 0;
                    }

                    paint.setColor(Color.CYAN);//点击变色
                    drawLine = false;
                    cp.x = X;
                    cp.y = Y;

                    mLineA[1] = Y;
                    mLineA[3] = Y;
                    mLineB[0] = X;
                    mLineB[2] = X;
//                invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                drawLine = true;
                break;
        }

        invalidate();
        return true;
    }


    public int[] getEQ() {
        return new int[]{cp.x, cp.y};
    }
}
