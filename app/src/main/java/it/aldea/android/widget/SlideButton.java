package it.aldea.android.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.SeekBar;

import it.aldea.demo.R;

/**
 * Created by Roberto on 08/04/2016.
 */
public class SlideButton extends SeekBar {
    public static final int ORIENTATION_HORIZONTAL = 0;
    public static final int ORIENTATION_VERTICAL = 1;

    private Drawable thumb;
    private SlideButtonListener listener;
    private int orientation;

    public SlideButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public SlideButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs,defStyle);
        //TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.slideButton, defStyle, 0);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.slideButton);
        orientation = a.getInteger(R.styleable.slideButton_orientation, ORIENTATION_HORIZONTAL);
        a.recycle();
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (orientation == ORIENTATION_VERTICAL) {
            super.onMeasure(heightMeasureSpec, widthMeasureSpec);
            setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
        }else {
            super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onDraw(Canvas c) {
        if (orientation == ORIENTATION_VERTICAL) {
            c.rotate(90);
            c.translate(0, -getWidth());
        }
        super.onDraw(c);
    }
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(h, w, oldh, oldw);
    }


    @Override
    public void setThumb(Drawable thumb) {
        super.setThumb(thumb);
        this.thumb = thumb;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return false;
        }
        if (orientation == ORIENTATION_HORIZONTAL) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                int x= (int) event.getX();
                int y= (int) event.getY();
                if (thumb.getBounds().contains((int) event.getX(), (int) event.getY())) {
                    super.onTouchEvent(event);
                } else
                    return false;
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                if (getProgress() > 70)
                    handleSlide();

                setProgress(0);
            } else
                super.onTouchEvent(event);
        }else{
            int i=0;
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        int x= (int) event.getX();
                        int y= (int) event.getY();
                        if (!thumb.getBounds().contains((int) event.getY(), (int) event.getX())) {
                            return false;
//                            super.onTouchEvent(event);
//                            break;
                        }
                    }
                case MotionEvent.ACTION_MOVE:
                    i=getMax() - (int) (getMax() * event.getY() / getHeight());
                    setProgress(100 - i);
                    onSizeChanged(getWidth(), getHeight(), 0, 0);
                    break;
                case MotionEvent.ACTION_UP:
                    i=getMax() - (int) (getMax() * event.getY() / getHeight());
                    if (i < 30) {
                        handleSlide();
                    }
                    setProgress(0);
                    onSizeChanged(getWidth(), getHeight(), 0, 0);
                    break;

                case MotionEvent.ACTION_CANCEL:
                    break;
            }
        }
        return true;
    }

    private void handleSlide() {
        listener.handleSlide();
    }

    public void setSlideButtonListener(SlideButtonListener listener) {
        this.listener = listener;
    }
}


