package com.ebanx.swipebutton;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ebanx.swipebtn.OnActiveListener;
import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SwipeButton swipeButton = (SwipeButton) findViewById(R.id.swipe_btn_enabled);
        final SwipeButton swipeButtonNoState = (SwipeButton) findViewById(R.id.swipe_no_keep_state);

        swipeButton.setBackground(ContextCompat.getDrawable(this, R.drawable.shape_button2));
        swipeButton.setSlidingButtonBackground(ContextCompat.getDrawable(this, R.drawable.shape_rounded2));
        swipeButton.setInnerTextPadding(10, 10, 10, 10);

        swipeButton.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
                Toast.makeText(MainActivity.this, "State: " + active, Toast.LENGTH_SHORT).show();
                if (active) {
                    swipeButton.setButtonBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.shape_button));
                } else {
                    swipeButton.setButtonBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.shape_button3));
                }
            }
        });

        swipeButtonNoState.setOnActiveListener(new OnActiveListener() {
            @Override
            public void onActive() {
                Toast.makeText(MainActivity.this, "Active!", Toast.LENGTH_SHORT).show();
            }
        });

        Button toggleBtn = (Button) findViewById(R.id.toggleBtn);
        toggleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!swipeButton.isActive()) {
                    swipeButton.toggleState();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

}
