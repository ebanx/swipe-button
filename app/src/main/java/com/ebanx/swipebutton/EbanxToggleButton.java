package com.ebanx.swipebutton;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by leandroferreira on 07/03/17.
 */

public class EbanxToggleButton extends RelativeLayout {

    private boolean isClicked;
    private Button mSwipeButton;
    private int initialButtonWidth;
    private int animationButtonWidth;

    public EbanxToggleButton(Context context) {
        super(context);

        init(context);
    }

    public EbanxToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public EbanxToggleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    public EbanxToggleButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(context);
    }

    private void init(Context context){
        isClicked = false;

        mSwipeButton = new Button(context);
        mSwipeButton.setText(null);
        mSwipeButton.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_button));

        LayoutParams layoutParamsButton = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        layoutParamsButton.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        layoutParamsButton.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);



        addView(mSwipeButton, layoutParamsButton);


        OnClickListener clickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isClicked){
                    animateCheck();
                    isClicked = true;
                } else{
                    animateUncheck();
                    isClicked = false;
                }
            }
        };

        setOnClickListener(clickListener);
        mSwipeButton.setOnClickListener(clickListener);

    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);

        initialButtonWidth = mSwipeButton.getWidth();
        animationButtonWidth = getWidth();
    }

    private void animateCheck(){
        final ValueAnimator expandAnimator = ValueAnimator.ofInt(initialButtonWidth, animationButtonWidth);
        expandAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mSwipeButton.setWidth((Integer) animation.getAnimatedValue());
            }
        });

        expandAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                setGravity(Gravity.LEFT);
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        final ValueAnimator shirinkAnimator = ValueAnimator.ofInt(animationButtonWidth, initialButtonWidth);
        shirinkAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mSwipeButton.setWidth((Integer) animation.getAnimatedValue());
            }
        });
        shirinkAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                setGravity(Gravity.RIGHT);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.playSequentially(expandAnimator, shirinkAnimator);

        animatorSet.addListener(getClickableListener());

        animatorSet.start();
    }

    private void animateUncheck(){
        final ValueAnimator expandAnimator = ValueAnimator.ofInt(initialButtonWidth, animationButtonWidth);
        expandAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mSwipeButton.setWidth((Integer) animation.getAnimatedValue());
            }
        });

        expandAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                setGravity(Gravity.RIGHT);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        final ValueAnimator shirinkAnimator = ValueAnimator.ofInt(animationButtonWidth, initialButtonWidth);
        shirinkAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mSwipeButton.setWidth((Integer) animation.getAnimatedValue());
            }
        });



        shirinkAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                setGravity(Gravity.LEFT);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.playSequentially(expandAnimator, shirinkAnimator);

        animatorSet.addListener(getClickableListener());

        animatorSet.start();
    }

    private Animator.AnimatorListener getClickableListener(){
        return new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                setClickable(false);
                mSwipeButton.setClickable(false);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                setClickable(true);
                mSwipeButton.setClickable(true);
            }

            @Override
            public void onAnimationCancel(Animator animation) {}

            @Override
            public void onAnimationRepeat(Animator animation) {}
        };
    }

}
