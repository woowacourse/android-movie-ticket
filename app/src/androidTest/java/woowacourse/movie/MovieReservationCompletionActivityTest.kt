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
import woowacourse.movie.fixtures.ticket

class MovieReservationCompletionActivityTest {
    private lateinit var intent: Intent

    @get:Rule
    val activityRule = ActivityScenarioRule(MovieReservationActivity::class.java)

    @Before
    fun setUp() {
        intent =
            Intent(fakeContext, MovieReservationCompletionActivity::class.java).apply {
                putExtra("ticket", ticket)
            }
        ActivityScenario.launch<MovieReservationCompletionActivity>(intent)
    }

    @Test
    @DisplayName("예매한 영화의 제목이 표시된다")
    fun displayReservedMovieTitleTest() {
        onView(withId(R.id.movie_title)).check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    @DisplayName("예매한 영화의 상영 시간이 표시된다")
    fun displayReservedMovieShowTimeTest() {
        onView(withId(R.id.showtime)).check(matches(withText("2025.04.15 11:00")))
    }

    @Test
    @DisplayName("예매한 인원수가 표시된다")
    fun displayReservedTicketCountTest() {
        onView(withId(R.id.ticket_count)).check(matches(withText("2명")))
    }

    @Test
    @DisplayName("예매한 총 금액이 표시된다")
    fun displayReservedTotalPriceTest() {
        onView(withId(R.id.total_price)).check(matches(withText("26,000원")))
    }
}
