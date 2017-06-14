package com.ebanx.swipebutton

import android.support.test.rule.ActivityTestRule
import com.ebanx.swipebtn.SwipeButton
import org.junit.Rule
import org.junit.Test

class SwipeButtonInstrumentedTest {

    @Rule
    @JvmField
    val activityTest = ActivityTestRule(MainActivity::class.java)

    @Test
    fun shouldSetEnabledAfterSwipe() {
        test {
            enableButton()
        } result {
            checkButtonIsEnabled(activityTest.activity.findViewById(R.id.swipe_btn_disabled) as SwipeButton)
        }
    }

    @Test
    fun shouldSetDisabledAfterClick() {
        test {
            enableButton()
            disableButton()
        } result {
            checkButtonIsDisabled(activityTest.activity.findViewById(R.id.swipe_btn_disabled) as SwipeButton)
        }
    }

    @Test
    fun shouldCreateButtonCorrectly() {
        test { } result {
            checkButtonIsDisabled(activityTest.activity.findViewById(R.id.swipe_btn_disabled) as SwipeButton)
            checkButtonIsEnabled(activityTest.activity.findViewById(R.id.swipe_btn_enabled) as SwipeButton)
            shouldBeginWithRightText("SWIPE")
        }
    }

    @Test
    fun shouldNotAcceptSwipeFromOutsideMovingPart() {
        test {
            enableFromMidOfButton()
        } result {
            checkButtonIsDisabled(activityTest.activity.findViewById(R.id.swipe_btn_disabled) as SwipeButton)
        }
    }

    fun test(func: SwipeButtonTestRobot.() -> Unit) = SwipeButtonTestRobot().apply { func() }
}