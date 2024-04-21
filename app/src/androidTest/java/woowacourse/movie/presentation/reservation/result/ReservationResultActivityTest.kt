package woowacourse.movie.presentation.reservation.result

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import woowacourse.movie.R
import woowacourse.movie.data.FakeMovieRepository
import woowacourse.movie.model.HeadCount
import woowacourse.movie.utils.context
import java.time.LocalDateTime

class ReservationResultActivityTest {
    @Test
    @DisplayName("유효한 ID 가 전달되었을 때, 예약 결과가 보여지는지 테스트")
    fun test() {
        launchSuccessScenario()

        Espresso.onView(ViewMatchers.withId(R.id.cl_reservation_result_error))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
        Espresso.onView(ViewMatchers.withId(R.id.cl_reservation_result_success))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    @DisplayName("유효하지 않은 ID 가 전달되었을 때, 에러 화면이 보여지는지 테스트")
    fun test2() {
        ActivityScenario.launch<ReservationResultActivity>(
            ReservationResultActivity.newIntent(
                context,
                ReservationResultActivity.INVALID_RESERVATION_ID,
            ),
        )

        Espresso.onView(ViewMatchers.withId(R.id.cl_reservation_result_error))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.cl_reservation_result_success))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
    }

    private fun launchSuccessScenario(): ActivityScenario<ReservationResultActivity> {
        // 이렇게 안하고 어떻게 하지...?
        FakeMovieRepository.reserveMovie(1, LocalDateTime.now(), HeadCount(1))
        return ActivityScenario.launch<ReservationResultActivity>(
            ReservationResultActivity.newIntent(
                context,
                1L,
            ),
        )
    }
}
