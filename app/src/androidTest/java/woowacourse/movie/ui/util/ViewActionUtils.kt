package woowacourse.movie.ui.util

import android.view.View
import androidx.annotation.IdRes
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import org.hamcrest.Matcher

fun findViewByIdAndClickAction(description: String, @IdRes findId: Int): ViewAction {
    return object : ViewAction {
        override fun getDescription(): String = description

        override fun getConstraints(): Matcher<View>? = null

        override fun perform(uiController: UiController, view: View) {
            val v = view.findViewById<View>(findId)
            v.performClick()
        }
    }
}

fun ViewInteraction.checkMatches(matcher: Matcher<View>): ViewInteraction =
    check(matches(matcher))
