package woowacourse.movie.view.reservation

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.RunningTime
import woowacourse.movie.domain.model.ScreeningPeriod
import woowacourse.movie.view.fixture.fakeContext
import java.time.LocalDate

class ReservationActivityTest {
    private lateinit var scenario: ActivityScenario<ReservationActivity>

    private val harryPotter =
        Movie(
            title = "해리 포터와 마법사의 돌",
            screeningPeriod = ScreeningPeriod(LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 25)),
            runningTime = RunningTime(152),
            posterResId = R.drawable.harrypotter.toString(),
        )

    @Before
    fun setUp() {
        val intent =
            Intent(fakeContext, ReservationActivity::class.java).putExtra("movie", harryPotter)
        scenario = ActivityScenario.launch(intent)
    }

    @Test
    fun `영화_제목을_보여준다`() {
        onView(withId(R.id.tv_reservation_title))
            .check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun `영화_상영기간을_보여준다`() {
        onView(withId(R.id.tv_screening_period))
            .check(matches(withText("상영일: 2025.4.1 ~ 2025.4.25")))
    }

    @Test
    fun `영화_러닝타임을_보여준다`() {
        onView(withId(R.id.tv_reservation_running_time))
            .check(matches(withText("러닝타임: 152분")))
    }

    @Test
    fun `예매_인원수의_초기값은_1이다`() {
        onView(withId(R.id.tv_reservation_count))
            .check(matches(withText("1")))
    }

    @Test
    fun `예매_인원수가_3일때_마이너스_버튼을_한_번_누르면_2가_된다`() {
        onView(withId(R.id.btn_reservation_count_plus))
            .perform(click())
            .perform(click())

        onView(withId(R.id.btn_reservation_count_minus))
            .perform(click())

        onView(withId(R.id.tv_reservation_count))
            .check(matches(withText("2")))
    }

    @Test
    fun `회전하여도_선택한_데이터가_유지된다`() {
        onView(withId(R.id.btn_reservation_count_plus))
            .perform(click())

        scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        onView(withId(R.id.tv_reservation_count))
            .check(matches(withText("2")))
    }
}
