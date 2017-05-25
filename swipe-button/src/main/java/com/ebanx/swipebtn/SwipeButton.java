package com.ebanx.swipebtn;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by leandroferreira on 07/03/17.
 *
 */

public class SwipeButton extends RelativeLayout {

    private ImageView swipeButton;
    private float initialX;
    private boolean isActive;
    private int initialButtonWidth;
    private TextView centerText;

    private boolean first = true;

    public SwipeButton(Context context) {
        super(context);

        init(context);
    }

    public SwipeButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public SwipeButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    public SwipeButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(context);
    }


    private void init(Context context){
        RelativeLayout view = new RelativeLayout(context);
        view.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_rounded));

        LayoutParams layoutParamsView = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutParamsView.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        addView(view, layoutParamsView);

        final TextView centerText = new TextView(context);
        this.centerText = centerText;
        centerText.setText("SWIPE");
        centerText.setGravity(Gravity.CENTER);
        centerText.setPadding(0, 50, 0, 50);

        LayoutParams layoutParams = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);


        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        view.addView(centerText, layoutParams);

        final ImageView swipeButton = new ImageView(context);

        swipeButton.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.ic_lock_open_black_24dp));

        swipeButton.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_button));
        swipeButton.setPadding(52, 52, 52, 52);
        swipeButton.setClickable(false);

        this.swipeButton = swipeButton;

        LayoutParams layoutParamsButton = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutParamsButton.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        layoutParamsButton.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);


        addView(swipeButton, layoutParamsButton);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        Log.d("Haa", "uhh");

                        return true;
                    }

                    case MotionEvent.ACTION_MOVE: {
                        if (initialX == 0) {
                            initialX = swipeButton.getX();
                        }

                        if (event.getX() > initialX + swipeButton.getWidth() / 2
                                && event.getX() + swipeButton.getWidth() / 2 < getWidth()) {
                            swipeButton.setX(event.getX() - swipeButton.getWidth() / 2);
                        }

                        centerText.setAlpha(1 - 1.3f*(swipeButton.getX() + swipeButton.getWidth()) / getWidth());

                        return true;
                    }

                    case MotionEvent.ACTION_UP: {
                        if (isActive) {
                            collapseButton();
                        } else {
                            initialButtonWidth = swipeButton.getWidth();

                            if (swipeButton.getX() + swipeButton.getWidth() > getWidth() * 0.85) {
                                expandButton();
                            } else {
                                moveButtonBack();
                            }
                        }
                    }

                }

                return false;
            }
        });
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void expandButton() {
        final ValueAnimator positionAnimator =
                ValueAnimator.ofFloat(swipeButton.getX(), 0);
        positionAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float x = (Float) positionAnimator.getAnimatedValue();
                swipeButton.setX(x);
            }
        });


        final ValueAnimator widthAnimator = ValueAnimator.ofInt(
                swipeButton.getWidth(),
                getWidth());

        widthAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ViewGroup.LayoutParams params = swipeButton.getLayoutParams();
                params.width = (Integer) widthAnimator.getAnimatedValue();
                swipeButton.setLayoutParams(params);
            }
        });


        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                isActive = true;
                swipeButton.setImageDrawable(ContextCompat.getDrawable(
                        getContext(), R.drawable.ic_lock_outline_black_24dp));

            }
        });

        animatorSet.playTogether(positionAnimator, widthAnimator);
        animatorSet.start();
    }

    private void moveButtonBack() {
        final ValueAnimator positionAnimator =
                ValueAnimator.ofFloat(swipeButton.getX(), 0);
        positionAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        positionAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float x = (Float) positionAnimator.getAnimatedValue();
                swipeButton.setX(x);
            }
        });

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(
                centerText, "alpha", 1);

        positionAnimator.setDuration(200);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator, positionAnimator);
        animatorSet.start();
    }

    private void collapseButton() {
        final ValueAnimator widthAnimator = ValueAnimator.ofInt(
                swipeButton.getWidth(),
                initialButtonWidth);

        widthAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ViewGroup.LayoutParams params =  swipeButton.getLayoutParams();
                params.width = (Integer) widthAnimator.getAnimatedValue();
                swipeButton.setLayoutParams(params);
            }
        });

        widthAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isActive = false;
                swipeButton.setImageDrawable(
                        ContextCompat.getDrawable(getContext(), R.drawable.ic_lock_open_black_24dp));
            }
        });

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(
                centerText, "alpha", 1);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator, widthAnimator);
        animatorSet.start();
    }




}
