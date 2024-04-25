package woowacourse.movie.view

import android.content.pm.ActivityInfo
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R

class DetailActivityTest {
    @get:Rule
    val activityRule: ActivityScenarioRule<DetailActivity> =
        ActivityScenarioRule(DetailActivity::class.java)

    @Test
    fun 인원수를_2로_증가시킨_후_회전_시_값이_유지된다() {
        val activityScenario = activityRule.scenario
        onView(withId(R.id.button_plus)).perform(click())

        activityScenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        onView(withId(R.id.detail_person_counter)).check(matches(withText("2")))
    }
}
