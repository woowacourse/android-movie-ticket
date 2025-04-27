package woowacourse.movie

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.DisplayName
import woowacourse.movie.fixtures.fakeContext
import woowacourse.movie.fixtures.theater
import woowacourse.movie.fixtures.ticket
import woowacourse.movie.view.movieReservationResult.MovieReservationResultActivity

class MovieReservationResultActivityTest {
    private lateinit var intent: Intent

    @get:Rule
    val activityRule = ActivityScenarioRule(MovieReservationResultActivity::class.java)

    @Before
    fun setUp() {
        intent =
            Intent(fakeContext, MovieReservationResultActivity::class.java).apply {
                putExtra("ticket", ticket)
                putExtra("theater", theater)
            }
        ActivityScenario.launch<MovieReservationResultActivity>(intent)
    }

    @Test
    @DisplayName("예매한 영화의 상영 시간이 표시된다")
    fun displayReservedMovieShowtimeTest() {
        onView(withId(R.id.showtime)).check(matches(withText("2025.04.15 11:00")))
    }

    @Test
    @DisplayName("선택한 좌석이 표시된다")
    fun displaySelectedSeatsTest() {
        onView(withId(R.id.selected_seats)).check(matches(withText("A1, C1, E1")))
    }

    @Test
    @DisplayName("예매한 인원수가 표시된다")
    fun displayReservedTicketCountTest() {
        onView(withId(R.id.ticket_count)).check(matches(withText("3명")))
    }

    @Test
    @DisplayName("예매한 총 금액이 표시된다")
    fun displayReservedTotalPriceTest() {
        onView(withId(R.id.total_price)).check(matches(withText("37,000원")))
    }
}
