package woowacourse.movie

import android.content.pm.ActivityInfo
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.anything
import org.junit.Before
import org.junit.Test
import woowacourse.movie.booking.detail.BookingDetailActivity
import woowacourse.movie.booking.detail.BookingDetailActivity.Companion.newIntent
import woowacourse.movie.domain.Movie
import woowacourse.movie.movies.toMovieUiModel
import java.time.LocalDate

@Suppress("ktlint:standard:function-naming")
class BookingDetailActivityTest {
    private lateinit var activityScenario: ActivityScenario<BookingDetailActivity>

    @Before
    fun setup() {
        val movie =
            Movie(
                title = "해리 포터와 마법사의 돌",
                startDate = LocalDate.of(2025, 4, 1),
                endDate = LocalDate.of(2025, 4, 25),
                runningTime = 152,
                poster = R.drawable.img_poster_harry_potter_and_the_philosophers_stone,
            ).toMovieUiModel()

        val intent =
            newIntent(
                context = getApplicationContext(),
                uiModel = movie,
            )

        activityScenario = ActivityScenario.launch(intent)
    }

    @Test
    fun 선택한_날이_평일이면_기본_시간은_9시로_설정된다() {
        onView(withId(R.id.sp_booking_detail_time))
            .check(matches(withSpinnerText("09:00")))
    }

    @Test
    fun 선택한_날이_주말이면_기본_시간은_10시로_설정된다() {
        onView(withId(R.id.sp_booking_detail_date))
            .perform(click())

        onData(anything())
            .atPosition(4)
            .perform(click())

        onView(withId(R.id.sp_booking_detail_time))
            .check(matches(withSpinnerText("10:00")))
    }

    @Test
    fun 플러스_버튼을_누르면_티켓_장수가_1_증가한다() {
        onView(withId(R.id.btn_booking_detail_count_up))
            .perform(click())

        onView(withId(R.id.tv_booking_detail_count))
            .check(matches(withText("2")))
    }

    @Test
    fun 마이너스_버튼을_누르면_티켓_장수가_1_감소한다() {
        onView(withId(R.id.btn_booking_detail_count_up))
            .perform(click())
            .perform(click())

        onView(withId(R.id.btn_booking_detail_count_down))
            .perform(click())

        onView(withId(R.id.tv_booking_detail_count))
            .check(matches(withText("2")))
    }

    @Test
    fun 티켓_장수가_0일때_마이너스_버튼을_눌러도_변동되지_않는다() {
        onView(withId(R.id.btn_booking_detail_count_down))
            .perform(click())
            .perform(click())

        onView(withId(R.id.tv_booking_detail_count))
            .check(matches(withText("0")))
    }

    @Test
    fun 화면을_회전해도_입력_정보가_유지된다() {
        onView(withId(R.id.btn_booking_detail_count_up))
            .perform(click())
        activityScenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        onView(withId(R.id.tv_booking_detail_count))
            .check(matches(withText("2")))
    }
}
