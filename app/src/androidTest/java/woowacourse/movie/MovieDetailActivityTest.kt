package woowacourse.movie

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.activity.MovieDetailActivity

@RunWith(AndroidJUnit4::class)
class MovieDetailActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MovieDetailActivity::class.java)

    @Test
    fun minusButtonTest() {
        Espresso.onView(withId(R.id.minus_button))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.minus_button))
            .perform(click())
        Espresso.onView(withId(R.id.quantity_text_view)).check(matches(withText("0")))
    }

    @Test
    fun plusButtonTest() {
        Espresso.onView(withId(R.id.plus_button))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.plus_button))
            .perform(click())
        Espresso.onView(withId(R.id.quantity_text_view)).check(matches(withText("2")))
    }

    @Test
    fun buyTicketButtonTest() {
        Espresso.onView(withId(R.id.buy_ticket_button))
            .check(matches(isDisplayed()))
    }

    @Test
    fun buyTicketButtonClickTest() {
        Espresso.onView(withId(R.id.buy_ticket_button))
            .perform(click())
        Espresso.onView(withId(R.id.can_cancel_time))
            .check(matches(isDisplayed()))
    }

    @Test
    fun pressBackTest() {
        Espresso.pressBack()
        Espresso.onView(withId(R.id.movies_list_item))
            .check(matches(isDisplayed()))
    }

}
