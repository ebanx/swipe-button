package com.ebanx.swipebutton

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.swipeRight
import android.support.test.espresso.matcher.ViewMatchers.withId

/**
 * Created by vinicius on 13/06/17.
 */
class SwipeButtonTestRobot {

    fun enableButton() : SwipeButtonTestRobot {
        onView(withId(R.id.swipe_btn_disabled)).perform(swipeRight())
        return this
    }

    fun disableButton() : SwipeButtonTestRobot {
        onView(withId(R.id.swipe_btn_disabled)).perform(click())
        return this
    }

    infix fun result(func: SwipeButtonResultRobot.() -> Unit) = SwipeButtonResultRobot().apply { func() }
}