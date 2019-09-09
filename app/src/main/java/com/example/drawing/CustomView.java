package com.example.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Random;

public class CustomView extends View {
    private int mX, mY;
    private int mDX, mDY;

    private final int mRadius = 5;
    Paint mBallPaint;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mX = mY = -1;
        mDX = mDY = 0;
        mBallPaint = new Paint();
        mBallPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mX == -1) {
            initBall();
        } else {
            update();
        }

        canvas.drawColor(Color.WHITE);
        canvas.drawCircle(mX, mY, mRadius, mBallPaint);

        invalidate();
    }

    private void initBall() {
        Random random = new Random();
        mX = random.nextInt(getWidth());
        mY = random.nextInt(getHeight());

        mDX = random.nextInt(10) - 5;
        mDY = random.nextInt(10) - 5;
    }

    private  void update() {
        mX = mX + mDX;
        mY = mY + mDY;

        if (mX - mRadius < 0) {
            mX = mRadius;
            mDX = -mDX;
        } else if (mX + mRadius > getWidth()) {
            mX = getWidth() - mRadius;
            mDX = - mDX;
        }

        if (mY - mRadius < 0) {
            mY = mRadius;
            mDY = - mDY;
        } else if (mY + mRadius > getHeight()) {
            mY = getHeight() - mRadius;
            mDY = -mDY;
        }
    }
}
