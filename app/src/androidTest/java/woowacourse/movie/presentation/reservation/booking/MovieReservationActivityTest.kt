package woowacourse.movie.presentation.reservation.booking

import android.content.pm.ActivityInfo
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import woowacourse.movie.R
import woowacourse.movie.utils.context

class MovieReservationActivityTest {
    @Test
    @DisplayName("상영중인 영화들이 정상적 화면에 보여지는지 테스트")
    fun test() {
        launchSuccessScenario()
        onView(withId(R.id.cl_reservation_movie)).check(matches(isDisplayed()))
    }

    @Test
    @DisplayName(
        "count 가 1일 때, plus button 클릭 시 count 가 2로 증가하고" +
                " minus button 클릭 시 count 가 1로 감소하는 지 테스트"
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

    private fun launchSuccessScenario() {
        ActivityScenario.launch<MovieReservationActivity>(
            MovieReservationActivity.newIntent(
                context,
                1L,
            ),
        )
    }
}
