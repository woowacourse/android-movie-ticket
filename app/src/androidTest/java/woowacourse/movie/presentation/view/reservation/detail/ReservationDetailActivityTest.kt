package woowacourse.movie.presentation.view.reservation.detail

import android.content.pm.ActivityInfo
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeUp
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Before
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.Poster
import woowacourse.movie.domain.model.RunningTime
import woowacourse.movie.domain.model.ScreeningPeriod
import woowacourse.movie.presentation.fixture.fakeContext
import woowacourse.movie.presentation.model.toUiModel
import java.time.LocalDate

class ReservationDetailActivityTest {
    private lateinit var scenario: ActivityScenario<ReservationDetailActivity>
    private val fakeMovie =
        Movie(
            1,
            "해리 포터와 마법사의 돌",
            Poster.Resource(R.drawable.harrypotter),
            ScreeningPeriod(
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2025, 4, 25),
            ),
            RunningTime(152),
        )

    @Before
    fun setUp() {
        val intent = ReservationDetailActivity.newIntent(fakeContext, fakeMovie.toUiModel())
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
            .check(matches(withText("상영일: 2025.4.1 ~ 2025.4.25")))
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
    fun `선택_완료_버튼을_누르면_예매_확인_다이얼로그가_노출된다`() {
        onView(withId(R.id.btn_reservation_finish))
            .perform(click())

        onView(withText("예매 확인")).check(matches(isDisplayed()))
    }

    @Test
    fun `예매_확인_다이얼로그_밖_영역을_터치해도_닫히지_않는다`() {
        onView(withId(R.id.btn_reservation_finish))
            .perform(click())

        pressBack()

        onView(withText("예매 확인")).check(matches(isDisplayed()))
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
                        ).toUiModel(),
                )
            activity.startActivity(intent)
        }

        Thread.sleep(1000)
        onView(withText("선택 가능한 날짜/시간이 없습니다"))
            .check(matches(isDisplayed()))
    }
}
