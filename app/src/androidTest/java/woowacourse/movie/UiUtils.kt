package woowacourse.movie

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click

fun ViewInteraction.repeatClick(count: Int) {
    repeat(count) {
        perform(click())
    }
}
