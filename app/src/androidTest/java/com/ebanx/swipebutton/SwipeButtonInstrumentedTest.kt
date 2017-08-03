package com.ebanx.swipebutton

import android.app.KeyguardManager
import android.content.Context
import android.support.test.rule.ActivityTestRule
import android.view.WindowManager
import com.ebanx.swipebtn.SwipeButton
import kotlinx.android.synthetic.main.content_main.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SwipeButtonInstrumentedTest {

    @Rule
    @JvmField
    val activityTest = ActivityTestRule(MainActivity::class.java)

    @Suppress("DEPRECATION")
    @Before
    fun setUp() {
//        activityTest.activity.runOnUiThread {
//            val mKG = activityTest.activity.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
//            val mLock = mKG.newKeyguardLock(Context.KEYGUARD_SERVICE)
//            mLock.disableKeyguard()
//
//            //turn the screen on
//            activityTest.activity.window.addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
//                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
//                    WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or
//                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON or
//                    WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON)
//        }
    }

    @Test
    fun shouldSetEnabledAfterSwipe() {
        test {
            enableButton()
        } result {
            checkButtonIsEnabled(activityTest.activity.swipeBtnDisabled as SwipeButton)
        }
    }

    @Test
    fun shouldSetDisabledAfterClick() {
        test {
            enableButton()
            disableButton()
        } result {
            checkButtonIsDisabled(activityTest.activity.swipeBtnDisabled as SwipeButton)
        }
    }

    @Test
    fun shouldCreateButtonCorrectly() {
        test { } result {
            checkButtonIsDisabled(activityTest.activity.swipeBtnDisabled as SwipeButton)
            checkButtonIsEnabled(activityTest.activity.swipeBtnDisabled as SwipeButton)
            shouldBeginWithRightText("SWIPE")
        }
    }

    @Test
    fun shouldNotAcceptSwipeFromOutsideMovingPart() {
        test {
            enableFromMidOfButton()
        } result {
            checkButtonIsDisabled(activityTest.activity.swipeBtnDisabled as SwipeButton)
        }
    }

    fun test(func: SwipeButtonTestRobot.() -> Unit) = SwipeButtonTestRobot().apply { func() }
}