package woowacourse.movie.view.reservation

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
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.extension.ResourceMapper
import woowacourse.movie.feature.movieSelect.adapter.ScreeningData
import woowacourse.movie.feature.reservation.ReservationActivity
import java.time.LocalDate
import java.time.LocalTime

class ReservationActivityTest {
    val screeningData =
        ScreeningData(
            title = "영화 제목",
            startDate = LocalDate.of(2025, 4, 16),
            endDate = LocalDate.of(2025, 4, 21),
            movieId = "HarryPotter1",
            runningTime = 152,
            ResourceMapper.movieIdToPosterImageResource("HarryPotter1"),
        )

    @get:Rule
    val activityRule =
        ActivityScenarioRule<ReservationActivity>(
            ReservationActivity.newIntent(
                ApplicationProvider.getApplicationContext(),
                screeningData,
            ),
        )

    private fun rotateToLandscape() {
        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Thread.sleep(1500)
    }

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
    fun `티켓_인원수의_기본값이_표시된다`() {
        onView(withId(R.id.tv_reservation_audience_count))
            .check(matches(withText("1")))
    }

    @Test
    fun `티켓_인원수를_버튼으로_증가시킬_수_있다`() {
        onView(withId(R.id.tv_reservation_audience_count))
            .check(matches(withText("1")))

        onView(withId(R.id.btn_reservation_plus))
            .perform(click())
        onView(withId(R.id.tv_reservation_audience_count))
            .check(matches(withText("2")))
    }

    @Test
    fun `티켓_인원수는_기본값_이하로_감소시킬_수_없다`() {
        onView(withId(R.id.tv_reservation_audience_count))
            .check(matches(withText("1")))

        onView(withId(R.id.btn_reservation_minus))
            .perform(click())
        onView(withId(R.id.tv_reservation_audience_count))
            .check(matches(withText("1")))
    }

    @Test
    fun `티켓_인원수를_버튼으로_감소시킬_수_있다`() {
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
    fun `인원수_증가_후_화면_회전시_데이터가_유지된다`() {
        // 인원수 증가
        onView(withId(R.id.btn_reservation_plus))
            .perform(click())

        onView(withId(R.id.tv_reservation_audience_count))
            .check(matches(withText("2")))

        // 화면 회전
        rotateToLandscape()

        // 인원수 유지 확인
        onView(withId(R.id.tv_reservation_audience_count))
            .check(matches(withText("2")))
    }

    @Test
    fun `날짜_선택_후_화면_회전시_데이터가_유지된다`() {
        // 4월 20일(일요일) 선택
        onView(withId(R.id.spinner_reservation_screening_date))
            .perform(click())
        onData(
            allOf(
                `is`(instanceOf(LocalDate::class.java)),
                `is`(LocalDate.of(2025, 4, 20)),
            ),
        ).perform(click())

        // 화면 회전
        rotateToLandscape()

        // 4월 20일(일요일) 유지 확인
        onView(withId(R.id.spinner_reservation_screening_date))
            .check(matches(withSpinnerText(LocalDate.of(2025, 4, 20).toString())))
    }

    @Test
    fun `시간_선택_후_화면_회전시_데이터가_유지된다`() {
        // 4월 20일(일요일) 13시 선택
        onView(withId(R.id.spinner_reservation_screening_date))
            .perform(click())
        onData(
            allOf(
                `is`(instanceOf(LocalDate::class.java)),
                `is`(LocalDate.of(2025, 4, 20)),
            ),
        ).perform(click())

        onView(withId(R.id.spinner_reservation_screening_time))
            .perform(click())
        onData(
            allOf(
                `is`(instanceOf(LocalTime::class.java)),
                `is`(LocalTime.of(13, 0)),
            ),
        ).perform(click())

        // 화면 회전
        rotateToLandscape()

        // 4월 20일(일요일) 13시 유지 확인
        onView(withId(R.id.spinner_reservation_screening_date))
            .check(matches(withSpinnerText(LocalDate.of(2025, 4, 20).toString())))
        onView(withId(R.id.spinner_reservation_screening_time))
            .check(matches(withSpinnerText(LocalTime.of(13, 0).toString())))
    }
}
