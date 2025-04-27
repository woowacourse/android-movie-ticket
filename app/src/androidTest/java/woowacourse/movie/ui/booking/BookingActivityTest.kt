package woowacourse.movie.ui.booking

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.device.DeviceInteraction.Companion.setScreenOrientation
import androidx.test.espresso.device.EspressoDevice
import androidx.test.espresso.device.action.ScreenOrientation
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers
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
        EspressoDevice.Companion.onDevice().setScreenOrientation(ScreenOrientation.PORTRAIT)
        ActivityScenario.launch<BookingActivity>(intent)
    }

    @Test
    fun `영화_이름을_출력한다`() {
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_title))
            .check(ViewAssertions.matches(ViewMatchers.withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun `영화_상영일을_출력한다`() {
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_screening_period))
            .check(ViewAssertions.matches(ViewMatchers.withText("2025.5.1 ~ 2025.5.25")))
    }

    @Test
    fun `영화_상영시간을_출력한다`() {
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_running_time))
            .check(ViewAssertions.matches(ViewMatchers.withText("152분")))
    }

    @Test
    fun `영화_상영날짜_스피너에_날짜_목록이_표시된다`() {
        Espresso
            .onView(ViewMatchers.withId(R.id.sp_date))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun `영화_예매가능시간_스피너에_시간_목록이_표시된다`() {
        Espresso
            .onView(ViewMatchers.withId(R.id.sp_time))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun `인원_증가_버튼을_누르면_인원이_1_증가한다`() {
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_headcount))
            .check(ViewAssertions.matches(ViewMatchers.withText("1")))

        Espresso.onView(ViewMatchers.withId(R.id.btn_increase)).perform(ViewActions.click())

        Espresso
            .onView(ViewMatchers.withId(R.id.tv_headcount))
            .check(ViewAssertions.matches(ViewMatchers.withText("2")))
    }

    @Test
    fun `인원_감소_버튼을_누르면_인원이_1_감소한다`() {
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_headcount))
            .check(ViewAssertions.matches(ViewMatchers.withText("1")))

        Espresso.onView(ViewMatchers.withId(R.id.btn_increase)).perform(ViewActions.click())
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_headcount))
            .check(ViewAssertions.matches(ViewMatchers.withText("2")))

        Espresso.onView(ViewMatchers.withId(R.id.btn_decrease)).perform(ViewActions.click())
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_headcount))
            .check(ViewAssertions.matches(ViewMatchers.withText("1")))
    }

    @Test
    fun `인원은_1명_미만으로_감소하지_않는다`() {
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_headcount))
            .check(ViewAssertions.matches(ViewMatchers.withText("1")))

        Espresso.onView(ViewMatchers.withId(R.id.btn_decrease)).perform(ViewActions.click())

        Espresso
            .onView(ViewMatchers.withId(R.id.tv_headcount))
            .check(ViewAssertions.matches(ViewMatchers.withText("1")))
    }

//    @Test
//    fun `예매_선택_완료_버튼을_누르면_예매_확인_다이얼로그가_나타난다`() {
//        onView(withId(R.id.btn_booking_complete)).perform(click())
//
//        onView(withText("예매 확인")).check(matches(isDisplayed()))
//        onView(withText("정말 예매하시겠습니까?")).check(matches(isDisplayed()))
//        onView(withText("취소")).check(matches(isDisplayed()))
//        onView(withText("예매 완료")).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun `다이얼로그_외부_영역을_클릭해도_다이얼로그가_유지된다`() {
//        onView(withId(R.id.btn_booking_complete))
//            .perform(click())
//
//        pressBack()
//
//        onView(withText("예매 확인")).check(matches(isDisplayed()))
//        onView(withText("정말 예매하시겠습니까?")).check(matches(isDisplayed()))
//        onView(withText("취소")).check(matches(isDisplayed()))
//        onView(withText("예매 완료")).check(matches(isDisplayed()))
//    }

    @Test
    fun `화면이_회전되어도_인원수가_유지된다`() {
        Espresso.onView(ViewMatchers.withId(R.id.btn_increase)).perform(ViewActions.click())
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_headcount))
            .check(ViewAssertions.matches(ViewMatchers.withText("2")))

        EspressoDevice.Companion.onDevice().setScreenOrientation(ScreenOrientation.LANDSCAPE)
        Espresso
            .onView(ViewMatchers.withId(R.id.tv_headcount))
            .check(ViewAssertions.matches(ViewMatchers.withText("2")))

        EspressoDevice.Companion.onDevice().setScreenOrientation(ScreenOrientation.PORTRAIT)
    }

    // 시간 변경됨에 따라 실패하는 테스트입니다.
    @Test
    fun `화면이_회전되어도_선택된_날짜가_유지된다`() {
        Espresso.onView(ViewMatchers.withId(R.id.sp_date)).perform(ViewActions.click())
        Espresso.onData(CoreMatchers.anything()).atPosition(8).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.sp_time)).perform(ViewActions.click())
        Espresso.onData(CoreMatchers.anything()).atPosition(1).perform(ViewActions.click())
        Espresso
            .onView(ViewMatchers.withId(R.id.sp_date))
            .check(ViewAssertions.matches(ViewMatchers.withSpinnerText("2025-04-25")))
        Espresso
            .onView(ViewMatchers.withId(R.id.sp_time))
            .check(ViewAssertions.matches(ViewMatchers.withSpinnerText("12:00")))

        EspressoDevice.Companion.onDevice().setScreenOrientation(ScreenOrientation.LANDSCAPE)
        EspressoDevice.Companion.onDevice().setScreenOrientation(ScreenOrientation.PORTRAIT)

        Espresso
            .onView(ViewMatchers.withId(R.id.sp_date))
            .check(ViewAssertions.matches(ViewMatchers.withSpinnerText("2025-04-25")))
        Espresso
            .onView(ViewMatchers.withId(R.id.sp_time))
            .check(ViewAssertions.matches(ViewMatchers.withSpinnerText("12:00")))
    }
}
