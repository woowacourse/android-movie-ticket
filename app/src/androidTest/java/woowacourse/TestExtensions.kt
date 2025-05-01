package woowacourse

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isEnabled
import androidx.test.espresso.matcher.ViewMatchers.isNotEnabled
import androidx.test.espresso.matcher.ViewMatchers.withText

fun ViewInteraction.performClick(): ViewInteraction = perform(click())

fun ViewInteraction.checkMatchText(value: String): ViewInteraction = check(matches(withText(value)))

fun ViewInteraction.checkIsDisplayed(): ViewInteraction = check(matches(isDisplayed()))

fun ViewInteraction.checkIsEnabled(enabled: Boolean): ViewInteraction =
    if (enabled == true) {
        check(matches(isEnabled()))
    } else {
        check(matches(isNotEnabled()))
    }
