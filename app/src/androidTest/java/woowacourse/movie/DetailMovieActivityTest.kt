package woowacourse.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class DetailMovieActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MovieReservationActivity::class.java)

    @Test
    fun test_isActivityInView() {
        onView(withId(R.id.detail_movie)).check(matches(isDisplayed()))
    }
}
