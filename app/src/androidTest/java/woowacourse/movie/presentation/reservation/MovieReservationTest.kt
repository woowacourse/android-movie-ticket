package woowacourse.movie.presentation.reservation

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R

@RunWith(AndroidJUnit4::class)
class MovieReservationTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MovieReservationActivity::class.java)

    @Test
    fun `플러스_버튼을_누르면_개수가_늘어나야_한다`() {
        Espresso.onView(ViewMatchers.withId(R.id.plus_button))
            .perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.ticket_count))
            .check(ViewAssertions.matches(ViewMatchers.withText("2")))
    }

    @Test
    fun `마이너스_버튼을_누르면_개수가_감소해야_한다`() {
        Espresso.onView(ViewMatchers.withId(R.id.plus_button))
            .perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.plus_button))
            .perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.minus_button))
            .perform(click())
        Espresso.onView(ViewMatchers.withId(R.id.ticket_count))
            .check(ViewAssertions.matches(ViewMatchers.withText("2")))
    }
}
