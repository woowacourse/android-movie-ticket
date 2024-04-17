package woowacourse.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class TicketingResultActivityTest {
    @get:Rule
    val activityScenario = ActivityScenarioRule(TicketingResultActivity::class.java)

    @Test
    fun `예매_결과_데이터를_보여준다`() {
        onView(withId(R.id.tv_guide)).check(matches(withText(R.string.text_guide)))
        onView(withId(R.id.tv_movie_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_movie_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_number_of_people)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_price)).check(matches(isDisplayed()))
    }
}
