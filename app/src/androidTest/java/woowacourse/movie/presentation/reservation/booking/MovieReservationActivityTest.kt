package woowacourse.movie.presentation.reservation.booking

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
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import woowacourse.movie.R
import woowacourse.movie.data.FakeMovieRepository
import woowacourse.movie.data.MovieRepositoryFactory
import woowacourse.movie.utils.context

class MovieReservationActivityTest {
    @Before
    fun setUp() {
        MovieRepositoryFactory.setMovieRepository(repository = FakeMovieRepository())
    }

    @After
    fun tearDown() {
        MovieRepositoryFactory.clear()
    }

    @Test
    @DisplayName("유효한 ID 가 전달되었을 때, 예약 화면이 보여지는지 테스트")
    fun test() {
        launchSuccessScenario()

        onView(withId(R.id.cl_reservation_movie_error))
            .check(matches(not(isDisplayed())))
        onView(withId(R.id.cl_reservation_movie_success))
            .check(matches(isDisplayed()))
    }

    @Test
    @DisplayName("유효하지 않은 ID 가 전달되었을 때, 에러 화면이 보여지는지 테스트")
    fun test2() {
        ActivityScenario.launch<MovieReservationActivity>(
            MovieReservationActivity.newIntent(
                context,
                MovieReservationActivity.INVALID_ID,
            ),
        )

        onView(withId(R.id.cl_reservation_movie_error))
            .check(matches(isDisplayed()))
        onView(withId(R.id.cl_reservation_movie_success))
            .check(matches(not(isDisplayed())))
    }

    @Test
    @DisplayName(
        "count 가 1일 때, plus button 클릭 시 count 가 2로 증가하고" +
            " minus button 클릭 시 count 가 1로 감소하는 지 테스트",
    )
    fun button_test1() {
        launchSuccessScenario()
        onView(withId(R.id.tv_reservation_count))
            .check(matches(withText("1")))
        onView(withId(R.id.btn_reservation_plus))
            .perform(click())
        onView(withId(R.id.tv_reservation_count))
            .check(matches(withText("2")))
        onView(withId(R.id.btn_reservation_minus))
            .perform(click())
        onView(withId(R.id.tv_reservation_count))
            .check(matches(withText("1")))
    }

    @Test
    @DisplayName("count 가 1일 때, minus button 클릭 시 count 가 1로 유지")
    fun button_test2() {
        launchSuccessScenario()
        onView(withId(R.id.tv_reservation_count))
            .check(matches(withText("1")))
        onView(withId(R.id.btn_reservation_minus))
            .perform(click())
        onView(withId(R.id.tv_reservation_count))
            .check(matches(withText("1")))
    }

    @Test
    @DisplayName(
        "count 가 1일 때, plus 버튼을 눌러 2로 만든 후" +
            "화면 회전 시에도 count 가 2가 유지",
    )
    fun restore_count_test() {
        val scenario = launchSuccessScenario()
        onView(withId(R.id.btn_reservation_plus))
            .perform(click())

        scenario.onActivity {
            it.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        onView(withId(R.id.tv_reservation_count))
            .check(matches(withText("2")))
    }

    @Test
    @DisplayName(
        "date picker 를 통해 Date 를 선택하고" +
            "화면 회전 시에도 선택한 날짜가 유지",
    )
    fun date_picker_test() {
        // given
        val datePosition = 2
        val selectDate = selectedDate(datePosition)
        // when : date picker 를 통해 날짜를 선택
        val scenario = launchSuccessScenario()
        onView(withId(R.id.sp_reservation_date))
            .perform(click())
        onView(withText(selectDate))
            .perform(click())
        // when: 화면 회전
        scenario.onActivity {
            it.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        // then: 선택한 날짜가 유지
        onView(withId(R.id.sp_reservation_date))
            .check(matches(withSpinnerText(containsString(selectDate))))
    }

    @Test
    @DisplayName(
        "time picker 를 통해 Time 을 선택하고" +
            "화면 회전 시에도 선택한 시간가 유지",
    )
    fun time_picker_test() {
        // given
        val timePosition = 2
        val selectTime = selectedTime(timePosition = timePosition)
        // when : date picker 를 통해 날짜를 선택
        val scenario = launchSuccessScenario()
        onView(withId(R.id.sp_reservation_time))
            .perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)), `is`(selectTime))).perform(click())
        // when: 화면 회전
        scenario.onActivity {
            it.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        // then: 선택한 날짜가 유지
        onView(withId(R.id.sp_reservation_time))
            .check(matches(withSpinnerText(containsString(selectTime))))
    }

    private fun launchSuccessScenario(): ActivityScenario<MovieReservationActivity> {
        return ActivityScenario.launch<MovieReservationActivity>(
            MovieReservationActivity.newIntent(
                context,
                1L,
            ),
        )
    }
}
