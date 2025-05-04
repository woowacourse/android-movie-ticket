package woowacourse.movie.ui.booking

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.device.DeviceInteraction.Companion.setScreenOrientation
import androidx.test.espresso.device.EspressoDevice.Companion
import androidx.test.espresso.device.EspressoDevice.Companion.onDevice
import androidx.test.espresso.device.action.ScreenOrientation
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.*
import org.junit.Before
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.ScreeningDate
import woowacourse.movie.fixture.fakeContext
import woowacourse.movie.ui.booking.view.BookingActivity
import java.time.LocalDate

class BookingActivityTest {
    @Before
    fun setUp() {
        val intent =
            Intent(
                fakeContext,
                BookingActivity::class.java,
            ).apply {
                putExtra(
                    "movie",
                    Movie(
                        "해리 포터와 마법사의 돌",
                        R.drawable.harry_potter_one,
                        ScreeningDate(LocalDate.of(2025, 5, 1), LocalDate.of(2025, 5, 25)),
                        152,
                    ),
                )
            }
        onDevice().setScreenOrientation(ScreenOrientation.PORTRAIT)
        ActivityScenario.launch<BookingActivity>(intent)
    }

    @Test
    fun `영화_이름을_출력한다`() {
        onView(withId(R.id.tv_title))
            .check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun `영화_상영일을_출력한다`() {
        onView(withId(R.id.tv_screening_period))
            .check(matches(withText("2025.5.1 ~ 2025.5.25")))
    }

    @Test
    fun `영화_상영시간을_출력한다`() {
        onView(withId(R.id.tv_running_time))
            .check(matches(withText("152분")))
    }

    @Test
    fun `영화_상영날짜_스피너에_날짜_목록이_표시된다`() {
        onView(withId(R.id.sp_date))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `영화_예매가능시간_스피너에_시간_목록이_표시된다`() {
        onView(withId(R.id.sp_time))
            .check(matches(isDisplayed()))
    }

    @Test
    fun `인원_증가_버튼을_누르면_인원이_1_증가한다`() {
        onView(withId(R.id.tv_headcount))
            .check(matches(withText("1")))

        onView(withId(R.id.btn_increase)).perform(click())

        onView(withId(R.id.tv_headcount))
            .check(matches(withText("2")))
    }

    @Test
    fun `인원_감소_버튼을_누르면_인원이_1_감소한다`() {
        onView(withId(R.id.tv_headcount))
            .check(matches(withText("1")))

        onView(withId(R.id.btn_increase)).perform(click())
        onView(withId(R.id.tv_headcount))
            .check(matches(withText("2")))

        onView(withId(R.id.btn_decrease)).perform(click())
        onView(withId(R.id.tv_headcount))
            .check(matches(withText("1")))
    }

    @Test
    fun `인원은_1명_미만으로_감소하지_않는다`() {
        onView(withId(R.id.tv_headcount))
            .check(matches(withText("1")))

        onView(withId(R.id.btn_decrease)).perform(click())

        onView(withId(R.id.tv_headcount))
            .check(matches(withText("1")))
    }

    @Test
    fun `화면이_회전되어도_인원수가_유지된다`() {
        onView(withId(R.id.btn_increase)).perform(click())
        onView(withId(R.id.tv_headcount))
            .check(matches(withText("2")))

        Companion.onDevice().setScreenOrientation(ScreenOrientation.LANDSCAPE)
        onView(withId(R.id.tv_headcount))
            .check(matches(withText("2")))

        Companion.onDevice().setScreenOrientation(ScreenOrientation.PORTRAIT)
    }

    // 시간 변경됨에 따라 실패하는 테스트입니다.
    @Test
    fun `화면이_회전되어도_선택된_날짜가_유지된다`() {
        onView(withId(R.id.sp_date)).perform(click())
        onData(anything()).atPosition(8).perform(click())
        onView(withId(R.id.sp_time)).perform(click())
        onData(anything()).atPosition(1).perform(click())
        onView(withId(R.id.sp_date))
            .check(matches(withSpinnerText("2025-04-25")))
        onView(withId(R.id.sp_time))
            .check(matches(withSpinnerText("12:00")))

        Companion.onDevice().setScreenOrientation(ScreenOrientation.LANDSCAPE)
        Companion.onDevice().setScreenOrientation(ScreenOrientation.PORTRAIT)

        onView(withId(R.id.sp_date))
            .check(matches(withSpinnerText("2025-04-25")))
        onView(withId(R.id.sp_time))
            .check(matches(withSpinnerText("12:00")))
    }
}
