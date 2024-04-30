package woowacourse.movie.java

import android.content.pm.ActivityInfo
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.reservation.view.MovieReservationActivity

@RunWith(AndroidJUnit4::class)
class MovieReservationTest {
    @get:Rule
    val activityRule: ActivityScenarioRule<MovieReservationActivity> = ActivityScenarioRule(MovieReservationActivity::class.java)

    @Before
    fun setUp() {
        activityRule.scenario.onActivity {
            it.presenter.model.initTicketCount(1)
        }
    }

    @Test
    fun `플러스_버튼을_누르면_개수가_늘어나야_한다`() {
        onView(withId(R.id.plus_button))
            .perform(click())
        onView(withId(R.id.ticket_count))
            .check(matches(withText("2")))
    }

    @Test
    fun `마이너스_버튼을_누르면_개수가_감소해야_한다`() {
        onView(withId(R.id.plus_button))
            .perform(click())
        onView(withId(R.id.plus_button))
            .perform(click())
        onView(withId(R.id.minus_button))
            .perform(click())
        onView(withId(R.id.ticket_count))
            .check(matches(withText("2")))
    }

    @Test
    fun `인원수를_2로_증가시킨후_회전시_유지되어야_한다`() {
        onView(withId(R.id.plus_button)).perform(click())

        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        onView(withId(R.id.ticket_count)).check(matches(withText("2")))
    }
}
