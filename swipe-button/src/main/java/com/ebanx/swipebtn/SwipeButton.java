package com.ebanx.swipebtn;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by leandroferreira on 07/03/17.
 *
 */

public class SwipeButton extends RelativeLayout {

    private ImageView slidingButton;
    private float initialX;
    private boolean active;
    private int initialButtonWidth;
    private TextView centerText;

    private Drawable disabledDrawable;
    private Drawable enabledDrawable;

    private OnStateChangeListener onStateChangeListener;

    public SwipeButton(Context context) {
        super(context);

        init(context, null, -1, -1);
    }

    public SwipeButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs, -1, -1);
    }

    public SwipeButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs, defStyleAttr, -1);
    }

    @TargetApi(21)
    public SwipeButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(context, attrs, defStyleAttr, defStyleRes);
    }
    
    public boolean isActive() {
        return active;
    }

    public void setOnStateChangeListener(OnStateChangeListener onStateChangeListener) {
        this.onStateChangeListener = onStateChangeListener;
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        RelativeLayout background = new RelativeLayout(context);

        LayoutParams layoutParamsView = new LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutParamsView.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        addView(background, layoutParamsView);

        final TextView centerText = new TextView(context);
        this.centerText = centerText;
        centerText.setGravity(Gravity.CENTER);

        LayoutParams layoutParams = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);


        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        background.addView(centerText, layoutParams);

        final ImageView swipeButton = new ImageView(context);

        this.slidingButton = swipeButton;

        LayoutParams layoutParamsButton = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        layoutParamsButton.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        layoutParamsButton.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);

        addView(swipeButton, layoutParamsButton);

        if (attrs != null && defStyleAttr == -1 && defStyleRes == -1) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SwipeButton,
                    defStyleAttr, defStyleRes);

            Drawable drawable = typedArray.getDrawable(R.styleable.SwipeButton_inner_text_background);

            if(drawable != null) {
                background.setBackground(drawable);
            } else {
                background.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_rounded));
            }

            centerText.setText(typedArray.getText(R.styleable.SwipeButton_inner_text));
            centerText.setTextColor(typedArray.getColor(R.styleable.SwipeButton_inner_text_color,
                    Color.WHITE));

            float textSize = DimentionUtils.converPixelsToSp(
                    typedArray.getDimension(R.styleable.SwipeButton_inner_text_size, 0), context);

            if (textSize != 0) {
                centerText.setTextSize(textSize);
            } else {
                centerText.setTextSize(12);
            }

            disabledDrawable = typedArray.getDrawable(R.styleable.SwipeButton_button_image_disabled);
            enabledDrawable = typedArray.getDrawable(R.styleable.SwipeButton_button_image_enabled);
            float innerTextLeftPadding = typedArray.getDimension(
                    R.styleable.SwipeButton_inner_text_left_padding, 0);
            float innerTextTopPadding = typedArray.getDimension(
                    R.styleable.SwipeButton_inner_text_top_padding, 0);
            float innerTextRightPadding = typedArray.getDimension(
                    R.styleable.SwipeButton_inner_text_right_padding, 0);
            float innerTextBottomPadding = typedArray.getDimension(
                    R.styleable.SwipeButton_inner_text_bottom_padding, 0);

            centerText.setPadding((int) innerTextLeftPadding,
                    (int)innerTextTopPadding,
                    (int) innerTextRightPadding,
                    (int) innerTextBottomPadding);

            Drawable buttonBackground =
                    typedArray.getDrawable(R.styleable.SwipeButton_button_background);

            if (buttonBackground != null) {
                swipeButton.setBackground(buttonBackground);
            } else {
                swipeButton.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_button));
            }

            swipeButton.setImageDrawable(disabledDrawable);

            float buttonLeftPadding = typedArray.getDimension(
                    R.styleable.SwipeButton_button_left_padding, 0);
            float buttonTopPadding = typedArray.getDimension(
                    R.styleable.SwipeButton_button_top_padding, 0);
            float buttonRightPadding = typedArray.getDimension(
                    R.styleable.SwipeButton_button_right_padding, 0);
            float buttonBottomPadding = typedArray.getDimension(
                    R.styleable.SwipeButton_button_bottom_padding, 0);

            swipeButton.setPadding((int) buttonLeftPadding,
                    (int) buttonTopPadding,
                    (int) buttonRightPadding,
                    (int) buttonBottomPadding);

            typedArray.recycle();
        }

        setOnTouchListener(getButtonTouchListener());
    }

    private OnTouchListener getButtonTouchListener() {
        return new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    return true;
                case MotionEvent.ACTION_MOVE:
                    if (initialX == 0) {
                        initialX = slidingButton.getX();
                    }

                    if (event.getX() > initialX + slidingButton.getWidth() / 2 &&
                            event.getX() + slidingButton.getWidth() / 2 < getWidth()) {
                        slidingButton.setX(event.getX() - slidingButton.getWidth() / 2);
                        centerText.setAlpha(1 - 1.3f * (slidingButton.getX() + slidingButton.getWidth()) / getWidth());
                    }

                    if  (event.getX() + slidingButton.getWidth() / 2 > getWidth() &&
                            slidingButton.getX() + slidingButton.getWidth() / 2 < getWidth()) {
                        slidingButton.setX(getWidth() - slidingButton.getWidth());
                    }

                    if  (event.getX() < slidingButton.getWidth() / 2 &&
                            slidingButton.getX() > 0) {
                        slidingButton.setX(0);
                    }

                    return true;
                case MotionEvent.ACTION_UP:
                    if (active) {
                        collapseButton();
                    } else {
                        initialButtonWidth = slidingButton.getWidth();

                        if (slidingButton.getX() + slidingButton.getWidth() > getWidth() * 0.85) {
                            expandButton();
                        } else {
                            moveButtonBack();
                        }
                    }

                    return true;
                }

            return false;
            }
        };
    }

    private void expandButton() {
        final ValueAnimator positionAnimator =
                ValueAnimator.ofFloat(slidingButton.getX(), 0);
        positionAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float x = (Float) positionAnimator.getAnimatedValue();
                slidingButton.setX(x);
            }
        });


        final ValueAnimator widthAnimator = ValueAnimator.ofInt(
                slidingButton.getWidth(),
                getWidth());

        widthAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ViewGroup.LayoutParams params = slidingButton.getLayoutParams();
                params.width = (Integer) widthAnimator.getAnimatedValue();
                slidingButton.setLayoutParams(params);
            }
        });


        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

                active = true;
                slidingButton.setImageDrawable(enabledDrawable);

                if(onStateChangeListener != null) {
                    onStateChangeListener.onStateChange(active);
                }
            }
        });

        animatorSet.playTogether(positionAnimator, widthAnimator);
        animatorSet.start();
    }

    private void moveButtonBack() {
        final ValueAnimator positionAnimator =
                ValueAnimator.ofFloat(slidingButton.getX(), 0);
        positionAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        positionAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float x = (Float) positionAnimator.getAnimatedValue();
                slidingButton.setX(x);
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
                slidingButton.getWidth(),
                initialButtonWidth);

        widthAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ViewGroup.LayoutParams params =  slidingButton.getLayoutParams();
                params.width = (Integer) widthAnimator.getAnimatedValue();
                slidingButton.setLayoutParams(params);
            }
        });

        widthAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                active = false;
                slidingButton.setImageDrawable(disabledDrawable);

                if(onStateChangeListener != null) {
                    onStateChangeListener.onStateChange(active);
                }
            }
        });

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(
                centerText, "alpha", 1);

        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.playTogether(objectAnimator, widthAnimator);
        animatorSet.start();
    }


}
