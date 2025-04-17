package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.junit.Rule
import org.junit.Test
import woowacourse.movie.view.MainActivity.Companion.EXTRA_END_DAY
import woowacourse.movie.view.MainActivity.Companion.EXTRA_END_MONTH
import woowacourse.movie.view.MainActivity.Companion.EXTRA_END_YEAR
import woowacourse.movie.view.MainActivity.Companion.EXTRA_POSTER_ID
import woowacourse.movie.view.MainActivity.Companion.EXTRA_RUNNING_TIME
import woowacourse.movie.view.MainActivity.Companion.EXTRA_START_DAY
import woowacourse.movie.view.MainActivity.Companion.EXTRA_START_MONTH
import woowacourse.movie.view.MainActivity.Companion.EXTRA_START_YEAR
import woowacourse.movie.view.MainActivity.Companion.EXTRA_TITLE
import woowacourse.movie.view.ReservationActivity
import java.time.LocalTime

class ReservationActivityTest {
    @get:Rule
    val activityRule =
        ActivityScenarioRule<ReservationActivity>(
            Intent(
                ApplicationProvider.getApplicationContext(),
                ReservationActivity::class.java,
            ).apply {
                putExtra(EXTRA_TITLE, "영화 제목")
                putExtra(EXTRA_START_YEAR, 2025)
                putExtra(EXTRA_START_MONTH, 4)
                putExtra(EXTRA_START_DAY, 16)
                putExtra(EXTRA_END_YEAR, 2025)
                putExtra(EXTRA_END_MONTH, 4)
                putExtra(EXTRA_END_DAY, 18)
                putExtra(
                    EXTRA_POSTER_ID,
                    R.drawable.poster_harry_potter_and_the_philosophers_stone,
                )
                putExtra(EXTRA_RUNNING_TIME, 152)
            },
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
    fun `선택_완료를_클릭하면_다이얼로그가_표시된다`() {
        onView(withId(R.id.btn_reservation_select_complete))
            .perform(click())
        onView(withText(R.string.ticket_dialog_title))
            .inRoot(RootMatchers.isDialog())
            .check(matches(isDisplayed()))
    }

    @Test
    fun `날짜를_선택하면_가능한_시간대가_표시된다`() {
        // FIXME: 뷰가 2개 찾아지는 문제 해결 필요
        onData(
            allOf(
                `is`(instanceOf(LocalTime::class.java)),
                `is`(LocalTime.of(23, 30)),
//                `is`(LocalDate.of(2025, 4, 17)),
            ),
        ).check(matches(withId(R.id.spinner_reservation_screening_time)))
//
//        onView(withId(R.id.spinner_reservation_screening_time))
//            .check(matches(withSpinnerText("10:00")))
    }

    @Test
    fun `예매_완료를_확인하는_다이얼로그가_표시되고_배경을_터치해도_사라지지_않아야_한다`() {
        onView(withId(R.id.btn_reservation_select_complete))
            .perform(click())

        onView(isRoot())
            .perform(click())

        onView(withText(R.string.ticket_dialog_title))
            .check(matches(isDisplayed()))
    }
}
