package com.example.viewdemo;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;

public class CustomTouchListener implements View.OnTouchListener{
    //create Gesture Detector object as data
    GestureDetectorCompat gestureDetectorCompat;

    //set up constructor to create gesture detector object
    //need a gesture listener - custom


    public CustomTouchListener(Context context) {
        gestureDetectorCompat = new GestureDetectorCompat(context,
                new CustomGestureListener());
    }

    public class CustomGestureListener
            extends GestureDetector.SimpleOnGestureListener {
        @Override
        public void onLongPress(@NonNull MotionEvent e) {
            onLongClick();
            super.onLongPress(e);
        }

        @Override
        public boolean onFling(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
            final int SWIPE_DIST_THRESHOLD = 10;
            final int SWIPE_VEL_THRESHOLD = 20;

            float distX = e2.getX() - e1.getX();
            float distY = e2.getY() - e1.getY();

            if (Math.abs(distX) > Math.abs(distY)
                && Math.abs(distX) > SWIPE_DIST_THRESHOLD
                && Math.abs(velocityX) > SWIPE_VEL_THRESHOLD){
                //horizontal swipe
                if (distX > 0){
                    onSwipeRight();
                } else {
                    onSwipeLeft();
                }
            } else if (Math.abs(distY) > Math.abs(distX)
                        && Math.abs(distY) > SWIPE_DIST_THRESHOLD
                        && Math.abs(velocityY) > SWIPE_VEL_THRESHOLD){
                //vertical swipe
                if (distY > 0){
                    onSwipeDown();
                } else {
                    onSwipeUp();
                }
            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public boolean onDown(@NonNull MotionEvent e) {
            //return super.onDown(e); //by default returns false
            return true;
        }

        @Override
        public boolean onDoubleTap(@NonNull MotionEvent e) {
            onDoubleClick();
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onSingleTapConfirmed(@NonNull MotionEvent e) {
            onSingleClick();
            return super.onSingleTapConfirmed(e);
        }
    }

    public void onSwipeUp(){
        Log.d("GESTURE DEMO","Swipe up");
    }
    public void onSwipeDown() {
        Log.d("GESTURE DEMO","Swipe down");
    }

    public void onSwipeLeft() {
        Log.d("GESTURE DEMO","Swipe left");
    }

    public void onSwipeRight() {
        Log.d("GESTURE DEMO","Swipe right");

    }

    public void onDoubleClick() {
        Log.d("GESTURE DEMO","Detected double click in custom touch");
    }

    public void onSingleClick() {
        Log.d("GESTURE DEMO","Detected single click in custom touch");

    }

    public void onLongClick() {
        Log.d("GESTURE DEMO","Detected long click in custom touch");
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //return false;
        //return gesture detector's on touch event
        return gestureDetectorCompat.onTouchEvent(motionEvent);
    }
}
