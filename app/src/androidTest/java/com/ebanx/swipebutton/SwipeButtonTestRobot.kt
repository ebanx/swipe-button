package com.ebanx.swipebutton

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.CoordinatesProvider
import android.support.test.espresso.action.GeneralLocation
import android.support.test.espresso.action.Press
import android.support.test.espresso.action.Swipe
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.swipeRight
import android.support.test.espresso.matcher.ViewMatchers.withId
import ebanx.com.ego.utils.CenterSwipeAction

/**
 * Created by vinicius on 13/06/17.
 */
class SwipeButtonTestRobot {

    fun enableButton() : SwipeButtonTestRobot {
        onView(withId(R.id.swipeBtnDisabled)).perform(swipeRight())
        return this
    }

    fun disableButton() : SwipeButtonTestRobot {
        onView(withId(R.id.swipeBtnDisabled)).perform(click())
        return this
    }

    fun enableFromMidOfButton() : SwipeButtonTestRobot {
        onView(withId(R.id.swipeBtnDisabled)).perform(swipeFromCenterToRight())
        return this
    }

    private fun swipeFromCenterToRight(): ViewAction {
        val endCoordProvide = CoordinatesProvider { view ->
            val coordinates = GeneralLocation.CENTER.calculateCoordinates(view)
            coordinates[0] = 1500f
            coordinates
        }
        return CenterSwipeAction(Swipe.FAST,
                GeneralLocation.CENTER,
                endCoordProvide,
                Press.FINGER)
    }

    infix fun result(func: SwipeButtonResultRobot.() -> Unit) = SwipeButtonResultRobot().apply { func() }
}