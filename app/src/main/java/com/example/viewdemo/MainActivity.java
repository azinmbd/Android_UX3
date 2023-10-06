package com.example.viewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    final String TAG = "GESTURE DEMO";
    boolean bigger = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtViewSample = findViewById(R.id.txtViewSample);
        ImageView imgViewSample = findViewById(R.id.imgViewSample);

        Log.d(TAG, "Started Gesture Demo");

        try {
            //set of stmts
        } catch (Exception ex) {
            Log.e(TAG, "File error");
            //Toast to the user
            ex.printStackTrace();
        }

        Drawable img = ResourcesCompat.getDrawable(getResources(),
                R.drawable.border, getTheme());

        Objects.requireNonNull(img, "Somehow image is null");

        img.setBounds(0, 0,
                img.getIntrinsicWidth(), img.getIntrinsicHeight());

        txtViewSample.setCompoundDrawables(img, null, img, null);
        txtViewSample.setCompoundDrawablePadding(8);
        txtViewSample.setAlpha(1.0f); //alpha 1 - opaque; 0 - fully transparent

        Button btnShowTextOrImage = findViewById(R.id.btnShowTextOrImage);
        Button btnShowBoth = findViewById(R.id.btnShowBoth);

        btnShowTextOrImage.setOnClickListener((View view) -> {
            if (btnShowTextOrImage.getText().toString().equals("Show Text")) {
                txtViewSample.setVisibility(View.VISIBLE);
                imgViewSample.setVisibility(View.INVISIBLE);
                btnShowTextOrImage
                        .setText(getResources().getText(R.string.txtShowImage));
            } else {
                imgViewSample.setVisibility(View.VISIBLE);
                txtViewSample.setVisibility(View.GONE);
                btnShowTextOrImage.
                        setText(getResources().getText(R.string.txtShowText));
            }
        });

        btnShowBoth.setOnClickListener((View view) -> {
            txtViewSample.setVisibility(View.VISIBLE);
            imgViewSample.setVisibility(View.VISIBLE);
        });

        txtViewSample.setOnTouchListener(
                new CustomTouchListener(MainActivity.this) {
                    @Override
                    public void onDoubleClick() {
                        if (!bigger) {
                            txtViewSample.setTextSize(
                                    txtViewSample.getTextSize() / getResources().getDisplayMetrics().density + 10
                            );
                            bigger = true;
                        } else {
                            txtViewSample.setTextSize(
                                    txtViewSample.getTextSize() / getResources().getDisplayMetrics().density - 10
                            );
                            bigger = false;
                        }
                        super.onDoubleClick();
                    }

                    @Override
                    public void onSingleClick() {
                        if (txtViewSample.getCurrentTextColor() !=
                                ResourcesCompat.getColor(getResources(),
                                        R.color.purple_200, getTheme())) {
                            txtViewSample.setTextColor(ResourcesCompat.getColor
                                    (getResources(), R.color.purple_200, getTheme()));
                        } else {
                            // txtViewSample.setTextColor(Color.WHITE);
                            txtViewSample.setTextColor(Color.rgb(255, 255, 255));
                            // txtViewSample.setTextColor(Color.parseColor("#FFFFFF"));
                        }
                        super.onSingleClick();
                    }

                    @Override
                    public void onLongClick() {
                        //long click behaviour for textview
                        if (txtViewSample.getPaint().isStrikeThruText()) {
                            //turning off strike thru
                            txtViewSample.setPaintFlags(
                                    txtViewSample.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG
                            );
                        } else {
                            //turning on strike thru
                            txtViewSample.setPaintFlags
                                    (txtViewSample.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                        }
                        super.onLongClick();
                    }

                    @Override
                    public void onSwipeUp() {
                        int horzGravity = txtViewSample.getGravity() & Gravity.HORIZONTAL_GRAVITY_MASK;
                        txtViewSample.setGravity(horzGravity | Gravity.TOP);
                        super.onSwipeUp();
                    }

                    @Override
                    public void onSwipeDown() {
                        int horzGravity = txtViewSample.getGravity() & Gravity.HORIZONTAL_GRAVITY_MASK;
                        txtViewSample.setGravity(horzGravity | Gravity.BOTTOM);
                        super.onSwipeDown();
                    }

                    @Override
                    public void onSwipeLeft() {
                        int verGravity = txtViewSample.getGravity() & Gravity.VERTICAL_GRAVITY_MASK;
                        txtViewSample.setGravity(verGravity | Gravity.LEFT);
                        super.onSwipeLeft();
                    }

                    @Override
                    public void onSwipeRight() {
                        int verGravity = txtViewSample.getGravity() & Gravity.VERTICAL_GRAVITY_MASK;
                        txtViewSample.setGravity(verGravity | Gravity.RIGHT);
                        super.onSwipeRight();
                    }

                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        return super.onTouch(view, motionEvent);
                    }
                });
        imgViewSample.setOnTouchListener(new CustomTouchListener(MainActivity.this) {
            @Override
            public void onDoubleClick() {
                if (imgViewSample.getScaleType() != ImageView.ScaleType.FIT_CENTER) {

                    imgViewSample.setScaleType(ImageView.ScaleType.FIT_CENTER);
                }else {
                    imgViewSample.setScaleType(ImageView.ScaleType.FIT_XY);
                }
            }

            @Override
            public void onSingleClick() {
                super.onSingleClick();
                if (imgViewSample.getDrawable().getConstantState() !=
                        ResourcesCompat
                                .getDrawable(getResources(), R.drawable.bird, getTheme())
                                .getConstantState()) {
                    imgViewSample.setImageResource(R.drawable.bird);

                } else {
                    imgViewSample.setImageResource(R.drawable.fire);
                }

            }
        });
    }
}