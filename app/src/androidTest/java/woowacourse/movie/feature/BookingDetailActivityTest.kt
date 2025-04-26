package woowacourse.movie.feature

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
import woowacourse.movie.MOVIE_UI_MODEL_01
import woowacourse.movie.R
import woowacourse.movie.feature.bookingdetail.view.BookingDetailActivity
import woowacourse.movie.feature.bookingdetail.view.BookingDetailActivity.Companion.newIntent

@Suppress("ktlint:standard:function-naming")
class BookingDetailActivityTest {
    private lateinit var activityScenario: ActivityScenario<BookingDetailActivity>

    @Before
    fun setup() {
        val intent =
            newIntent(
                context = getApplicationContext(),
                movie = MOVIE_UI_MODEL_01.copy(),
            )

        activityScenario = ActivityScenario.launch(intent)
    }

    @Test
    fun 선택한_날이_평일이면_기본_시간은_10시로_설정된다() {
        onView(withId(R.id.sp_booking_detail_time))
            .check(matches(withSpinnerText("10:00")))
    }

    @Test
    fun 선택한_날이_주말이면_기본_시간은_9시로_설정된다() {
        onView(withId(R.id.sp_booking_detail_date))
            .perform(click())

        onData(anything())
            .atPosition(4)
            .perform(click())

        onView(withId(R.id.sp_booking_detail_time))
            .check(matches(withSpinnerText("09:00")))
    }

    @Test
    fun 티켓_장수_초기값은_1이_출력된다() {
        onView(withId(R.id.tv_booking_detail_count))
            .check(matches(withText("1")))
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
    fun 티켓_장수가_1일때_마이너스_버튼을_눌러도_변동되지_않는다() {
        onView(withId(R.id.btn_booking_detail_count_down))
            .perform(click())

        onView(withId(R.id.tv_booking_detail_count))
            .check(matches(withText("1")))
    }

    @Test
    fun 화면을_회전해도_예매_정보가_유지된다() {
        onView(withId(R.id.sp_booking_detail_date))
            .perform(click())

        onData(anything())
            .atPosition(1)
            .perform(click())

        onView(withId(R.id.sp_booking_detail_time))
            .perform(click())

        onData(anything())
            .atPosition(1)
            .perform(click())

        onView(withId(R.id.btn_booking_detail_count_up))
            .perform(click())

        activityScenario.onActivity {
            it.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        onView(withId(R.id.sp_booking_detail_date))
            .check(matches(withSpinnerText("2025.04.02")))

        onView(withId(R.id.sp_booking_detail_time))
            .check(matches(withSpinnerText("12:00")))

        onView(withId(R.id.tv_booking_detail_count))
            .check(matches(withText("2")))
    }
}
