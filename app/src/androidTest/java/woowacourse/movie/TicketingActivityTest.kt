package woowacourse.movie

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class TicketingActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(TicketingActivity::class.java)

    @Test
    fun `증가_버튼을_누르면_숫자가_증가한다`() {
        onView(withId(R.id.btn_plus)).perform(click())
        onView(withId(R.id.tv_count)).check(matches(withText("2")))
    }

    @Test
    fun `감소_버튼을_누르면_숫자가_감소한다`() {
        onView(withId(R.id.btn_plus)).perform(click())
        onView(withId(R.id.btn_plus)).perform(click())

        onView(withId(R.id.btn_minus)).perform(click())
        onView(withId(R.id.tv_count)).check(matches(withText("2")))
    }

    @Test
    fun `숫자가_예매_가능한_최솟값일_경우_감소_버튼을_누르면_숫자가_감소하지_않는다`() {
        onView(withId(R.id.btn_minus)).perform(click())
        onView(withId(R.id.tv_count)).check(matches(withText("1")))
    }
}
