package com.ebanx.swipebutton

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import com.ebanx.swipebtn.SwipeButton
import junit.framework.Assert
import org.hamcrest.Matchers.allOf

/**
 * Created by vinicius on 13/06/17.
 */
class SwipeButtonResultRobot {

    fun checkButtonIsEnabled(swipeButton: SwipeButton) {
        Assert.assertTrue(swipeButton.isActive)
    }

    fun checkButtonIsDisabled(swipeButton: SwipeButton) {
        Assert.assertFalse(swipeButton.isActive)
    }

    fun shouldBeginWithRightText(title: String) {
        onView(withText(title)).check(matches(isDisplayed()))
    }

}