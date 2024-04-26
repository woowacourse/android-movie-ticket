package woowacourse.movie.view

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.instanceOf
import org.hamcrest.Matchers.`is`
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R

class TicketingActivityTest {
    @get:Rule
    var activityRule: ActivityScenarioRule<TicketingActivity> =
        ActivityScenarioRule<TicketingActivity>(
            Intent(ApplicationProvider.getApplicationContext(), TicketingActivity::class.java).apply {
                putExtra("screening_id", 0L)
            },
        )

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

    @Test
    fun `인원수_증가_후_화면_회전_시에도_인원수가_유지된다`() {
        val activityScenario = activityRule.scenario
        onView(withId(R.id.btn_plus)).perform(click())
        activityScenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        onView(withId(R.id.tv_count)).check(matches(withText("2")))
    }

    @Test
    fun `스피너에서_관람_날짜를_선택_시_정확한_날짜가_선택된다`() {
        onView(withId(R.id.spinner_date)).perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)), `is`("2024-03-08"))).perform(click())
        onView(withId(R.id.spinner_date)).check(matches(withSpinnerText(containsString("2024-03-08"))))
    }

    @Test
    fun `스피너에서_관람_시간을_선택_시_정확한_시간이_선택된다`() {
        onView(withId(R.id.spinner_time)).perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)), `is`("23:00"))).perform(click())
        onView(withId(R.id.spinner_time)).check(matches(withSpinnerText(containsString("23:00"))))
    }
}
