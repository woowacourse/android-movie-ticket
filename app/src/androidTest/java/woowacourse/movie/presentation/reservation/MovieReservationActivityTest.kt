package woowacourse.movie.presentation.reservation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R

@RunWith(AndroidJUnit4::class)
class MovieReservationActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MovieReservationActivity::class.java)

    @Test
    fun `플러스_버튼을_누르면_개수가_늘어나야_한다`() {
        onView(withId(R.id.plus_button))
            .perform(click())
        onView(withId(R.id.ticket_count))
            .check(matches(withText("2")))
    }

    @Test
    fun `마이너스_버튼을_누르면_개수가_감소해야_한다`() {
        onView(withId(R.id.plus_button)).perform(click())
        onView(withId(R.id.plus_button)).perform(click())
        onView(withId(R.id.minus_button)).perform(click())
        onView(withId(R.id.ticket_count)).check(matches(withText("2")))
    }
}
