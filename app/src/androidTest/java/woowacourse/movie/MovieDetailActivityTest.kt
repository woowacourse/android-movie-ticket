package woowacourse.movie

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.activity.MovieDetailActivity

@RunWith(AndroidJUnit4::class)
class MovieDetailActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MovieDetailActivity::class.java)

    @Test
    fun 초기값은1_마이너스버튼클릭_티켓수량0표시() {
        Espresso.onView(withId(R.id.minus_button))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.minus_button))
            .perform(click())
        Espresso.onView(withId(R.id.quantity_text_view)).check(matches(withText("0")))
    }

    @Test
    fun 초기값은1_플러스버튼클릭_티켓수량2표시() {
        Espresso.onView(withId(R.id.plus_button))
            .check(matches(isDisplayed()))

        Espresso.onView(withId(R.id.plus_button))
            .perform(click())
        Espresso.onView(withId(R.id.quantity_text_view)).check(matches(withText("2")))
    }

    @Test
    fun 티켓구매버튼표시_화면에보임() {
        Espresso.onView(withId(R.id.buy_ticket_button))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 티켓구매버튼클릭_취소가능시간표시() {
        Espresso.onView(withId(R.id.buy_ticket_button))
            .perform(click())
        Espresso.onView(withId(R.id.can_cancel_time))
            .check(matches(isDisplayed()))
    }

    @Test
    fun 다음화면에서_뒤로가기버튼클릭_영화디테일화면표시() {
        Espresso.onView(withId(R.id.buy_ticket_button))
            .perform(click())
        Espresso.pressBack()
        Espresso.onView(withId(R.id.scroll_view)).check(matches(isDisplayed()))
    }
}
