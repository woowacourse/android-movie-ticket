package woowacourse.movie.detail

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.containsString
import org.hamcrest.Matchers.instanceOf
import org.hamcrest.Matchers.`is`
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class MovieDetailActivityTest {
    @get:Rule
    var activityRule = ActivityScenarioRule(MovieDetailActivity::class.java)

    @Test
    fun `영화_티켓_디폴트_값은_1이다`() {
        onView(withId(R.id.text_view_reservation_detail_number_of_tickets)).check(matches(withText("1")))
    }

    @Test
    fun `빼기_버튼을_누르면_영화_티켓_수가_1만큼_감소한다`() {
        onView(withId(R.id.button_reservation_detail_plus)).perform(click())

        onView(withId(R.id.button_reservation_detail_minus)).perform(click())

        onView(withId(R.id.text_view_reservation_detail_number_of_tickets)).check(matches(withText("1")))
    }

    @Test
    fun `더하기_버튼을_누르면_영화_티켓_수가_1만큼_증가한다`() {
        onView(withId(R.id.button_reservation_detail_plus)).perform(click())

        onView(withId(R.id.text_view_reservation_detail_number_of_tickets)).check(matches(withText("2")))
    }

    @Test
    fun `2024_01_01_상영날짜_선택시_2024_01_01_날짜가_보여진다`() {
        onView(withId(R.id.spinner_reservation_detail_screening_date)).perform(click())
        onData(
            allOf(
                `is`(instanceOf(LocalDate::class.java)),
                `is`(LocalDate.of(2024, 1, 1)),
            ),
        ).perform(click())
        onView(withId(R.id.spinner_reservation_detail_screening_date)).check(matches(withSpinnerText(containsString("2024-01-01"))))
    }

    @Test
    fun `10_00_상영시간_선택시_10_00_상영시간이_보여진다`() {
        onView(withId(R.id.spinner_reservation_detail_screening_time)).perform(click())
        onData(
            allOf(
                `is`(instanceOf(String::class.java)),
                `is`("10:00"),
            ),
        ).perform(click())
        onView(withId(R.id.spinner_reservation_detail_screening_time)).check(matches(withSpinnerText(containsString("10:00"))))
    }
}
