package woowacourse.movie

import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.anything
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import woowacourse.movie.fixture.fakeContext
import woowacourse.movie.view.booking.BookingActivity
import woowacourse.movie.view.movie.MovieListActivity.Companion.KEY_MOVIE_END_DAY
import woowacourse.movie.view.movie.MovieListActivity.Companion.KEY_MOVIE_END_MONTH
import woowacourse.movie.view.movie.MovieListActivity.Companion.KEY_MOVIE_END_YEAR
import woowacourse.movie.view.movie.MovieListActivity.Companion.KEY_MOVIE_POSTER
import woowacourse.movie.view.movie.MovieListActivity.Companion.KEY_MOVIE_RUNNING_TIME
import woowacourse.movie.view.movie.MovieListActivity.Companion.KEY_MOVIE_START_DAY
import woowacourse.movie.view.movie.MovieListActivity.Companion.KEY_MOVIE_START_MONTH
import woowacourse.movie.view.movie.MovieListActivity.Companion.KEY_MOVIE_START_YEAR
import woowacourse.movie.view.movie.MovieListActivity.Companion.KEY_MOVIE_TITLE

@RunWith(JUnit4::class)
class BookingActivityTest {
    private lateinit var scenario: ActivityScenario<BookingActivity>

    @Before
    fun setUp() {
        val intent =
            Intent(
                fakeContext,
                BookingActivity::class.java,
            ).apply {
                putExtra(KEY_MOVIE_TITLE, "해리 포터와 마법사의 돌")
                putExtra(KEY_MOVIE_POSTER, R.drawable.harry_potter_one)
                putExtra(KEY_MOVIE_START_YEAR, 2025)
                putExtra(KEY_MOVIE_START_MONTH, 4)
                putExtra(KEY_MOVIE_START_DAY, 1)
                putExtra(KEY_MOVIE_END_YEAR, 2025)
                putExtra(KEY_MOVIE_END_MONTH, 4)
                putExtra(KEY_MOVIE_END_DAY, 25)
                putExtra(KEY_MOVIE_RUNNING_TIME, "152분")
            }

        scenario = ActivityScenario.launch(intent)
    }

    @DisplayName("전달 받은 영화 이름을 출력한다")
    @Test
    fun movieTitleDisplayTest() {
        onView(withId(R.id.tv_title)).check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @DisplayName("전달 받은 상영일 출력한다")
    @Test
    fun movieReleaseDateDisplayTest() {
        onView(withId(R.id.tv_screening_period)).check(matches(withText("2025.4.1 ~ 2025.4.25")))
    }

    @DisplayName("전달 받은 상영 시간을 출력한다")
    @Test
    fun movieRunningTimeDisplayTest() {
        onView(withId(R.id.tv_running_time)).check(matches(withText("152분")))
    }

    @DisplayName("상영 날짜 스피너에 날짜 목록이 표시된다")
    @Test
    fun releasedDateSpinnerDisplayTest() {
        onView(withId(R.id.sp_date)).check(matches(isDisplayed()))
    }

    @DisplayName("예매 가능 시간 스피너에 시간 목록이 표시된다")
    @Test
    fun releasedTimeSpinnerDisplayTest() {
        onView(withId(R.id.sp_time)).check(matches(isDisplayed()))
    }

    @DisplayName("인원 증가 버튼을 누르면 인원이 1 증가한다")
    @Test
    fun increaseBtnTest() {
        onView(withId(R.id.btn_increase)).perform(click())
        onView(withId(R.id.tv_people_count)).check(matches(withText("2")))
    }

    @DisplayName("인원 감소 버튼을 누르면 인원이 1 감소한다")
    @Test
    fun decreaseBtnTest() {
        onView(withId(R.id.btn_increase)).perform(click())
        onView(withId(R.id.btn_decrease)).perform(click())
        onView(withId(R.id.tv_people_count)).check(matches(withText("1")))
    }

    @DisplayName("인원은 1명 이하로 감소하지 않는다")
    @Test
    fun minDecreaseBtnTest() {
        onView(withId(R.id.btn_decrease)).perform(click())
        onView(withId(R.id.tv_people_count)).check(matches(withText("1")))
    }

    @DisplayName("예매 선택 완료 버튼을 누르면 예매 확인 다이얼로그가 뜬다")
    @Test
    fun bookingDialogDisplayTest() {
        onView(withId(R.id.btn_booking_complete)).perform(click())
        onView(withText("예매 확인")).check(matches(isDisplayed()))
        onView(withText("정말 예매하시겠습니까?")).check(matches(isDisplayed()))
        onView(withText("취소")).check(matches(isDisplayed()))
        onView(withText("예매 완료")).check(matches(isDisplayed()))
    }

    @DisplayName("다이얼로그 외부 영역을 클릭해도 다이얼로그가 유지된다")
    @Test
    fun dialogOutsideTouchTest() {
        onView(withId(R.id.btn_booking_complete))
            .perform(click())

        pressBack()

        onView(withText("예매 확인")).check(matches(isDisplayed()))
        onView(withText("정말 예매하시겠습니까?")).check(matches(isDisplayed()))
        onView(withText("취소")).check(matches(isDisplayed()))
        onView(withText("예매 완료")).check(matches(isDisplayed()))
    }

    @DisplayName("화면이 회전 되어도 인원수가 유지된다")
    @Test
    fun configurationChangePeopleCountTest() {
        onView(withId(R.id.btn_increase)).perform(click())
        scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        onView(withId(R.id.tv_people_count)).check(matches(withText("2")))
        scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }

    @DisplayName("화면이 회전 되어도 선택된 날짜가 유지된다")
    @Test
    fun configurationChangeDateTest() {
        onView(withId(R.id.sp_date))
            .perform(click())

        onData(anything())
            .atPosition(7)
            .perform(click())

        onView(withId(R.id.sp_time))
            .perform(click())

        onData(anything())
            .atPosition(1)
            .perform(click())

        scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        onView(withId(R.id.sp_date))
            .check(matches(withSpinnerText("2025-04-25")))

        onView(withId(R.id.sp_time))
            .check(matches(withSpinnerText("12:00")))
    }
}
