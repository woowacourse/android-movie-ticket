package woowacourse.movie.view

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
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
import java.time.LocalDate
import java.time.LocalTime

class TicketingActivityTest {
    @get:Rule
    var activityRule: ActivityScenarioRule<TicketingActivity> =
        ActivityScenarioRule<TicketingActivity>(
            Intent(
                ApplicationProvider.getApplicationContext(),
                TicketingActivity::class.java,
            ).apply {
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
    fun `인원수_증가_및_신청_일시_변경_후_화면_회전_시에도_데이터가_유지된다`() {
        // TODO 테스트 케이스 실패 해결하기
        val activityScenario = activityRule.scenario
        onView(withId(R.id.btn_plus)).perform(click())
        onView(withId(R.id.spinner_date)).perform(click())
        onData(
            allOf(
                `is`(instanceOf(LocalDate::class.java)),
                `is`(LocalDate.of(2024, 3, 8)),
            ),
        ).perform(click())
        activityScenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        onView(withId(R.id.tv_count)).check(matches(withText("2")))
        onView(withId(R.id.spinner_date)).check(matches(withSpinnerText(containsString("2024-03-08"))))
    }

    @Test
    fun `스피너에서_관람_날짜를_선택_시_정확한_날짜가_선택된다`() {
        onView(withId(R.id.spinner_date)).perform(click())
        onData(
            allOf(
                `is`(instanceOf(LocalDate::class.java)),
                `is`(LocalDate.of(2024, 3, 8)),
            ),
        ).perform(click())
        onView(withId(R.id.spinner_date)).check(matches(withSpinnerText(containsString("2024-03-08"))))
    }

    @Test
    fun `스피너에서_관람_시간을_선택_시_정확한_시간이_선택된다`() {
        onView(withId(R.id.spinner_time)).perform(click())
        onData(allOf(`is`(instanceOf(LocalTime::class.java)), `is`(LocalTime.of(23, 0)))).perform(
            click(),
        )
        onView(withId(R.id.spinner_time)).check(matches(withSpinnerText(containsString("23:00"))))
    }

    @Test
    fun `스피너에서_평일_날짜를_선택했을_경우_올바른_시간들이_표출된다`() {
        onView(withId(R.id.spinner_date)).perform(click())
        onData(
            allOf(
                `is`(instanceOf(LocalDate::class.java)),
                `is`(LocalDate.of(2024, 3, 5)),
            ),
        ).perform(click())

        onView(withId(R.id.spinner_time)).perform(click())
        WEEKDAY_TIMES.forEach(::doesTimeExist)
    }

    @Test
    fun `스피너에서_주말_날짜를_선택했을_경우_올바른_시간들이_표출된다`() {
        onView(withId(R.id.spinner_date)).perform(click())
        onData(allOf(`is`(instanceOf(LocalDate::class.java)), `is`(LocalDate.of(2024, 3, 2)))).perform(click())
        onView(withId(R.id.spinner_time)).perform(click())
        WEEKEND_TIMES.forEach(::doesTimeExist)
    }

    private fun doesTimeExist(time: LocalTime) {
        onData(
            allOf(
                `is`(instanceOf(LocalTime::class.java)),
                `is`(time),
            ),
        ).check(matches(isDisplayed()))
    }

    companion object {
        private val WEEKDAY_TIMES =
            listOf<LocalTime>(
                LocalTime.of(9, 0),
                LocalTime.of(11, 0),
                LocalTime.of(13, 0),
                LocalTime.of(15, 0),
                LocalTime.of(17, 0),
                LocalTime.of(19, 0),
                LocalTime.of(21, 0),
                LocalTime.of(23, 0),
            )

        private val WEEKEND_TIMES =
            listOf<LocalTime>(
                LocalTime.of(10, 0),
                LocalTime.of(12, 0),
                LocalTime.of(14, 0),
                LocalTime.of(16, 0),
                LocalTime.of(18, 0),
                LocalTime.of(20, 0),
                LocalTime.of(22, 0),
                LocalTime.of(0, 0),
            )
    }
}
