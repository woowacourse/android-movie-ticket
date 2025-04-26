package woowacourse.movie.view.booking

import android.content.pm.ActivityInfo
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.anything
import org.junit.Before
import org.junit.Test
import woowacourse.movie.R
import woowacourse.movie.fixture.fakeContext

class BookingActivityTest {
    private lateinit var scenario: ActivityScenario<BookingActivity>

    @Before
    fun setUp() {
        val intent =
            Intent(
                fakeContext,
                BookingActivity::class.java,
            ).apply {
                putExtra(KEY_MOVIE, 1)
            }

        scenario = ActivityScenario.launch(intent)
    }

    @Test
    fun `전달_받은_영화_이름_상영일_상영_시간을_출력한다`() {
        onView(withText("해리 포터와 마법사의 돌")).check(matches(isDisplayed()))
        onView(withText("2025.5.1 ~ 2025.5.25")).check(matches(isDisplayed()))
        onView(withText("152분")).check(matches(isDisplayed()))
    }

    @Test
    fun `상영_날짜_스피너에_날짜_목록이_표시된다`() {
        onView(withId(R.id.sp_date)).check(matches(isDisplayed()))
    }

    @Test
    fun `예매_가능_시간_스피너에_시간_목록이_표시된다`() {
        onView(withId(R.id.sp_time)).check(matches(isDisplayed()))
    }

    @Test
    fun `인원_증가_버튼을_누르면_인원이_1_증가한다`() {
        // given
        onView(withId(R.id.tv_people_count)).check(matches(withText("1")))

        // when
        onView(withId(R.id.btn_increase)).perform(click())

        // then
        onView(withId(R.id.tv_people_count)).check(matches(withText("2")))
    }

    @Test
    fun `인원_감소_버튼을_누르면_인원이_1_감소한다`() {
        // given
        onView(withId(R.id.tv_people_count)).check(matches(withText("1")))

        // when
        onView(withId(R.id.btn_decrease)).perform(click())

        // then
        onView(withId(R.id.tv_people_count)).check(matches(withText("1")))
    }

    @Test
    fun `인원은_1명_이하로_감소하지_않는다`() {
        // when
        onView(withId(R.id.tv_people_count))
            .check(matches(withText("1")))

        // when
        onView(withId(R.id.btn_decrease)).perform(click())

        // then
        onView(withId(R.id.tv_people_count)).check(matches(withText("1")))
    }

    @Test
    fun `예매_선택_완료_버튼을_누르면_예매_확인_다이얼로그가_뜬다`() {
        // when
        onView(withId(R.id.btn_booking_complete)).perform(click())

        // then
        onView(withText("예매 확인")).check(matches(isDisplayed()))
        onView(withText("정말 예매하시겠습니까?")).check(matches(isDisplayed()))
        onView(withText("취소")).check(matches(isDisplayed()))
        onView(withText("예매 완료")).check(matches(isDisplayed()))
    }

    @Test
    fun `화면이_회전_되어도_인원수가_유지된다`() {
        // given
        onView(withId(R.id.btn_increase)).perform(click())

        // When
        scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        // then
        onView(withId(R.id.tv_people_count)).check(matches(withText("2")))
    }

    @Test
    fun `화면이_회전_되어도_선택된_날짜가_유지된다`() {
        // given
        onView(withId(R.id.sp_date)).perform(click())
        onData(anything()).atPosition(7).perform(click())
        onView(withId(R.id.sp_time)).perform(click())
        onData(anything()).atPosition(1).perform(click())

        // when
        scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        // then
        onView(withId(R.id.sp_date)).check(matches(withSpinnerText("2025-04-25")))
        onView(withId(R.id.sp_time)).check(matches(withSpinnerText("12:00")))
    }
}
