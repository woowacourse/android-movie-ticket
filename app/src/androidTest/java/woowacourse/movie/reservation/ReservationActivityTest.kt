package woowacourse.movie.reservation

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.domain.reservation.Movie
import woowacourse.movie.domain.reservation.Screening
import woowacourse.movie.view.reservation.ReservationActivity
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ReservationActivityTest {
    @get:Rule
    val activityRule =
        ActivityScenarioRule<ReservationActivity>(
            ReservationActivity.newIntent(
                ApplicationProvider.getApplicationContext(),
                Screening(
                    movie =
                        Movie(
                            0,
                            "해리 포터와 마법사의 돌",
                            runningTime = 152,
                        ),
                    start = LocalDate.of(2025, 4, 18),
                    end = LocalDate.of(2025, 4, 21),
                    current = LocalDateTime.of(2025, 4, 19, 8, 0),
                ),
            ),
        )

    @Test
    fun `영화_제목이_표시된다`() {
        onView(withId(R.id.tv_reservation_movie_title))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `상영일이_표시된다`() {
        onView(withId(R.id.tv_reservation_movie_period))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `포스터가_표시된다`() {
        onView(withId(R.id.iv_reservation_poster))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `러닝타임이_표시된다`() {
        onView(withId(R.id.tv_reservation_movie_running_time))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `티켓_인원수를_버튼으로_증감시킬_수_있다`() {
        onView(withId(R.id.tv_reservation_audience_count))
            .check(matches(withText("1")))

        onView(withId(R.id.btn_reservation_plus))
            .perform(click())
        onView(withId(R.id.tv_reservation_audience_count))
            .check(matches(withText("2")))

        onView(withId(R.id.btn_reservation_minus))
            .perform(click())
        onView(withId(R.id.tv_reservation_audience_count))
            .check(matches(withText("1")))
    }

    @Test
    fun `날짜를_선택하면_가능한_시간대가_표시된다`() {
        onView(withId(R.id.spinner_reservation_screening_date)).perform(click())
        onData(
            allOf(
                `is`(instanceOf(LocalDate::class.java)),
                `is`(LocalDate.of(2025, 4, 20)),
            ),
        ).perform(click())

        onView(withId(R.id.spinner_reservation_screening_time)).perform(click())

        val hours = listOf(9, 11, 13, 15, 17, 19, 21, 23)
        for (hour in hours) {
            onData(
                allOf(
                    `is`(instanceOf(LocalTime::class.java)),
                    `is`(LocalTime.of(hour, 0, 0)),
                ),
            ).check(matches(isDisplayed()))
        }
    }

    @Test
    fun `사용자는_인원_날짜_선택_후에_좌석을_고를_수_있다`() {
        onView(withId(R.id.btn_reservation_select_complete))
            .perform(click())

        onView(withId(R.id.layout_seat_selection))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `화면이_다시_그려질_때_데이터를_유지한다`() {
        onView(withId(R.id.btn_reservation_plus))
            .perform(click())

        onView(withId(R.id.btn_reservation_plus))
            .perform(click())

        activityRule.scenario.recreate()

        onView(withId(R.id.tv_reservation_audience_count))
            .check(matches(withText("3")))
    }
}
