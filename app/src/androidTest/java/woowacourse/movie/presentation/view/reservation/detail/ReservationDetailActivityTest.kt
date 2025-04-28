package woowacourse.movie.presentation.view.reservation.detail

import android.content.pm.ActivityInfo
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.domain.model.movie.ScreeningPeriod
import woowacourse.movie.presentation.extension.toDateTimeFormatter
import woowacourse.movie.presentation.fixture.fakeContext
import woowacourse.movie.presentation.model.MovieUiModel
import woowacourse.movie.presentation.model.PosterUiModel
import java.time.LocalDate

class ReservationDetailActivityTest {
    private lateinit var scenario: ActivityScenario<ReservationDetailActivity>
    private val fakeMovie =
        MovieUiModel(
            1,
            "해리 포터와 마법사의 돌",
            PosterUiModel.Resource(R.drawable.harrypotter),
            ScreeningPeriod(
                LocalDate.now(),
                LocalDate.now().plusDays(1),
            ),
            152,
        )

    @Before
    fun setUp() {
        val intent = ReservationDetailActivity.newIntent(fakeContext, fakeMovie)
        scenario = ActivityScenario.launch(intent)
    }

    @Test
    fun `영화_제목을_보여준다`() {
        onView(withId(R.id.tv_reservation_title))
            .check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun `영화_상영_기간을_보여준다`() {
        onView(withId(R.id.tv_screening_period))
            .check(matches(withText(formatPeriod(fakeMovie))))
    }

    @Test
    fun `영화_상영_시간을_보여준다`() {
        onView(withId(R.id.tv_reservation_running_time))
            .check(matches(withText("러닝타임: 152분")))
    }

    @Test
    fun `예매_인원의_초기값은_1이다`() {
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
    fun `화면을_회전해도_데이터가_유지된다`() {
        scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        onView(withId(R.id.main))
            .perform(swipeUp())

        onView(withId(R.id.btn_reservation_count_plus))
            .perform(click())
            .perform(click())

        onView(withId(R.id.tv_reservation_count))
            .check(matches(withText("3")))
    }

    @Test
    fun `선택_가능한_날짜가_없다면_다이얼로그가_노출된다`() {
        scenario.onActivity { activity ->
            val intent =
                ReservationDetailActivity.newIntent(
                    fakeContext,
                    fakeMovie
                        .copy(
                            screeningPeriod =
                                ScreeningPeriod(
                                    LocalDate.of(2025, 1, 1),
                                    LocalDate.of(2025, 1, 1),
                                ),
                        ),
                )
            activity.startActivity(intent)
        }

        Thread.sleep(1000)
        onView(withText("선택 가능한 날짜/시간이 없습니다"))
            .check(matches(isDisplayed()))
    }

    private fun formatPeriod(movie: MovieUiModel): String {
        val formatter = "yyyy.M.d".toDateTimeFormatter()
        val start = movie.screeningPeriod.startDate.format(formatter)
        val end = movie.screeningPeriod.endDate.format(formatter)
        return "상영일: %s ~ %s".format(start, end)
    }
}
