package com.ebanx.swipebutton

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeBtnEnabled.background = ContextCompat.getDrawable(this, R.drawable.shape_button2)
        swipeBtnEnabled.setSlidingButtonBackground(ContextCompat.getDrawable(this, R.drawable.shape_rounded2))
        swipeBtnEnabled.setInnerTextPadding(10, 10, 10, 10)

        swipeBtnChangeColor.setButtonColor(R.color.swipeLightGreen);
        swipeBtnChangeColor.setLayoutBgColor(R.color.swipeDarkGreen);

        swipeBtnEnabled.setOnStateChangeListener { active ->
            Toast.makeText(this@MainActivity, "State: " + active, Toast.LENGTH_SHORT).show()
            if (active) {
                swipeBtnEnabled.setButtonBackground(ContextCompat.getDrawable(this@MainActivity, R.drawable.shape_button))
            } else {
                swipeBtnEnabled.setButtonBackground(ContextCompat.getDrawable(this@MainActivity, R.drawable.shape_button3))
            }
        }

        swipeNoState.setOnActiveListener { Toast.makeText(this@MainActivity, "Active!", Toast.LENGTH_SHORT).show() }

        toggleBtn.setOnClickListener {
            if (!swipeBtnEnabled.isActive) {
                swipeBtnEnabled.toggleState()
            }
        }

    }

}
