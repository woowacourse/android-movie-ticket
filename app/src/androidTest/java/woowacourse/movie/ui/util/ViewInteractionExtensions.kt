package woowacourse.movie.ui.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf

fun ViewInteraction.performScrollTo(position: Int): ViewInteraction =
    perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))

fun ViewInteraction.performAction(position: Int, viewAction: ViewAction): ViewInteraction =
    perform(
        actionOnItemAtPosition<RecyclerView.ViewHolder>(
            position,
            viewAction
        )
    )

fun ViewInteraction.checkDisplayed(id: Int): ViewInteraction =
    check(
        matches(
            hasDescendant(
                allOf(
                    withId(id),
                    isDisplayed()
                ),
            ),
        ),
    )

fun ViewInteraction.checkDisplayed(text: String): ViewInteraction =
    check(
        matches(
            hasDescendant(
                allOf(
                    withText(text),
                    isDisplayed()
                ),
            ),
        ),
    )

fun ViewInteraction.checkMatches(matcher: Matcher<View>): ViewInteraction =
    check(matches(matcher))

fun ViewInteraction.performClick(): ViewInteraction = perform(click())
